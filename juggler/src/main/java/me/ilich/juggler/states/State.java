package me.ilich.juggler.states;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
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
    @Nullable
    private List<SharedElement> sharedElements;
    @Nullable
    private Bundle activityOptions;

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

    protected Drawable getUpNavigationIcon(Context context, P params) {
        return null;
    }

    public final Drawable getUpNavigationIcon(Context context) {
        return getUpNavigationIcon(context, params);
    }

    @CallSuper
    public void onActivate(JugglerActivity activity) {
        processTitle(activity);
    }

    protected void processTitle(JugglerActivity activity) {
        String title = getTitle(activity, params);
        if (!TextUtils.isEmpty(title)) {
            activity.setTitle(title);
        }
    }

    public void addSharedElement(View view, String transitionName) {
        if (sharedElements == null) sharedElements = new ArrayList<>();
        sharedElements.add(new SharedElement(view, transitionName));
    }

    public void addActivityOptions(Bundle activityOptions) {
        this.activityOptions = activityOptions;
    }

    public void deactivateNotSerializable() {
        sharedElements = null;
        activityOptions = null;
    }

    @Nullable
    public List<SharedElement> getSharedElements() {
        return sharedElements;
    }

    @Nullable
    public Bundle getActivityOptions() {
        return activityOptions;
    }

    @CallSuper
    public void onDeactivate(JugglerActivity activity) {
    }

    @Nullable
    public final JugglerFragment convertFragment(int cellType, @Nullable JugglerFragment fragment) {
        JugglerFragment f = onConvertFragment(cellType, params, fragment);
        if (f != null) {
            f.setTargetCellType(cellType);
            f.setState(this);
        }
        return f;
    }

    protected abstract JugglerFragment onConvertFragment(int cellType, P params, @Nullable JugglerFragment fragment);

    public void onFragmentTransitionBeforeCommit(FragmentTransaction fragmentTransaction) {

    }

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
    public final Transition getUpTransition() {
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

    @Nullable
    public P getParams() {
        return params;
    }

    @ActionBar.DisplayOptions
    public int getToolbarDisplayOptions() {
        return 0;
    }

    public static class Params implements Serializable {

        @Override
        public String toString() {
            return getClass().getName();
        }

    }

    public static class SharedElement implements Serializable {
        private View view;
        private String transitionName;

        public SharedElement(View view, String transitionName) {
            this.view = view;
            this.transitionName = transitionName;
        }

        public View getView() {
            return view;
        }

        public String getTransitionName() {
            return transitionName;
        }

        public void clearView() {
            view = null;
        }
    }

}
