package me.ilich.juggler;

import android.support.v4.app.FragmentTransaction;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import me.ilich.juggler.fragments.content.JugglerContentFragment;
import me.ilich.juggler.fragments.navigation.JugglerNavigationFragment;
import me.ilich.juggler.fragments.toolbar.JugglerToolbarFragment;

public abstract class ScreensManager implements Screen {

    private static final String TAG_TOOLBAR = "toolbar";
    private static final String TAG_CONTENT = "content";
    private static final String TAG_NAVIGATION = "navagation";

    private JugglerActivity<? extends ScreensManager> activity;
    private List<Screen.Instance> stack = new ArrayList<>();
    @Nullable
    private Screen.Instance currentScreenInstance = null;
    @Nullable
    private JugglerToolbarFragment toolbarFragment = null;
    @Nullable
    private JugglerContentFragment contentFragment = null;
    @Nullable
    private JugglerNavigationFragment navigationFragment = null;

    public ScreensManager(JugglerActivity<? extends ScreensManager> activity) {
        this.activity = activity;
    }

    public boolean back() {
        final boolean b;
        if (stack.size() > 0) {
            show(SHOW_MODE.GET, null);
            b = true;
        } else {
            b = false;
        }
        return b;
    }

    public boolean up() {
        final boolean b;
        if (stack.size() > 0) {
            show(SHOW_MODE.GET, null);
            b = true;
        } else {
            b = false;
        }
        return b;
    }

    public enum SHOW_MODE {
        ADD,
        CLEAR,
        DIG,
        GET
    }

    protected void show(SHOW_MODE mode, @Nullable Class<? extends Screen> screen) {
        if (screen == null) {
            doShow(mode, null);
        } else {
            doShow(mode, Screen.Factory.create(screen));
        }
    }

    protected void show(SHOW_MODE mode, Class<? extends Screen> screen, Screen.Params params) {
        doShow(mode, Screen.Factory.create(screen, params));
    }

    private void doShow(SHOW_MODE mode, @Nullable Screen.Instance screenInstance) {
        switch (mode) {
            case ADD:
                if (currentScreenInstance != null) {
                    FragmentManager fragmentManager = activity.getSupportFragmentManager();
                    if (toolbarFragment != null) {
                        Fragment.SavedState savedState = fragmentManager.saveFragmentInstanceState(toolbarFragment);
                        currentScreenInstance.setToolbarSavedState(savedState);
                    }
                    if (navigationFragment != null) {
                        Fragment.SavedState savedState = fragmentManager.saveFragmentInstanceState(navigationFragment);
                        currentScreenInstance.setNavigationSavedState(savedState);
                    }
                    if (contentFragment != null) {
                        Fragment.SavedState savedState = fragmentManager.saveFragmentInstanceState(contentFragment);
                        Log.v("Sokolov", "set content saved state " + savedState);
                        currentScreenInstance.setContentSavedState(savedState);
                    }
                    stack.add(currentScreenInstance);
                }
                currentScreenInstance = screenInstance;
                break;
            case CLEAR:
                stack.clear();
                currentScreenInstance = screenInstance;
                break;
            case DIG:
                //TODO
                break;
            case GET:
                currentScreenInstance = stack.get(stack.size() - 1);
                stack.remove(stack.size() - 1);
                break;
        }

        showScreenInstance();
    }

    private void showScreenInstance() {
        assert currentScreenInstance != null;
        activity.getJuggler().onBeginNewScreen(currentScreenInstance);

        FragmentFactory.Bundle bundle = currentScreenInstance.instanceFragments();

        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        JugglerToolbarFragment currentToolbarFragment = (JugglerToolbarFragment) fragmentManager.findFragmentByTag(TAG_TOOLBAR);
        JugglerToolbarFragment newToolbarFragment = bundle.getToolbarFragment();
        if (newToolbarFragment == null) {
            if (currentToolbarFragment != null) {
                transaction.remove(currentToolbarFragment);
            }
        } else {
            newToolbarFragment.setInitialSavedState(currentScreenInstance.getToolbarSavedState());
            transaction.replace(activity.getContainerToolbarLayoutId(), newToolbarFragment, TAG_TOOLBAR);
            toolbarFragment = null;
        }

        JugglerNavigationFragment currentNavigationFragment = (JugglerNavigationFragment) fragmentManager.findFragmentByTag(TAG_NAVIGATION);
        JugglerNavigationFragment newNavigationFragment = bundle.getNavigationFragment();
        if (newNavigationFragment == null) {
            if (currentNavigationFragment != null) {
                transaction.remove(currentNavigationFragment);
            }
        } else {
            newNavigationFragment.setInitialSavedState(currentScreenInstance.getNavigationSavedState());
            transaction.replace(activity.getContainerNavigationLayoutId(), newNavigationFragment, TAG_NAVIGATION);
            navigationFragment = null;
        }

        JugglerContentFragment currentContentFragment = (JugglerContentFragment) fragmentManager.findFragmentByTag(TAG_CONTENT);
        JugglerContentFragment newContentFragment = bundle.getContentFragment();
        if (newContentFragment == null) {
            if (currentContentFragment != null) {
                transaction.remove(currentContentFragment);
            }
        } else {
            Fragment.SavedState savedState = currentScreenInstance.getContentSavedState();
            Log.v("Sokolov", "get content saved state " + savedState);
            newContentFragment.setInitialSavedState(savedState);
            transaction.replace(activity.getContainerContentLayoutId(), newContentFragment, TAG_CONTENT);
            contentFragment = null;
        }

        transaction.commit();
        activity.getJuggler().onEndNewScreen(currentScreenInstance);
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

    public void onContentAttached(JugglerContentFragment fragment) {
        Log.v("Sokolov", "content attached " + fragment);
        contentFragment = fragment;
    }

    public void onContentDetached(JugglerContentFragment fragment) {
        Log.v("Sokolov", "content detached " + fragment);
        contentFragment = null;
    }

    public void onToolbarAttached(JugglerToolbarFragment fragment) {
        toolbarFragment = fragment;
        initNavigationWithToolbar();
    }

    public void onToolbarDetached(JugglerToolbarFragment fragment) {
        toolbarFragment = null;
    }

    public void onNavigationAttached(JugglerNavigationFragment fragment) {
        navigationFragment = fragment;
        initNavigationWithToolbar();
    }

    public void onNavigationDetached(JugglerNavigationFragment fragment) {
        DrawerLayout drawerLayout = activity.getDrawerLayout();
        if (navigationFragment != null) {
            navigationFragment.deinit(drawerLayout);
            navigationFragment = null;
        }
    }

    private void initNavigationWithToolbar() {
        if (navigationFragment != null && toolbarFragment != null) {
            DrawerLayout drawerLayout = activity.getDrawerLayout();
            Toolbar toolbar = toolbarFragment.getToolbar();
            navigationFragment.init(drawerLayout, toolbar);
        }
    }

}
