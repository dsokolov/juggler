package me.ilich.juggler;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import me.ilich.juggler.fragments.content.JugglerContentFragment;
import me.ilich.juggler.fragments.navigation.JugglerNavigationFragment;
import me.ilich.juggler.fragments.toolbar.JugglerToolbarFragment;

public interface Screen {

/*    boolean back();

    boolean up();*/

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
                public Bundle create(@Nullable Params params) {
                    return ReflectionUtils.createFragmentBundle(params, screenClass);
                }

            };
        }

    }

    interface FragmentFactory {

        class Bundle {

            @Nullable
            private final JugglerToolbarFragment toolbarFragment;
            @Nullable
            private final JugglerNavigationFragment navigationFragment;
            @Nullable
            private final JugglerContentFragment contentFragment;

            public Bundle(@Nullable JugglerToolbarFragment toolbarFragment, @Nullable JugglerNavigationFragment navigationFragment, @Nullable JugglerContentFragment contentFragment) {
                this.toolbarFragment = toolbarFragment;
                this.navigationFragment = navigationFragment;
                this.contentFragment = contentFragment;
            }

            @Nullable
            public JugglerToolbarFragment getToolbarFragment() {
                return toolbarFragment;
            }

            @Nullable
            public JugglerNavigationFragment getNavigationFragment() {
                return navigationFragment;
            }

            @Nullable
            public JugglerContentFragment getContentFragment() {
                return contentFragment;
            }

        }

        Bundle create(@Nullable Params params);

    }

    class Params {

        @Override
        public String toString() {
            return getClass().getCanonicalName();
        }

    }

    final class Instance {

        private final String name;
        private final Params params;
        private final FragmentFactory fragmentFactory;
        @Nullable
        private Fragment.SavedState contentSavedState = null;
        @Nullable
        private Fragment.SavedState toolbarSavedState = null;
        @Nullable
        private Fragment.SavedState navigationSavedState = null;

        Instance(@NonNull Class<? extends Screen> screen, Params params) {
            name = screen.getSimpleName() + " " + params;
            this.params = params;
            this.fragmentFactory = Screen.Factory.instance(screen);
        }

        public FragmentFactory.Bundle instanceFragments() {
            return fragmentFactory.create(params);
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

        @Override
        public String toString() {
            return "ScreenInstance " + name;
        }

    }

}
