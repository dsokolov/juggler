package me.ilich.juggler;

import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;

public abstract class Screen implements Cloneable {

    private static final String TAG_TOOLBAR = "toolbar";
    private static final String TAG_CONTENT = "content";

    private Action backAction = Action.nothing();
    private Action upAction = Action.nothing();
    @Nullable
    private Screen backScreen;

    public void showOn(JugglerActivity activity) {
        processToolbar(activity);

        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        int contentContainerId = activity.getContainerContentLayoutId();
        if (contentContainerId != 0) {
            JugglerContentFragment contentFragment = instanceContent();
            if (contentFragment != null) {
                fragmentManager.beginTransaction().replace(contentContainerId, contentFragment, TAG_CONTENT).commit();
            }
        }

        activity.setCurrentScreen(this);
    }

    private void processToolbar(JugglerActivity activity) {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();

        int toolbarContainerId = activity.getContainerToolbarLayoutId();
        if (toolbarContainerId != 0) {
            JugglerToolbarFragment newToolbarFragment = instanceToolbar();
            JugglerToolbarFragment currentToolbarFragment = (JugglerToolbarFragment) fragmentManager.findFragmentByTag(TAG_TOOLBAR);
            if (currentToolbarFragment == null) {
                if (newToolbarFragment != null) {
                    fragmentManager.beginTransaction().replace(toolbarContainerId, newToolbarFragment, TAG_TOOLBAR).commit();
                }
            } else {
                if (newToolbarFragment == null) {
                    fragmentManager.beginTransaction().remove(currentToolbarFragment).commit();
                } else {
                    if (!newToolbarFragment.getClass().equals(currentToolbarFragment.getClass())) {
                        fragmentManager.beginTransaction().replace(toolbarContainerId, newToolbarFragment, TAG_TOOLBAR).commit();
                    }
                }
            }
        }
    }

    @Nullable
    protected abstract JugglerToolbarFragment instanceToolbar();

    @Nullable
    protected abstract JugglerContentFragment instanceContent();

    public void setBack(Action action) {
        this.backAction = action;
    }

    public void setBack(@Nullable Screen screen) {
        this.backScreen = screen;
    }

    public void setUp(Action action) {
        this.upAction = action;
    }

    @Nullable
    public Screen getBackScreen() {
        return backScreen;
    }

}
