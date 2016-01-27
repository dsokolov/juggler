package me.ilich.juggler;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import java.io.Serializable;

import me.ilich.juggler.old.fragments.Navigator_;
import me.ilich.juggler.old.fragments.Transition_;
import me.ilich.juggler.old.fragments.content.JugglerContentFragment_;
import me.ilich.juggler.old.fragments.navigation.JugglerNavigationFragment;
import me.ilich.juggler.old.fragments.toolbar.JugglerToolbarFragment;

public class Screen {

    private final Navigator_ navigator;

    public Screen(Navigator_ navigator) {
        this.navigator = navigator;
    }

    protected final void navigate(Transition_ transition, @Nullable Screen.Params params) {
        navigator.navigate(transition, params);
    }

/*    boolean back();

    boolean up();*/

    public static class Factory {

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

    public interface FragmentFactory {

        class Bundle {

            @Nullable
            private final JugglerToolbarFragment toolbarFragment;
            @Nullable
            private final JugglerNavigationFragment navigationFragment;
            @Nullable
            private final JugglerContentFragment_ contentFragment;
            @LayoutRes
            private final int layoutId;

            public Bundle(@Nullable JugglerToolbarFragment toolbarFragment, @Nullable JugglerNavigationFragment navigationFragment, @Nullable JugglerContentFragment_ contentFragment, int layoutId) {
                this.toolbarFragment = toolbarFragment;
                this.navigationFragment = navigationFragment;
                this.contentFragment = contentFragment;
                this.layoutId = layoutId;
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
            public JugglerContentFragment_ getContentFragment() {
                return contentFragment;
            }

            @LayoutRes
            public int getLayoutId() {
                return layoutId;
            }

        }

        Bundle create(@Nullable Params params);

    }

    public static class Params {

        @Override
        public String toString() {
            return getClass().getCanonicalName();
        }

    }

    public final static class Instance implements Serializable {

        private final String screenClassName;
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
            screenClassName = screen.getName();
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

        public String getScreenClassName() {
            return screenClassName;
        }

    }

}
