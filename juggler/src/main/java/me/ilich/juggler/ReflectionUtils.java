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

    static JugglerToolbarFragment createToolbarFragment(@Nullable Screen.Params params, Class<? extends Screen> screenClass) {
        return createFragment(params, screenClass, JugglerToolbar.class, onGetToolbarJugglerClass);
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

    private static OnGetJugglerClass<JugglerToolbarFragment> onGetToolbarJugglerClass = new OnGetJugglerClass<JugglerToolbarFragment>() {

        @Override
        public Class<? extends JugglerToolbarFragment> getJugglerClass(Class<? extends Screen> screenClass) {
            JugglerToolbar content = screenClass.getAnnotation(JugglerToolbar.class);
            return content.value();
        }

    };

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
