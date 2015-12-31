package me.ilich.juggler;

import android.support.annotation.Nullable;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import me.ilich.juggler.fragments.JugglerFragment;
import me.ilich.juggler.fragments.JugglerNewInstance;
import me.ilich.juggler.fragments.content.JugglerContent;
import me.ilich.juggler.fragments.content.JugglerContentFragment;
import me.ilich.juggler.fragments.navigation.JugglerNavigation;
import me.ilich.juggler.fragments.navigation.JugglerNavigationFragment;
import me.ilich.juggler.fragments.toolbar.JugglerToolbar;
import me.ilich.juggler.fragments.toolbar.JugglerToolbarFragment;

public class ReflectionUtils {


    public static Screen.FragmentFactory.Bundle createFragmentBundle(Screen.Params params, Class<? extends Screen> screenClass) {
        //final JugglerToolbarFragment toolbarFragment = createToolbarFragment(params, screenClass);
        final JugglerToolbarFragment toolbarFragment;
        if (screenClass.isAnnotationPresent(JugglerToolbar.class)) {
            JugglerToolbar annotation = screenClass.getAnnotation(JugglerToolbar.class);
            Class<? extends JugglerToolbarFragment> fragmentClass = annotation.value();
            int options = annotation.options();
            Method noParams = null;
            Method oneParam = null;
            for (Method method : fragmentClass.getMethods()) {
                if (method.isAnnotationPresent(JugglerNewInstance.class)) {
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
            JugglerToolbarFragment fragment = null;
            if (oneParam != null) {
                try {
                    fragment = (JugglerToolbarFragment) oneParam.invoke(null, params);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            } else if (noParams != null) {
                try {
                    fragment = (JugglerToolbarFragment) noParams.invoke(null);
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
            if (fragment != null) {
                fragment.setInitialOptions(options);
            }
            toolbarFragment = fragment;
        } else {
            toolbarFragment = null;
        }
        final JugglerNavigationFragment navigationFragment = createNavigationFragment(params, screenClass);
        final JugglerContentFragment contentFragment = createContentFragment(params, screenClass);
        return new Screen.FragmentFactory.Bundle(toolbarFragment, navigationFragment, contentFragment);
    }

    static JugglerNavigationFragment createNavigationFragment(@Nullable Screen.Params params, Class<? extends Screen> screenClass) {
        return createFragment(params, screenClass, JugglerNavigation.class, onGetNavigationJugglerClass);
    }

    static JugglerContentFragment createContentFragment(@Nullable Screen.Params params, Class<? extends Screen> screenClass) {
        return createFragment(params, screenClass, JugglerContent.class, onGetContentJugglerClass);
    }

    @Nullable
    private static <F extends JugglerFragment> F createFragment(@Nullable Screen.Params params, Class<? extends Screen> screenClass, Class<? extends Annotation> annotation, OnGetJugglerClass<F> onGetJugglerClass) {
        F r = null;
        if (screenClass.isAnnotationPresent(annotation)) {
            Class<? extends F> anotatedClass = onGetJugglerClass.getJugglerClass(screenClass);
            F oneParamsFragment = null;
            F noParamsFragment = null;
            try {
                for (Method method : anotatedClass.getMethods()) {
                    if (method.isAnnotationPresent(JugglerNewInstance.class)) {
                        Class[] methodParams = method.getParameterTypes();
                        switch (methodParams.length) {
                            case 1:
                                if (params != null && params.getClass().equals(methodParams[0])) {
                                    oneParamsFragment = (F) method.invoke(null, params);
                                }
                                break;
                            case 0:
                                noParamsFragment = (F) method.invoke(null);
                                break;
                        }
                    }
                }
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if (oneParamsFragment != null) {
                r = oneParamsFragment;
            } else if (noParamsFragment != null) {
                r = noParamsFragment;
            }
            if (r == null) {
                try {
                    r = anotatedClass.newInstance();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return r;
    }

    public interface OnGetJugglerClass<F extends JugglerFragment> {

        Class<? extends F> getJugglerClass(Class<? extends Screen> screenClass);

    }

    private static OnGetJugglerClass<JugglerNavigationFragment> onGetNavigationJugglerClass = new OnGetJugglerClass<JugglerNavigationFragment>() {

        @Override
        public Class<? extends JugglerNavigationFragment> getJugglerClass(Class<? extends Screen> screenClass) {
            JugglerNavigation content = screenClass.getAnnotation(JugglerNavigation.class);
            return content.value();
        }

    };

    private static OnGetJugglerClass<JugglerContentFragment> onGetContentJugglerClass = new OnGetJugglerClass<JugglerContentFragment>() {

        @Override
        public Class<? extends JugglerContentFragment> getJugglerClass(Class<? extends Screen> screenClass) {
            JugglerContent content = screenClass.getAnnotation(JugglerContent.class);
            return content.value();
        }

    };


}
