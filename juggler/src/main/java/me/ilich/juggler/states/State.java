package me.ilich.juggler.states;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.view.MenuItem;

import me.ilich.juggler.gui.JugglerActivity;
import me.ilich.juggler.gui.JugglerFragment;
import me.ilich.juggler.Transition;
import me.ilich.juggler.grid.Grid;

public abstract class State<P extends State.Params> {

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

    public String getTitle() {
        return null;
    }

    @StringRes
    public int getTitleRes() {
        return 0;
    }

    @CallSuper
    public void onActivate(JugglerActivity activity) {
        String title = getTitle();
        if (TextUtils.isEmpty(title)) {
            int titleRes = getTitleRes();
            if (titleRes == 0) {
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

    public JugglerFragment createFragment(int cellType) {
        return onCreateFragment(cellType, params);
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

    public static class Params {

        @Override
        public String toString() {
            return getClass().getName();
        }

    }

}
