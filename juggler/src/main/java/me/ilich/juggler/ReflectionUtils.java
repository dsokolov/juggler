package me.ilich.juggler;

import android.support.annotation.Nullable;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionUtils {

    @Nullable
    static JugglerContentFragment createContentFragment(Screen.Params params, Class<? extends Screen> screenClass) {
        JugglerContentFragment oneParamsFragment = null;
        JugglerContentFragment noParamsFragment = null;
        if (screenClass.isAnnotationPresent(JugglerContent.class)) {
            JugglerContent content = screenClass.getAnnotation(JugglerContent.class);
            Class<? extends JugglerContentFragment> clazz = content.value();
            try {
                for (Method method : clazz.getMethods()) {
                    if (method.isAnnotationPresent(JugglerNewInstance.class)) {
                        Class[] methodParams = method.getParameterTypes();
                        switch (methodParams.length) {
                            case 1:
                                if (params.getClass().equals(methodParams[0])) {
                                    oneParamsFragment = (JugglerContentFragment) method.invoke(null, params);
                                }
                                break;
                            case 0:
                                noParamsFragment = (JugglerContentFragment) method.invoke(null, null);
                                break;
                        }
                    }
                }
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        JugglerContentFragment r = null;
        if (oneParamsFragment != null) {
            r = oneParamsFragment;
        } else if (noParamsFragment != null) {
            r = noParamsFragment;
        }
        return r;
    }

    @Nullable
    static JugglerToolbarFragment createToolbarFragment(Screen.Params params, Class<Screen> screenClass) {
        JugglerToolbarFragment oneParamsFragment = null;
        JugglerToolbarFragment noParamsFragment = null;
        if (screenClass.isAnnotationPresent(JugglerToolbar.class)) {
            JugglerToolbar content = screenClass.getAnnotation(JugglerToolbar.class);
            Class<? extends JugglerToolbarFragment> clazz = content.value();
            try {
                for (Method method : clazz.getMethods()) {
                    if (method.isAnnotationPresent(JugglerNewInstance.class)) {
                        Class[] methodParams = method.getParameterTypes();
                        switch (methodParams.length) {
                            case 1:
                                if (params.getClass().equals(methodParams[0])) {
                                    oneParamsFragment = (JugglerToolbarFragment) method.invoke(null, params);
                                }
                                break;
                            case 0:
                                noParamsFragment = (JugglerToolbarFragment) method.invoke(null, null);
                                break;
                        }
                    }
                }
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        JugglerToolbarFragment r = null;
        if (oneParamsFragment != null) {
            r = oneParamsFragment;
        } else if (noParamsFragment != null) {
            r = noParamsFragment;
        }
        return r;
    }


}
