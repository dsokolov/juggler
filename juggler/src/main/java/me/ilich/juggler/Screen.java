package me.ilich.juggler;

import android.support.annotation.Nullable;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public abstract class Screen<P extends Screen.Params> {

    public Instance create(P params) {
        return new Instance(this, params);
    }

    @Nullable
    private ToolbarFragmentFactory instanceToolbar(final Class<Screen> screenClass, final Class<P> paramsClass) {
        return new ToolbarFragmentFactory() {
            @Override
            public JugglerToolbarFragment create(Params params) {
                return ReflectionUtils.createToolbarFragment(params, screenClass);
            }
        };
    }

    @Nullable
    private ContentFragmentFactory instanceContent(final Class<? extends Screen> screenClass, final Class<P> paramsClass) {
        return new ContentFragmentFactory() {
            @Override
            public JugglerContentFragment create(Params params) {
                return ReflectionUtils.createContentFragment(params, screenClass);
            }
        };
    }

    interface ContentFragmentFactory {

        JugglerContentFragment create(Params params);

    }

    interface ToolbarFragmentFactory {

        JugglerToolbarFragment create(Params params);

    }

    public static class Params {

    }

    public static final class Instance {

        private final Params params;
        private final ContentFragmentFactory contentFragmentFactory;
        private final ToolbarFragmentFactory toolbarFragmentFactory;

        Instance(Screen screen, Params params) {
            this.params = params;
            this.contentFragmentFactory = screen.instanceContent(screen.getClass(), params.getClass());
            this.toolbarFragmentFactory = screen.instanceToolbar(screen.getClass(), params.getClass());
        }

        public JugglerToolbarFragment instanceToolbar() {
            return toolbarFragmentFactory.create(params);
        }

        public JugglerContentFragment instanceContent() {
            return contentFragmentFactory.create(params);
        }

    }


}
