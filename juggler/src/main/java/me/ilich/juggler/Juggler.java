package me.ilich.juggler;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicBoolean;

import me.ilich.juggler.change.Add;
import me.ilich.juggler.change.Item;
import me.ilich.juggler.change.Remove;
import me.ilich.juggler.change.StateChanger;
import me.ilich.juggler.grid.Cell;
import me.ilich.juggler.gui.JugglerActivity;
import me.ilich.juggler.states.State;

public class Juggler implements Navigable, Serializable {

    public static final String TAG = "Juggler";
    private static final int NOT_SET = -1;

    private StateChanger stateChanger = new StateChanger();
    private StateHolder currentStateHolder = new StateHolder();
    @LayoutRes
    private int layoutId = NOT_SET;
    private transient JugglerActivity activity = null;
    private transient FragmentManager.OnBackStackChangedListener onBackStackChangedListener = null;

    @Override
    public boolean backState() {
        if (activity == null) {
            throw new NullPointerException("activity == null");
        }
        final boolean b;
        State state = currentStateHolder.get();
        if (state == null) {
            b = false;
        } else {
            Transition transition = state.getBackTransition();
            if (transition == null) {
                b = false;
            } else {
                State st = transition.execute(activity, stateChanger);
                currentStateHolder.set(st);
                b = currentStateHolder.get() != null;
            }
        }
        return b;
    }

    @Override
    public boolean upState() {
        if (activity == null) {
            throw new NullPointerException("activity == null");
        }
        final boolean b;
        State state = currentStateHolder.get();
        if (state == null) {
            b = false;
        } else {
            Transition transition = state.getUpTransition();
            if (transition == null) {
                b = false;
            } else {
                state = transition.execute(activity, stateChanger);
                currentStateHolder.set(state);
                b = state != null;
            }
        }
        return b;
    }

/*    @Override
    public void linearState(State state, TargetBound... targetBounds) {
        doState(null, Add.linear(state, targetBounds));
    }

    @Override
    public void linearState(State state, @Nullable String tag) {
        doState(null, Add.linear(state, tag));
    }

    @Override
    public void deeperState(State state, TargetBound... targetBounds) {
        doState(null, Add.deeper(state, targetBounds));
    }

    @Override
    public void deeperState(State state, String tag) {
        doState(null, Add.deeper(state, tag));
    }

    @Override
    public void clearState(State state) {
        doState(Remove.all(), Add.deeper(state));
    }

    @Override
    public void clearState(State state, String tag) {
        doState(Remove.all(), Add.deeper(state, tag));
    }

    @Override
    public void dig(String tag) {
        doState(Remove.dig(tag), null);
    }

    @Override
    public void digLinearState(String digTag, State state) {
        doState(Remove.dig(digTag), Add.deeper(state));
    }

    @Override
    public void digDeeperState(String tag, State state) {
        doState(Remove.dig(tag), Add.deeper(state, tag));
    }*/

    @Override
    public void restore() {
        currentStateHolder.set(stateChanger.restore(activity));
    }

    public void activateCurrentState() {
        State state = currentStateHolder.get();
        if (state != null) {
            state.onActivate(activity);
        }
    }

    @Override
    public void state(@Nullable Remove.Interface remove) {
        doState(remove, null);
    }

    @Override
    public void state(@Nullable Add.Interface add) {
        doState(null, add);
    }

    @Override
    public void state(@Nullable Remove.Interface remove, @Nullable Add.Interface add) {
        doState(remove, add);
    }

    private void doState(@Nullable Remove.Interface remove, @Nullable Add.Interface add) {
        Intent intent = new Intent();
        AtomicBoolean closeCurrentActivity = new AtomicBoolean(false);
        State state = currentStateHolder.get();
        if (state != null) {
            state.onDeactivate(activity);
        }
        Item newItem = null;
        if (remove != null) {
            currentStateHolder.set(null);
            newItem = remove.remove(activity, stateChanger.getItems(), currentStateHolder, intent, closeCurrentActivity);
        }
        if (add != null) {
            newItem = add.add(activity, stateChanger.getItems(), intent);
        }
        if (newItem != null) {
            currentStateHolder.set(newItem.getState());
        }
        if (intent.getComponent() != null) {
            activity.startActivity(intent);
        }
        if (closeCurrentActivity.get()) {
            activity.finish();
        }
    }

    public void setActivity(JugglerActivity activity) {
        if (activity != null) {
            activity.getSupportFragmentManager().removeOnBackStackChangedListener(onBackStackChangedListener);
        }
        this.activity = activity;
        onBackStackChangedListener = new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                State state = currentStateHolder.get();
                if (state != null) {
                    state.onActivate(Juggler.this.activity);
                }
            }
        };
        this.activity.getSupportFragmentManager().addOnBackStackChangedListener(onBackStackChangedListener);
    }

    public void onPostCreate(Bundle savedInstanceState) {
        State state = currentStateHolder.get();
        if (state != null) {
            state.onPostCreate(activity, savedInstanceState);
        }
    }

    /**
     * @return true if current state process back press
     * false if not
     */
    public boolean onBackPressed() {
        final boolean b;
        State state = currentStateHolder.get();
        if (state == null) {
            b = false;
        } else {
            b = state.onBackPressed(activity);
        }
        return b;
    }

    public boolean onUpPressed() {
        final boolean b;
        State state = currentStateHolder.get();
        if (state == null) {
            b = false;
        } else {
            b = state.onUpPressed(activity);
        }
        return b;
    }

    @VisibleForTesting
    public int getStackLength() {
        return stateChanger.getStackLength();
    }

    public boolean hasLayoutId() {
        return layoutId != NOT_SET;
    }

    @LayoutRes
    public int getLayoutId() {
        return layoutId;
    }

    public void setLayoutId(@LayoutRes int layoutId) {
        this.layoutId = layoutId;
    }

    public void dump() {
        Log.v(this, "*** begin Juggler dump ***");
        int backstackSize = activity.getSupportFragmentManager().getBackStackEntryCount();
        Log.v(this, "activity = " + activity);
        Log.v(this, "backstack size = " + backstackSize);
        for (int i = 0; i < backstackSize; i++) {
            FragmentManager.BackStackEntry backStackEntry = activity.getSupportFragmentManager().getBackStackEntryAt(i);
            Log.v(this, i + ") " + backStackEntry.getId() + " " + backStackEntry.getName() + " " + backStackEntry);
        }
        Log.v(this, "stack size = " + stateChanger.getItems().size());
        for (int i = 0; i < stateChanger.getItems().size(); i++) {
            Item item = stateChanger.getItems().get(i);
            Log.v(this, i + ") " + item);
        }
        State state = currentStateHolder.get();
        Log.v(this, "currentState = " + state);
        if (state != null) {
            Log.v(this, "grid size = " + state.getGrid().getCells().size());
            for (int i = 0; i < state.getGrid().getCells().size(); i++) {
                Cell cell = state.getGrid().getCells().get(i);
                Fragment fragment = activity.getSupportFragmentManager().findFragmentById(cell.getContainerId());
                Log.v(this, i + ") " + cell.getContainerId() + " " + cell.getType() + " " + fragment);
            }
        }
        Log.v(this, "*** end Juggler dump ***");
    }

    public static class StateHolder implements Serializable {

        @Nullable
        private State state = null;

        @Nullable
        public State get() {
            return state;
        }

        public void set(@Nullable State state) {
            this.state = state;
        }

    }

}
