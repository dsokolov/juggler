package me.ilich.juggler.states;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;

import java.io.Serializable;

import me.ilich.juggler.Juggler;
import me.ilich.juggler.R;
import me.ilich.juggler.Transition;
import me.ilich.juggler.grid.Cell;
import me.ilich.juggler.grid.Grid;
import me.ilich.juggler.gui.JugglerActivity;
import me.ilich.juggler.gui.JugglerFragment;

public abstract class State<P extends State.Params> implements Serializable {

    private static final int NOT_SET = -1;

    @Nullable
    private final P params;
    private Grid grid;
    @Nullable
    private Transition backTransition;
    @Nullable
    private Transition upTransition;

    public State(Grid grid, @Nullable P params) {
        if (grid == null) {
            throw new NullPointerException("grid");
        }
        this.params = params;
        this.grid = grid;
    }

    @NonNull
    public final Grid getGrid() {
        return grid;
    }

    @Nullable
    public String getTitle(Context context, P params) {
        return null;
    }

    public Drawable getUpNavigationIcon(Context context, P params) {
        return null;
    }

    @CallSuper
    public void onActivate(JugglerActivity activity) {
        processTitle(activity);
        processUpIcon(activity);
    }

    private void processUpIcon(final JugglerActivity activity) {
        final android.support.v7.app.ActionBarDrawerToggle.Delegate delegate = activity.getDrawerToggleDelegate();
        if (delegate != null) {
            Drawable upIcon = getUpNavigationIcon(activity, params);
            delegate.setActionBarUpIndicator(upIcon, R.string.empty);
        }
    }

    protected void processTitle(JugglerActivity activity) {
        String title = getTitle(activity, params);
        //me.ilich.juggler.Log.v(getClass(), "processTitle " + title);
        if (!TextUtils.isEmpty(title)) {
            activity.setTitle(title);
        }
    }

    @CallSuper
    public void onDeactivate(JugglerActivity activity) {
    }

    @NonNull
    public final JugglerFragment createFragment(int cellType) {
        JugglerFragment f = onCreateFragment(cellType, params);
        if (f == null) {
            throw new NullPointerException("Fragment " + cellType + " is null");
        }
        f.setTargetCellType(cellType);
        return f;
    }

    public void onFragmentTransitionBeforeCommit(FragmentTransaction fragmentTransaction) {

    }

    protected abstract JugglerFragment onCreateFragment(int cellType, P params);

    @Override
    public String toString() {
        return getClass().getName() + " (" + params + ")";
    }

    @Nullable
    public final Transition getBackTransition() {
        return backTransition;
    }

    public void setBackTransition(@Nullable Transition transition) {
        backTransition = transition;
    }

    @Nullable
    public Transition getUpTransition() {
        return upTransition;
    }

    public void setUpTransition(@Nullable Transition transition) {
        upTransition = transition;
    }

    public void onPostCreate(JugglerActivity activity, Bundle savedInstanceState) {

    }

    @Nullable
    public String getTag() {
        return null;
    }

    /**
     * @param activity An JugglerActivity
     * @return true if any juggler fragment process back press
     * false if not
     */
    public boolean onBackPressed(JugglerActivity activity) {
        boolean b = false;
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        for (Cell cell : grid.getCells()) {
            int containerId = cell.getContainerId();
            Fragment fragment = fragmentManager.findFragmentById(containerId);
            if (fragment != null && fragment instanceof JugglerFragment) {
                boolean processed = ((JugglerFragment) fragment).onBackPressed();
                if (processed) {
                    b = true;
                    break;
                }
            }
        }
        return b;
    }

    public boolean onUpPressed(JugglerActivity activity) {
        boolean b = false;
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        for (Cell cell : grid.getCells()) {
            int containerId = cell.getContainerId();
            Fragment fragment = fragmentManager.findFragmentById(containerId);
            if (fragment != null && fragment instanceof JugglerFragment) {
                boolean processed = ((JugglerFragment) fragment).onUpPressed();
                if (processed) {
                    b = true;
                    break;
                }
            }
        }
        return b;
    }

    public static class Params implements Serializable {

        @Override
        public String toString() {
            return getClass().getName();
        }

    }

}
