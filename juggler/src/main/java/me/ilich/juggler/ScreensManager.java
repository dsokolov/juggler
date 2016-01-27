package me.ilich.juggler;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import me.ilich.juggler.old.fragments.JugglerActivity_;
import me.ilich.juggler.old.fragments.Juggler_;
import me.ilich.juggler.old.fragments.Stacks_;
import me.ilich.juggler.old.fragments.content.JugglerContentFragment_;
import me.ilich.juggler.old.fragments.navigation.JugglerNavigationFragment;
import me.ilich.juggler.old.fragments.toolbar.JugglerToolbarFragment;

public abstract class ScreensManager {

    public enum MODE {
        ADD,
        CLEAR,
        DIG,
        GET
    }

    private static final String TAG_TOOLBAR = "toolbar";
    private static final String TAG_CONTENT = "content";
    private static final String TAG_NAVIGATION = "navigation";

    private Juggler_ juggler;
    private Stacks_ stacks = new Stacks_();
    @Nullable
    private Screen.Instance currentScreenInstance = null;
    @Nullable
    private JugglerToolbarFragment toolbarFragment = null;
    @Nullable
    private JugglerContentFragment_ contentFragment = null;
    @Nullable
    private JugglerNavigationFragment navigationFragment = null;

    @Nullable
    public JugglerToolbarFragment getToolbarFragment() {
        return toolbarFragment;
    }

    public void onSaveInstanceState(JugglerActivity_ activity, Bundle outState) {
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
                currentScreenInstance.setContentSavedState(savedState);
            }
            stacks.addCurrent(currentScreenInstance);
            currentScreenInstance = null;
        }
        outState.putSerializable("STACKS", stacks);
    }

    public void onRestore(JugglerActivity_ activity, Bundle savedInstanceState) {
        stacks = (Stacks_) savedInstanceState.getSerializable("STACKS");
        doShow(activity, MODE.GET, null, null);
    }

    public void setJuggler(Juggler_ juggler) {
        this.juggler = juggler;
    }

    public boolean back(JugglerActivity_ activity) {
        final boolean b;
        if (stacks.isCurrentEmpty()) {
            b = false;
        } else {
            doShow(activity, MODE.GET, stacks.getCurrentStackName(), null);
            b = true;
        }
        return b;
    }

    public boolean up(JugglerActivity_ activity) {
        final boolean b;
        if (stacks.isCurrentEmpty()) {
            b = false;
        } else {
            doShow(activity, MODE.GET, stacks.getCurrentStackName(), null);
            b = true;
        }
        return b;
    }

    protected void show(JugglerActivity_ activity, MODE mode, String stackName, @Nullable Class<? extends Screen> screen, @Nullable Screen.Params params) {
        if (screen == null) {
            doShow(activity, mode, stackName, null);
        } else {
            if (params == null) {
                doShow(activity, mode, stackName, Screen.Factory.create(screen));
            } else {
                doShow(activity, mode, stackName, Screen.Factory.create(screen, params));
            }
        }
    }

    private void doShow(JugglerActivity_ activity, MODE mode, String stackName, @Nullable Screen.Instance screenInstance) {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        stacks.setCurrentStack(stackName);
        switch (mode) {
            case ADD:
                if (currentScreenInstance != null) {
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
                        currentScreenInstance.setContentSavedState(savedState);
                    }
                    stacks.addCurrent(currentScreenInstance);
                }
                currentScreenInstance = screenInstance;
                break;
            case CLEAR:
                stacks.clearCurrent();
                currentScreenInstance = screenInstance;
                break;
            case DIG:
                throw new RuntimeException("not implemented");
            case GET:
                currentScreenInstance = stacks.getCurrentLast();
                stacks.removeCurrentLast();
                break;
        }

        showScreenInstance(activity, fragmentManager);
    }

    private void showScreenInstance(JugglerActivity_ activity, FragmentManager fragmentManager) {
        assert currentScreenInstance != null;
        juggler.onBeginNewScreen(currentScreenInstance);

        Screen.FragmentFactory.Bundle bundle = currentScreenInstance.instanceFragments();

        juggler.getLayoutController().init(activity, bundle.getLayoutId());

        FragmentTransaction transaction = fragmentManager.beginTransaction();

        if (juggler.hasToolbarContainer()) {
            JugglerToolbarFragment currentToolbarFragment = (JugglerToolbarFragment) fragmentManager.findFragmentByTag(TAG_TOOLBAR);
            JugglerToolbarFragment newToolbarFragment = bundle.getToolbarFragment();
            if (newToolbarFragment == null) {
                if (currentToolbarFragment != null) {
                    transaction.remove(currentToolbarFragment);
                }
            } else {
                if (currentToolbarFragment == null) {
                    newToolbarFragment.setInitialSavedState(currentScreenInstance.getToolbarSavedState());
                    transaction.replace(juggler.getLayoutController().getContainerToolbarLayoutId(), newToolbarFragment, TAG_TOOLBAR);
                    toolbarFragment = null;
                } else {
                    //TODO не заменять фрагмент если тот же класс. Надо ли? Почему поведение кнопки "назад" не меняется?
/*                if (currentToolbarFragment.getClass().equals(newToolbarFragment.getClass())) {
                    int options = newToolbarFragment.getOption();
                    currentToolbarFragment.setOptions(options);
                } else {*/
                    newToolbarFragment.setInitialSavedState(currentScreenInstance.getToolbarSavedState());
                    transaction.replace(juggler.getLayoutController().getContainerToolbarLayoutId(), newToolbarFragment, TAG_TOOLBAR);
                    toolbarFragment = null;
/*                }*/
                }
            }
        }

        JugglerNavigationFragment currentNavigationFragment = (JugglerNavigationFragment) fragmentManager.findFragmentByTag(TAG_NAVIGATION);
        JugglerNavigationFragment newNavigationFragment = bundle.getNavigationFragment();
        if (newNavigationFragment == null) {
            if (currentNavigationFragment != null) {
                transaction.remove(currentNavigationFragment);
            }
        } else {
            newNavigationFragment.setInitialSavedState(currentScreenInstance.getNavigationSavedState());
            transaction.replace(juggler.getLayoutController().getContainerNavigationLayoutId(), newNavigationFragment, TAG_NAVIGATION);
            navigationFragment = null;
        }

        JugglerContentFragment_ currentContentFragment = (JugglerContentFragment_) fragmentManager.findFragmentByTag(TAG_CONTENT);
        JugglerContentFragment_ newContentFragment = bundle.getContentFragment();
        if (newContentFragment == null) {
            if (currentContentFragment != null) {
                transaction.remove(currentContentFragment);
            }
        } else {
            Fragment.SavedState savedState = currentScreenInstance.getContentSavedState();
            newContentFragment.setInitialSavedState(savedState);
            transaction.replace(juggler.getLayoutController().getContainerContentLayoutId(), newContentFragment, TAG_CONTENT);
            contentFragment = null;
        }

        transaction.commit();
        juggler.onEndNewScreen(currentScreenInstance);
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

    public void onContentAttached(JugglerContentFragment_ fragment) {
        Log.v("Sokolov", "content attached " + fragment);
        contentFragment = fragment;
    }

    public void onContentDetached(JugglerContentFragment_ fragment) {
        Log.v("Sokolov", "content detached " + fragment);
        contentFragment = null;
    }

    public void onToolbarAttached(JugglerToolbarFragment fragment) {
        Log.v("Sokolov", "toolbar attached " + fragment);
        toolbarFragment = fragment;
        initNavigationWithToolbar();
    }

    public void onToolbarDetached(JugglerToolbarFragment fragment) {
        Log.v("Sokolov", "toolbar detached " + fragment);
        toolbarFragment = null;
    }

    public void onNavigationAttached(JugglerNavigationFragment fragment) {
        Log.v("Sokolov", "navigation attached " + fragment);
        navigationFragment = fragment;
        initNavigationWithToolbar();
    }

    public void onNavigationDetached(JugglerNavigationFragment fragment) {
        Log.v("Sokolov", "navigation detached " + fragment);
        DrawerLayout drawerLayout = juggler.getLayoutController().getDrawerLayout();
        if (navigationFragment != null) {
            navigationFragment.deinit(drawerLayout);
            navigationFragment = null;
        }
    }

    private void initNavigationWithToolbar() {
        if (navigationFragment != null && toolbarFragment != null) {
            DrawerLayout drawerLayout = juggler.getLayoutController().getDrawerLayout();
            Toolbar toolbar = toolbarFragment.getToolbar();
            navigationFragment.init(drawerLayout, toolbar);
        }
    }


    public void deattachAll(JugglerActivity_ activity) {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (toolbarFragment != null) {
            transaction.remove(toolbarFragment);
        }
        if (navigationFragment != null) {
            transaction.remove(navigationFragment);
        }
        if (contentFragment != null) {
            transaction.remove(contentFragment);
        }
        transaction.commit();
    }

    public Screen.Instance getCurrentScreen() {
        return currentScreenInstance;
    }

}
