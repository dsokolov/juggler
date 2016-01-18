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

import me.ilich.juggler.activity.JugglerActivity;
import me.ilich.juggler.fragments.content.JugglerContentFragment;
import me.ilich.juggler.fragments.navigation.JugglerNavigationFragment;
import me.ilich.juggler.fragments.toolbar.JugglerToolbarFragment;

public abstract class ScreensManager implements Screen {

    public enum MODE {
        ADD,
        CLEAR,
        DIG,
        GET
    }

    private static final String TAG_TOOLBAR = "toolbar";
    private static final String TAG_CONTENT = "content";
    private static final String TAG_NAVIGATION = "navigation";

    private JugglerActivity<? extends ScreensManager> activity;
    private Juggler juggler;
    private Stacks stacks = new Stacks();
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

    @Nullable
    public JugglerToolbarFragment getToolbarFragment() {
        return toolbarFragment;
    }

    public void onSaveInstanceState(Bundle outState) {
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

    public void onRestore(Bundle savedInstanceState) {
        stacks = (Stacks) savedInstanceState.getSerializable("STACKS");
        doShow(MODE.GET, null, null);
    }

    public void setJuggler(Juggler juggler) {
        this.juggler = juggler;
    }

    public abstract void onFirstScreen();

    public boolean back() {
        final boolean b;
        if (stacks.isCurrentEmpty()) {
            b = false;
        } else {
            doShow(MODE.GET, stacks.getCurrentStackName(), null);
            b = true;
        }
        return b;
    }

    public boolean up() {
        final boolean b;
        if (stacks.isCurrentEmpty()) {
            b = false;
        } else {
            doShow(MODE.GET, stacks.getCurrentStackName(), null);
            b = true;
        }
        return b;
    }

    protected void show(MODE mode, String stackName, @Nullable Class<? extends Screen> screen, @Nullable Screen.Params params) {
        if (screen == null) {
            doShow(mode, stackName, null);
        } else {
            if (params == null) {
                doShow(mode, stackName, Screen.Factory.create(screen));
            } else {
                doShow(mode, stackName, Screen.Factory.create(screen, params));
            }
        }
    }

    private void doShow(MODE mode, String stackName, @Nullable Screen.Instance screenInstance) {
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

        showScreenInstance(fragmentManager);
    }

    private void showScreenInstance(FragmentManager fragmentManager) {
        assert currentScreenInstance != null;
        juggler.onBeginNewScreen(currentScreenInstance);

        FragmentFactory.Bundle bundle = currentScreenInstance.instanceFragments();

        juggler.getLayoutController().init(bundle.getLayoutId());

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

        JugglerContentFragment currentContentFragment = (JugglerContentFragment) fragmentManager.findFragmentByTag(TAG_CONTENT);
        JugglerContentFragment newContentFragment = bundle.getContentFragment();
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

    public void onContentAttached(JugglerContentFragment fragment) {
        Log.v("Sokolov", "content attached " + fragment);
        contentFragment = fragment;
    }

    public void onContentDetached(JugglerContentFragment fragment) {
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


    public void deattachAll() {
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

    public Instance getCurrentScreen() {
        return currentScreenInstance;
    }

}
