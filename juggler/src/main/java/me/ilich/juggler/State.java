package me.ilich.juggler;

import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.text.TextUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.ilich.juggler.grid.CellType;
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

    public JugglerFragment createFragment(CellType cellType) {
        return onCreateFragment(cellType, params);
    }

    protected abstract JugglerFragment onCreateFragment(CellType cellType, P params);

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

    public static class Params {

        @Override
        public String toString() {
            return getClass().getName();
        }

    }

}
