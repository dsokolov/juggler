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
        private static FragmentFactory instance(final Class<? extends Screen> screenClass) {
            return new FragmentFactory() {

                @Override
                public JugglerToolbarFragment createToolbar(Params params) {
                    return ReflectionUtils.createToolbarFragment(params, screenClass);
                }

                @Override
                public JugglerContentFragment createContent(Params params) {
                    return ReflectionUtils.createContentFragment(params, screenClass);
                }

                @Override
                public JugglerNavigationFragment createNavigation(Params params) {
                    return ReflectionUtils.createNavigationFragment(params, screenClass);
                }

            };
        }

    }

    interface FragmentFactory {

        JugglerContentFragment createContent(@Nullable Params params);

        JugglerToolbarFragment createToolbar(@Nullable Params params);

        JugglerNavigationFragment createNavigation(@Nullable Params params);

    }

    class Params {

    }

    final class Instance {

        private final Params params;
        private final FragmentFactory fragmentFactory;
        @Nullable
        private Fragment.SavedState contentSavedState = null;
        @Nullable
        private Fragment.SavedState toolbarSavedState = null;
        @Nullable
        private Fragment.SavedState navigationSavedState = null;

        Instance(@NonNull Class<? extends Screen> screen, Params params) {
            this.params = params;
            this.fragmentFactory = Screen.Factory.instance(screen);
        }

        public JugglerToolbarFragment instanceToolbar() {
            return fragmentFactory.createToolbar(params);
        }

        public JugglerNavigationFragment instanceNavigation() {
            return fragmentFactory.createNavigation(params);
        }

        public JugglerContentFragment instanceContent() {
            return fragmentFactory.createContent(params);
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
