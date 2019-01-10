package me.ilich.juggler;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import java.io.Serializable;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import me.ilich.juggler.change.Add;
import me.ilich.juggler.change.Item;
import me.ilich.juggler.change.NewActivityAdd;
import me.ilich.juggler.change.Remove;
import me.ilich.juggler.change.StateChanger;
import me.ilich.juggler.grid.Cell;
import me.ilich.juggler.gui.JugglerActivity;
import me.ilich.juggler.gui.JugglerNavigationFragment;
import me.ilich.juggler.states.State;

public class Juggler implements Navigable, Serializable {

    private static final int NOT_SET = -1;

    public static final String TAG = "Juggler";

    public static final String DATA_NEW_ACTIVITY_INTENT = "new_activity_intent";
    public static final String DATA_CLOSE_CURRENT_ACTIVITY = "close_current_activity";
    public static final String DATA_ANIMATION_START_ENTER = "animation start enter";
    public static final String DATA_ANIMATION_START_EXIT = "animation start exit";
    public static final String DATA_ANIMATION_FINISH_ENTER = "animation finish enter";
    public static final String DATA_ANIMATION_FINISH_EXIT = "animation finish exit";
    public static final String DATA_IS_FOR_RESULT = "start for result";
    public static final String DATA_REQUEST_CODE = "request code";

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
        Bundle bundle = new Bundle();
        State state = currentStateHolder.get();
        if (state != null) {
            state.onDeactivate(activity);
        }
        Item newItem = null;
        if (remove != null) {
            newItem = remove.remove(activity, stateChanger.getItems(), currentStateHolder, bundle);
        }
        if (add != null) {
            newItem = add.add(activity, stateChanger.getItems(), currentStateHolder, bundle);
        }
        Intent newActivityIntent = bundle.getParcelable(DATA_NEW_ACTIVITY_INTENT);
        int enterAnimation = bundle.getInt(DATA_ANIMATION_START_ENTER, 0);
        int exitAnimation = bundle.getInt(DATA_ANIMATION_START_EXIT, 0);
        int finishEnterAnimation = bundle.getInt(DATA_ANIMATION_FINISH_ENTER, 0);
        int finishExitAnimation = bundle.getInt(DATA_ANIMATION_FINISH_EXIT, 0);
        boolean isForResult = bundle.getBoolean(DATA_IS_FOR_RESULT, false);
        int requestCode = bundle.getInt(DATA_REQUEST_CODE, 0);
        if (newActivityIntent != null) {
            newActivityIntent.putExtra(DATA_ANIMATION_FINISH_ENTER, finishEnterAnimation);
            newActivityIntent.putExtra(DATA_ANIMATION_FINISH_EXIT, finishExitAnimation);
            if (isForResult) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN && add != null && add instanceof NewActivityAdd && ((NewActivityAdd) add).getActivityOptions() != null) {
                    //noinspection RestrictedApi
                    activity.startActivityForResult(newActivityIntent, requestCode, ((NewActivityAdd) add).getActivityOptions());
                } else {
                    activity.startActivityForResult(newActivityIntent, requestCode);
                }
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN && add != null && add instanceof NewActivityAdd && ((NewActivityAdd) add).getActivityOptions() != null) {
                    activity.startActivity(newActivityIntent, ((NewActivityAdd) add).getActivityOptions());
                } else {
                    activity.startActivity(newActivityIntent);
                }
            }
            activity.overridePendingTransition(enterAnimation, exitAnimation);
        }
        boolean closeCurrentActivity = bundle.getBoolean(DATA_CLOSE_CURRENT_ACTIVITY, false);
        if (closeCurrentActivity) {
            activity.finish();
            activity.overridePendingTransition(finishEnterAnimation, finishExitAnimation);
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

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        State state = currentStateHolder.state;
        if (state != null) {
            for (Cell cell : state.getGrid().getCells()) {
                Fragment f = activity.getSupportFragmentManager().findFragmentById(cell.getContainerId());
                f.onActivityResult(requestCode, resultCode, data);
            }
        }
    }

    public void openDrawer() {
        State state = currentStateHolder.state;
        if (state != null) {
            Cell cell = null;
            for (Cell c : state.getGrid().getCells()) {
                if (c.getType() == Cell.CELL_TYPE_NAVIGATION) {
                    cell = c;
                }
            }
            if (cell == null) {
                throw new IllegalStateException("State has no drawer");
            } else {
                JugglerNavigationFragment f = (JugglerNavigationFragment) activity.getSupportFragmentManager().findFragmentById(cell.getContainerId());
                f.openDrawer();
            }
        } else {
            throw new NullPointerException("State not initialized");
        }
    }

    public void closeDrawer() {
        State state = currentStateHolder.state;
        if (state != null) {
            Cell cell = null;
            for (Cell c : state.getGrid().getCells()) {
                if (c.getType() == Cell.CELL_TYPE_NAVIGATION) {
                    cell = c;
                }
            }
            if (cell == null) {
                throw new IllegalStateException("State has no drawer");
            } else {
                JugglerNavigationFragment f = (JugglerNavigationFragment) activity.getSupportFragmentManager().findFragmentById(cell.getContainerId());
                f.closeDrawer();
            }
        } else {
            throw new NullPointerException("State not initialized");
        }
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
