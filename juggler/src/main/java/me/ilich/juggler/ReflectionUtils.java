package me.ilich.juggler;

import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import me.ilich.juggler.fragments.JugglerFragment;
import me.ilich.juggler.fragments.JugglerLayout;
import me.ilich.juggler.fragments.content.JugglerContent;
import me.ilich.juggler.fragments.content.JugglerContentFragment;
import me.ilich.juggler.fragments.navigation.JugglerNavigation;
import me.ilich.juggler.fragments.navigation.JugglerNavigationFragment;
import me.ilich.juggler.fragments.toolbar.JugglerToolbar;
import me.ilich.juggler.fragments.toolbar.JugglerToolbarFragment;

public class ReflectionUtils {

    public static Screen.FragmentFactory.Bundle createFragmentBundle(Screen.Params params, Class<? extends Screen> screenClass) {
        final JugglerToolbarFragment toolbarFragment = getToolbarFragment(params, screenClass);
        final JugglerNavigationFragment navigationFragment = getNavigationFragment(params, screenClass);
        final JugglerContentFragment contentFragment = getContentFragment(params, screenClass);
        final int layoutId;
        if (screenClass.isAnnotationPresent(JugglerLayout.class)) {
            JugglerLayout jugglerLayout = screenClass.getAnnotation(JugglerLayout.class);
            layoutId = jugglerLayout.value();
        } else {
            throw new RuntimeException(screenClass.getName() + " should have a " + JugglerLayout.class.getName() + " annotation.");
        }
        return new Screen.FragmentFactory.Bundle(toolbarFragment, navigationFragment, contentFragment, layoutId);
    }

    @Nullable
    private static JugglerContentFragment getContentFragment(Screen.Params params, Class<? extends Screen> screenClass) {
        final JugglerContentFragment contentFragment;
        if (screenClass.isAnnotationPresent(JugglerContent.class)) {
            JugglerContent annotation = screenClass.getAnnotation(JugglerContent.class);
            Class<? extends JugglerContentFragment> fragmentClass = annotation.value();
            contentFragment = createFragment(params, fragmentClass);
        } else {
            contentFragment = null;
        }
        return contentFragment;
    }

    @Nullable
    private static <T extends JugglerFragment> T createFragment(Screen.Params params, Class<? extends T> fragmentClass) {
        T fragment = null;
        Methods methods = new Methods(params, fragmentClass).invoke();
        if (methods.oneParam != null) {
            try {
                fragment = (T) methods.oneParam.invoke(null, params);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } else if (methods.noParams != null) {
            try {
                fragment = (T) methods.noParams.invoke(null);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } else {
            try {
                fragment = fragmentClass.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return fragment;
    }

    @Nullable
    private static JugglerNavigationFragment getNavigationFragment(Screen.Params params, Class<? extends Screen> screenClass) {
        final JugglerNavigationFragment navigationFragment;
        if (screenClass.isAnnotationPresent(JugglerNavigation.class)) {
            JugglerNavigation annotation = screenClass.getAnnotation(JugglerNavigation.class);
            Class<? extends JugglerNavigationFragment> fragmentClass = annotation.value();
            int menuItem = annotation.menuItem();
            JugglerNavigationFragment fragment = createFragment(params, fragmentClass);
            if (fragment != null) {
                fragment.setSelectedItem(menuItem);
            }
            navigationFragment = fragment;
        } else {
            navigationFragment = null;
        }
        return navigationFragment;
    }

    @Nullable
    private static JugglerToolbarFragment getToolbarFragment(Screen.Params params, Class<? extends Screen> screenClass) {
        final JugglerToolbarFragment toolbarFragment;
        if (screenClass.isAnnotationPresent(JugglerToolbar.class)) {
            JugglerToolbar annotation = screenClass.getAnnotation(JugglerToolbar.class);
            Class<? extends JugglerToolbarFragment> fragmentClass = annotation.value();
            @ActionBar.DisplayOptions int options = annotation.options();
            @DrawableRes int navigationIcon = annotation.navigationIcon();
            JugglerToolbarFragment fragment = createFragment(params, fragmentClass);
            if (fragment != null) {
                fragment.setOptions(options);
                fragment.setNavigationIcon(navigationIcon);
            }
            toolbarFragment = fragment;
        } else {
            toolbarFragment = null;
        }
        return toolbarFragment;
    }

    private static class Methods {

        private Screen.Params params;
        private Class<? extends JugglerFragment> fragmentClass;
        private Method noParams;
        private Method oneParam;

        public Methods(Screen.Params params, Class<? extends JugglerFragment> fragmentClass) {
            this.params = params;
            this.fragmentClass = fragmentClass;
        }

        public Methods invoke() {
            for (Method method : fragmentClass.getMethods()) {
                boolean isStatic = Modifier.isStatic(method.getModifiers());
                boolean isReturnsSame = method.getReturnType().equals(fragmentClass);
                if (isStatic && isReturnsSame) {
                    switch (method.getParameterTypes().length) {
                        case 0:
                            noParams = method;
                            break;
                        case 1:
                            if (method.getParameterTypes()[0].equals(params.getClass())) {
                                oneParam = method;
                            }
                            break;
                    }
                }
            }
            return this;
        }
    }
}
