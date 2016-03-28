package me.ilich.juggler.states;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;

import me.ilich.juggler.Transition;
import me.ilich.juggler.grid.Cell;
import me.ilich.juggler.grid.Grid;
import me.ilich.juggler.gui.JugglerActivity;
import me.ilich.juggler.gui.JugglerFragment;

public abstract class State<P extends State.Params> {

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

    public final Grid getGrid() {
        return grid;
    }

    @Nullable
    public String getTitle(Context context, P params) {
        return null;
    }

    @StringRes
    public int getTitleRes(Context context, P params) {
        return NOT_SET;
    }

    @CallSuper
    public void onActivate(JugglerActivity activity) {
        processTitle(activity);
    }

    protected void processTitle(JugglerActivity activity) {
        String title = getTitle(activity, params);
        if (TextUtils.isEmpty(title)) {
            int titleRes = getTitleRes(activity, params);
            if (titleRes == NOT_SET) {
                activity.setTitle(null);
            } else {
                activity.setTitle(titleRes);
            }
        } else {
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
        return f;
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
     *
     * @param activity
     * An JugglerActivity
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

    public static class Params {

        @Override
        public String toString() {
            return getClass().getName();
        }

    }

}
