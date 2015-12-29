package me.ilich.juggler;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.ArrayList;
import java.util.List;

import me.ilich.juggler.fragments.JugglerFragment;
import me.ilich.juggler.fragments.content.JugglerContentFragment;
import me.ilich.juggler.fragments.toolbar.JugglerToolbarFragment;

public abstract class ScreensManager {

    private static void putToolbarFragmentOnActivity(FragmentManager fragmentManager, @IdRes int containerId, Screen.Instance screenInstance, JugglerFragment fragment, String tag) {
        Fragment.SavedState savedState = screenInstance.getToolbarSavedState();
        fragment.setInitialSavedState(savedState);
        fragmentManager.beginTransaction().replace(containerId, fragment, tag).commit();
    }

    private static void putContentFragmentOnActivity(FragmentManager fragmentManager, @IdRes int containerId, Screen.Instance screenInstance, JugglerFragment fragment, String tag) {
        Fragment.SavedState savedState = screenInstance.getContentSavedState();
        fragment.setInitialSavedState(savedState);
        fragmentManager.beginTransaction().replace(containerId, fragment, tag).commit();
    }

    private static final String TAG_TOOLBAR = "toolbar";
    private static final String TAG_CONTENT = "content";

    private JugglerActivity<? extends ScreensManager> activity;
    private List<Screen.Instance> stack = new ArrayList<>();
    private Screen.Instance currentScreenInstance = null;
    private JugglerToolbarFragment toolbarFragment = null;

    public ScreensManager(JugglerActivity<? extends ScreensManager> activity) {
        this.activity = activity;
    }

    protected void showNew(Class<? extends Screen> screen, Screen.Params params) {
        Screen.Instance screenInstance = Screen.Factory.create(screen, params);
        doShowNew(screenInstance);
    }

    protected void showNew(Class<? extends Screen> screen) {
        Screen.Instance screenInstance = Screen.Factory.create(screen);
        doShowNew(screenInstance);
    }

    private void doShowNew(Screen.Instance screenInstance) {
        if (currentScreenInstance != null) {
            Fragment fragment = activity.getSupportFragmentManager().findFragmentByTag(TAG_CONTENT);
            Fragment.SavedState savedState = activity.getSupportFragmentManager().saveFragmentInstanceState(fragment);
            currentScreenInstance.setContentSavedState(savedState);
            stack.add(currentScreenInstance);
        }
        currentScreenInstance = screenInstance;
        doShow(screenInstance, activity);
    }

    protected boolean back() {
        final boolean b;
        if (stack.size() > 0) {
            currentScreenInstance = stack.get(stack.size() - 1);
            stack.remove(stack.size() - 1);
            doShow(currentScreenInstance, activity);
            b = true;
        } else {
            b = false;
        }
        return b;
    }

    private void doShow(Screen.Instance screenInstance, JugglerActivity<? extends ScreensManager> activity) {
        processToolbar(screenInstance, activity);
        processContent(screenInstance, activity);
    }

    public boolean hasBack() {
        return stack.size() > 0;
    }

    private void processToolbar(Screen.Instance screenInstance, JugglerActivity<? extends ScreensManager> activity) {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();

        int toolbarContainerId = activity.getContainerToolbarLayoutId();
        if (toolbarContainerId != 0) {
            JugglerToolbarFragment newToolbarFragment = screenInstance.instanceToolbar();
            JugglerToolbarFragment currentToolbarFragment = (JugglerToolbarFragment) fragmentManager.findFragmentByTag(TAG_TOOLBAR);
            if (currentToolbarFragment == null) {
                if (newToolbarFragment != null) {
                    putToolbarFragmentOnActivity(fragmentManager, toolbarContainerId, screenInstance, newToolbarFragment, TAG_TOOLBAR);
                    toolbarFragment = newToolbarFragment;
                }
            } else {
                if (newToolbarFragment == null) {
                    fragmentManager.beginTransaction().remove(currentToolbarFragment).commit();
                    toolbarFragment = null;
                } else {
                    if (!newToolbarFragment.getClass().equals(currentToolbarFragment.getClass())) {
                        putToolbarFragmentOnActivity(fragmentManager, toolbarContainerId, screenInstance, newToolbarFragment, TAG_TOOLBAR);
                        toolbarFragment = newToolbarFragment;
                    }
                }
            }
        }
    }

    private void processContent(Screen.Instance screenInstance, JugglerActivity<? extends ScreensManager> activity) {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        int contentContainerId = activity.getContainerContentLayoutId();
        if (contentContainerId != 0) {
            JugglerContentFragment contentFragment = screenInstance.instanceContent();
            if (contentFragment != null) {
                putContentFragmentOnActivity(fragmentManager, contentContainerId, screenInstance, contentFragment, TAG_CONTENT);
            }
        }
    }

    public void setTitle(String title) {
        if (toolbarFragment != null) {
            toolbarFragment.setTitle(title);
        }
    }

}
