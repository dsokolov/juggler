package me.ilich.juggler;

import android.support.annotation.IdRes;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;

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
                if (newToolbarFragment != null) {
                    putToolbarFragmentOnActivity(fragmentManager, toolbarContainerId, screenInstance, newToolbarFragment, TAG_TOOLBAR);
                    result = newToolbarFragment;
                } else {
                    result = null;
                }
            } else {
                if (newToolbarFragment == null) {
                    fragmentManager.beginTransaction().remove(currentToolbarFragment).commit();
                    result = null;
                } else {
                    if (!newToolbarFragment.getClass().equals(currentToolbarFragment.getClass())) {
                        putToolbarFragmentOnActivity(fragmentManager, toolbarContainerId, screenInstance, newToolbarFragment, TAG_TOOLBAR);
                        result = newToolbarFragment;
                    } else {
                        result = null;
                    }
                }
            }
        } else {
            result = null;
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
        int navagationContainerId = activity.getContainerNavigationLayoutId();
        JugglerNavigationFragment currentNavigationFragment = (JugglerNavigationFragment) fragmentManager.findFragmentByTag(TAG_NAVIGATION);
        if (navagationContainerId != 0) {
            JugglerNavigationFragment navigationFragment = screenInstance.instanceNavigation();
            if (navigationFragment != null) {
                putNavigationFragmentOnActivity(fragmentManager, navagationContainerId, screenInstance, navigationFragment, TAG_NAVIGATION);
                result = navigationFragment;
            } else {
                if (currentNavigationFragment != null) {
                    fragmentManager.beginTransaction().remove(currentNavigationFragment).commit();
                }
                result = null;
            }
        } else {
            result = null;
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
            Fragment.SavedState contentSavedState = fragmentManager.saveFragmentInstanceState(contentFragment);
            currentScreenInstance.setContentSavedState(contentSavedState);
            Fragment toolbarFragment = fragmentManager.findFragmentByTag(TAG_TOOLBAR);
            Fragment.SavedState toolbarSavedState = fragmentManager.saveFragmentInstanceState(toolbarFragment);
            currentScreenInstance.setToolbarSavedState(toolbarSavedState);
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

    void setTitle(String title) {
        if (toolbarFragment != null) {
            toolbarFragment.setTitle(title);
        }
    }

    void setTitle(@StringRes int title) {
        if (toolbarFragment != null) {
            String s = activity.getString(title);
            toolbarFragment.setTitle(s);
        }
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
