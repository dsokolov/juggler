package me.ilich.juggler;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import me.ilich.juggler.fragments.content.JugglerContentFragment;
import me.ilich.juggler.fragments.navigation.JugglerNavigationFragment;
import me.ilich.juggler.fragments.toolbar.JugglerToolbarFragment;

public interface Screen {

    boolean back();

    boolean up();

    class Factory {

        public static Instance create(Class<? extends Screen> screen, Params params) {
            return new Instance(screen, params);
        }

        public static Instance create(Class<? extends Screen> screen) {
            return new Instance(screen, new Params());
        }

        @Nullable
        private static ToolbarFragmentFactory instanceToolbar(final Class<? extends Screen> screenClass) {
            return new ToolbarFragmentFactory() {
                @Override
                public JugglerToolbarFragment create(Params params) {
                    return ReflectionUtils.createToolbarFragment(params, screenClass);
                }
            };
        }

        @Nullable
        private static ContentFragmentFactory instanceContent(final Class<? extends Screen> screenClass) {
            return new ContentFragmentFactory() {
                @Override
                public JugglerContentFragment create(Params params) {
                    return ReflectionUtils.createContentFragment(params, screenClass);
                }
            };
        }

        @Nullable
        private static NavigationFragmentFactory instanceNavigation(final Class<? extends Screen> screenClass) {
            return new NavigationFragmentFactory() {
                @Override
                public JugglerNavigationFragment create(Params params) {
                    return ReflectionUtils.createNavigationFragment(params, screenClass);
                }
            };
        }

    }

    interface ContentFragmentFactory {

        JugglerContentFragment create(@Nullable Params params);

    }

    interface ToolbarFragmentFactory {

        JugglerToolbarFragment create(@Nullable Params params);

    }

    interface NavigationFragmentFactory {

        JugglerNavigationFragment create(@Nullable Params params);

    }

    class Params {

    }

    final class Instance {

        private final Params params;
        private final ContentFragmentFactory contentFragmentFactory;
        private final ToolbarFragmentFactory toolbarFragmentFactory;
        private final NavigationFragmentFactory navigationFragmentFactory;
        @Nullable
        private Fragment.SavedState contentSavedState = null;
        @Nullable
        private Fragment.SavedState toolbarSavedState = null;
        @Nullable
        private Fragment.SavedState navigationSavedState = null;

        Instance(@NonNull Class<? extends Screen> screen, Params params) {
            this.params = params;
            this.contentFragmentFactory = Screen.Factory.instanceContent(screen);
            this.toolbarFragmentFactory = Screen.Factory.instanceToolbar(screen);
            this.navigationFragmentFactory = Screen.Factory.instanceNavigation(screen);
        }

        public JugglerToolbarFragment instanceToolbar() {
            return toolbarFragmentFactory.create(params);
        }

        public JugglerNavigationFragment instanceNavigation() {
            return navigationFragmentFactory.create(params);
        }

        public JugglerContentFragment instanceContent() {
            return contentFragmentFactory.create(params);
        }

        @Nullable
        public Fragment.SavedState getContentSavedState() {
            return contentSavedState;
        }

        public void setContentSavedState(@Nullable Fragment.SavedState contentSavedState) {
            this.contentSavedState = contentSavedState;
        }

        @Nullable
        public Fragment.SavedState getToolbarSavedState() {
            return toolbarSavedState;
        }

        public void setToolbarSavedState(@Nullable Fragment.SavedState toolbarSavedState) {
            this.toolbarSavedState = toolbarSavedState;
        }


        @Nullable
        public Fragment.SavedState getNavigationSavedState() {
            return navigationSavedState;
        }

        public void setNavigationSavedState(@Nullable Fragment.SavedState navigationSavedState) {
            this.navigationSavedState = navigationSavedState;
        }

    }


}
