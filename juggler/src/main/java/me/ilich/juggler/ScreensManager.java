package me.ilich.juggler;

import android.support.v4.app.FragmentManager;

import java.util.ArrayList;
import java.util.List;

public abstract class ScreensManager {

    private static final String TAG_TOOLBAR = "toolbar";
    private static final String TAG_CONTENT = "content";

    private List<Screen.Instance> stack = new ArrayList<>();

    public final void init() {
        onInit();
    }

    protected abstract void onInit();

    public void show(Screen.Instance screenInstance, JugglerActivity activity) {
        processToolbar(screenInstance, activity);
        processContent(screenInstance, activity);
    }

    private void processToolbar(Screen.Instance screenInstance, JugglerActivity activity) {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();

        int toolbarContainerId = activity.getContainerToolbarLayoutId();
        if (toolbarContainerId != 0) {
            JugglerToolbarFragment newToolbarFragment = screenInstance.instanceToolbar();
            JugglerToolbarFragment currentToolbarFragment = (JugglerToolbarFragment) fragmentManager.findFragmentByTag(TAG_TOOLBAR);
            if (currentToolbarFragment == null) {
                if (newToolbarFragment != null) {
                    fragmentManager.
                            beginTransaction().
                            replace(toolbarContainerId, newToolbarFragment, TAG_TOOLBAR).
                            disallowAddToBackStack().
                            commit();
                }
            } else {
                if (newToolbarFragment == null) {
                    fragmentManager.beginTransaction().remove(currentToolbarFragment).commit();
                } else {
                    if (!newToolbarFragment.getClass().equals(currentToolbarFragment.getClass())) {
                        fragmentManager.
                                beginTransaction().
                                replace(toolbarContainerId, newToolbarFragment, TAG_TOOLBAR).
                                disallowAddToBackStack().
                                commit();
                    }
                }
            }
        }
    }

    private void processContent(Screen.Instance screenInstance, JugglerActivity activity) {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        int contentContainerId = activity.getContainerContentLayoutId();
        if (contentContainerId != 0) {
            JugglerContentFragment contentFragment = screenInstance.instanceContent();
            if (contentFragment != null) {
                fragmentManager.
                        beginTransaction().
                        replace(contentContainerId, contentFragment, TAG_CONTENT).
                        disallowAddToBackStack().
                        commit();
            }
        }
    }

}
