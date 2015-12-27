package me.ilich.juggler;

import android.support.annotation.Nullable;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public abstract class Screen<P extends Screen.Params> {

    public Instance create(P params) {
        return new Instance(this, params);
    }

    protected abstract Class<? extends JugglerToolbarFragment> toolbarClass();

    protected abstract Class<? extends JugglerContentFragment> contentClass();

    @Nullable
    private ToolbarFragmentFactory instanceToolbar(final Class<P> paramsClass) {
        return new ToolbarFragmentFactory() {
            @Override
            public JugglerToolbarFragment create(Params params) {
                JugglerToolbarFragment r = null;
                Class<? extends JugglerToolbarFragment> clazz = toolbarClass();
                try {
                    Method method = clazz.getMethod("create", paramsClass);
                    r = (JugglerToolbarFragment) method.invoke(null, params);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                if (r == null) {
                    try {
                        Method method = clazz.getMethod("create");
                        r = (JugglerToolbarFragment) method.invoke(null);
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
                return r;
            }
        };
    }

    @Nullable
    private ContentFragmentFactory instanceContent(final Class<P> paramsClass) {
        return new ContentFragmentFactory() {
            @Override
            public JugglerContentFragment create(Params params) {
                JugglerContentFragment r = null;
                Class<? extends JugglerContentFragment> clazz = contentClass();
                try {
                    Method method = clazz.getMethod("create", paramsClass);
                    r = (JugglerContentFragment) method.invoke(null, params);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                return r;
            }
        };
    }

    public interface ContentFragmentFactory {

        JugglerContentFragment create(Params params);

    }

    public interface ToolbarFragmentFactory {

        JugglerToolbarFragment create(Params params);

    }

    public static class Params {

    }

    public static class Instance {

        private final Params params;
        private final ContentFragmentFactory contentFragmentFactory;
        private final ToolbarFragmentFactory toolbarFragmentFactory;

        public Instance(Screen screen, Params params) {
            this.params = params;
            this.contentFragmentFactory = screen.instanceContent(params.getClass());
            this.toolbarFragmentFactory = screen.instanceToolbar(params.getClass());
        }

        public JugglerToolbarFragment instanceToolbar() {
            return toolbarFragmentFactory.create(params);
        }

        public JugglerContentFragment instanceContent() {
            return contentFragmentFactory.create(params);
        }

    }


}
