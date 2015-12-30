package me.ilich.juggler;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import me.ilich.juggler.fragments.content.JugglerContentFragment;
import me.ilich.juggler.fragments.navigation.JugglerNavigationFragment;
import me.ilich.juggler.fragments.toolbar.JugglerToolbarFragment;

public abstract class ScreensManager implements Screen {

    private static void putToolbarFragmentOnActivity(FragmentManager fragmentManager, @IdRes int containerId, Screen.Instance screenInstance, JugglerToolbarFragment fragment, String tag) {
        Fragment.SavedState savedState = screenInstance.getToolbarSavedState();
        fragment.setInitialSavedState(savedState);
        fragmentManager.beginTransaction().replace(containerId, fragment, tag).commit();
    }

    private static void putNavigationFragmentOnActivity(FragmentManager fragmentManager, @IdRes int containerId, Screen.Instance screenInstance, JugglerNavigationFragment fragment, String tag) {
        Fragment.SavedState savedState = screenInstance.getNavigationSavedState();
        fragment.setInitialSavedState(savedState);
        fragmentManager.beginTransaction().replace(containerId, fragment, tag).commit();
    }

    private static void putContentFragmentOnActivity(FragmentManager fragmentManager, @IdRes int containerId, Screen.Instance screenInstance, JugglerContentFragment fragment, String tag) {
        Fragment.SavedState savedState = screenInstance.getContentSavedState();
        fragment.setInitialSavedState(savedState);
        fragmentManager.beginTransaction().replace(containerId, fragment, tag).commit();
    }

    private static JugglerToolbarFragment processToolbar(Screen.Instance screenInstance, JugglerActivity<? extends ScreensManager> activity) {
        final JugglerToolbarFragment result;
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        int toolbarContainerId = activity.getContainerToolbarLayoutId();
        if (toolbarContainerId != 0) {
            JugglerToolbarFragment newToolbarFragment = screenInstance.instanceToolbar();
            JugglerToolbarFragment currentToolbarFragment = (JugglerToolbarFragment) fragmentManager.findFragmentByTag(TAG_TOOLBAR);
            if (currentToolbarFragment == null) {
                if (newToolbarFragment == null) {
                    result = null;
                } else {
                    putToolbarFragmentOnActivity(fragmentManager, toolbarContainerId, screenInstance, newToolbarFragment, TAG_TOOLBAR);
                    result = newToolbarFragment;
                }
            } else {
                if (newToolbarFragment == null) {
                    fragmentManager.beginTransaction().remove(currentToolbarFragment).commit();
                    result = currentToolbarFragment;
                } else {
                    if (newToolbarFragment.getClass().equals(currentToolbarFragment.getClass())) {
                        result = currentToolbarFragment;
                    } else {
                        putToolbarFragmentOnActivity(fragmentManager, toolbarContainerId, screenInstance, newToolbarFragment, TAG_TOOLBAR);
                        result = newToolbarFragment;
                    }
                }
            }
        } else {
            result = null;
        }
        if (result == null) {
            activity.hideToolbarContainer();
        } else {
            activity.showToolbarContainer();
        }
        return result;
    }

    private static JugglerContentFragment processContent(Screen.Instance screenInstance, JugglerActivity<? extends ScreensManager> activity) {
        final JugglerContentFragment result;
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        int contentContainerId = activity.getContainerContentLayoutId();
        if (contentContainerId != 0) {
            JugglerContentFragment contentFragment = screenInstance.instanceContent();
            if (contentFragment != null) {
                putContentFragmentOnActivity(fragmentManager, contentContainerId, screenInstance, contentFragment, TAG_CONTENT);
                result = contentFragment;
            } else {
                result = null;
            }
        } else {
            result = null;
        }
        return result;
    }

    private static JugglerNavigationFragment processNavigation(Screen.Instance screenInstance, JugglerActivity<? extends ScreensManager> activity) {
        final JugglerNavigationFragment result;
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        int navigationContainerId = activity.getContainerNavigationLayoutId();
        JugglerNavigationFragment currentNavigationFragment = (JugglerNavigationFragment) fragmentManager.findFragmentByTag(TAG_NAVIGATION);
        if (navigationContainerId == 0) {
            result = null;
        } else {
            JugglerNavigationFragment navigationFragment = screenInstance.instanceNavigation();
            if (navigationFragment == null) {
                if (currentNavigationFragment != null) {
                    fragmentManager.beginTransaction().remove(currentNavigationFragment).commit();
                }
                result = null;
            } else {
                putNavigationFragmentOnActivity(fragmentManager, navigationContainerId, screenInstance, navigationFragment, TAG_NAVIGATION);
                result = navigationFragment;
            }
        }
        if (result == null) {
            activity.hideNavigationContainer();
        } else {
            activity.showNavigationContainer();
        }
        return result;
    }

    private static final String TAG_TOOLBAR = "toolbar";
    private static final String TAG_CONTENT = "content";
    private static final String TAG_NAVIGATION = "navagation";

    private JugglerActivity<? extends ScreensManager> activity;
    private List<Screen.Instance> stack = new ArrayList<>();
    private Screen.Instance currentScreenInstance = null;
    private JugglerToolbarFragment toolbarFragment = null;
    private JugglerContentFragment contentFragment = null;
    private JugglerNavigationFragment navigationFragment = null;

    public ScreensManager(JugglerActivity<? extends ScreensManager> activity) {
        this.activity = activity;
    }

    @Override
    public boolean back() {
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

    @Override
    public boolean up() {
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

    protected void showAddStack(Class<? extends Screen> screen, Screen.Params params) {
        Screen.Instance screenInstance = Screen.Factory.create(screen, params);
        doShowAddStack(screenInstance);
    }

    protected void showAddStack(Class<? extends Screen> screen) {
        Screen.Instance screenInstance = Screen.Factory.create(screen);
        doShowAddStack(screenInstance);
    }

    protected void showClearStack(Class<? extends Screen> screen, Screen.Params params) {
        stack.clear();
        currentScreenInstance = Screen.Factory.create(screen, params);
        doShow(currentScreenInstance, activity);
    }

    protected void showClearStack(Class<? extends Screen> screen) {
        stack.clear();
        currentScreenInstance = Screen.Factory.create(screen);
        doShow(currentScreenInstance, activity);
    }

    private void doShowAddStack(Screen.Instance screenInstance) {
        if (currentScreenInstance != null) {
            FragmentManager fragmentManager = activity.getSupportFragmentManager();
            Fragment contentFragment = fragmentManager.findFragmentByTag(TAG_CONTENT);
            if (contentFragment == null) {
                currentScreenInstance.setContentSavedState(null);
            } else {
                Fragment.SavedState contentSavedState = fragmentManager.saveFragmentInstanceState(contentFragment);
                currentScreenInstance.setContentSavedState(contentSavedState);
            }
            Fragment toolbarFragment = fragmentManager.findFragmentByTag(TAG_TOOLBAR);
            if (toolbarFragment == null) {
                currentScreenInstance.setToolbarSavedState(null);
            } else {
                Fragment.SavedState toolbarSavedState = fragmentManager.saveFragmentInstanceState(toolbarFragment);
                currentScreenInstance.setToolbarSavedState(toolbarSavedState);
            }
            Fragment navigationFragment = fragmentManager.findFragmentByTag(TAG_NAVIGATION);
            if (navigationFragment == null) {
                currentScreenInstance.setNavigationSavedState(null);
            } else {
                Fragment.SavedState toolbarSavedState = fragmentManager.saveFragmentInstanceState(navigationFragment);
                currentScreenInstance.setNavigationSavedState(toolbarSavedState);
            }
            stack.add(currentScreenInstance);
        }
        currentScreenInstance = screenInstance;
        doShow(screenInstance, activity);
    }

    private void doShow(Screen.Instance screenInstance, JugglerActivity<? extends ScreensManager> activity) {
        toolbarFragment = processToolbar(screenInstance, activity);
        contentFragment = processContent(screenInstance, activity);
        navigationFragment = processNavigation(screenInstance, activity);
    }

    public void setToolbarMode(JugglerToolbarFragment.Mode mode) {
        if (toolbarFragment != null) {
            toolbarFragment.setMode(mode);
        }
    }

    public void setToolbarOptions(@ActionBar.DisplayOptions int options) {
        if (toolbarFragment != null) {
            toolbarFragment.setOptions(options);
        }
    }

    public int getToolbarOptions() {
        final int r;
        if (toolbarFragment != null) {
            r = toolbarFragment.getOption();
        } else {
            r = 0;
        }
        return r;
    }
}
