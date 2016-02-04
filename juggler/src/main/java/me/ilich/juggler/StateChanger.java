package me.ilich.juggler;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.Stack;

import me.ilich.juggler.grid.Cell;
import me.ilich.juggler.grid.CellType;

public class StateChanger {

    public enum Mode {
        ADD_CLEAR,
        ADD_LINEAR,
        ADD_DEEPER
    }

    private Stack<Item> items = new Stack<>();

    public State transaction(String transactionName, JugglerActivity activity) {
        Item oldItem = items.peek();
        Item newItem = null;
        boolean work = true;
        while (work) {
            if (items.isEmpty()) {
                work = false;
            } else {
                Item item = items.peek();
                if (item.transactionName.equals(transactionName)) {
                    newItem = item;
                    work = false;
                } else {
                    items.pop();
                }
            }
        }
        final State newState;
        if (newItem == null) {
            newState = null;
        } else {
            newState = newItem.state;
            if (oldItem.layoutId != newItem.layoutId) {
                activity.setContentView(newItem.layoutId);
            }
            activity.getSupportFragmentManager().popBackStack(newItem.transactionName, 0);
        }
        processStateChange(activity, oldItem.state, newState);
        return newState;
    }

    public State add(State newState, JugglerActivity activity, Mode mode) {
        final Item oldItem;
        final State oldState;
        if (items.isEmpty()) {
            oldItem = null;
            oldState = null;
        } else {
            oldItem = items.peek();
            oldState = oldItem.state;
        }
        int newLayoutId = newState.getGrid().getLayoutId();
        final boolean sameLayout;
        if (oldItem == null || mode == Mode.ADD_CLEAR) {
            items.clear();
            activity.setContentView(newLayoutId);
            sameLayout = true;
        } else {
            int oldLayoutId = oldState.getGrid().getLayoutId();
            sameLayout = oldLayoutId == newLayoutId;
            switch (mode) {
                case ADD_DEEPER:
                    newState.setBackTransition(Transition.transaction(oldItem.transactionName));
                    newState.setUpTransition(Transition.transaction(oldItem.transactionName));
                    break;
                case ADD_LINEAR:
                    newState.setBackTransition(Transition.transaction(oldItem.transactionName));
                    newState.setUpTransition(oldState.getUpTransition());
                    break;
            }

        }
        String transactionName = oldState + " -> " + newState;
        Item item = new Item(newLayoutId, transactionName, newState);

        if (!sameLayout) {
            activity.setContentView(newLayoutId);
        }
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack(transactionName);
        if (oldState != null & !sameLayout) {
            for (Cell cell : oldState.getGrid().getCells()) {
                int containerId = cell.getContainerId();
                Fragment fragment = fragmentManager.findFragmentById(containerId);
                fragmentTransaction.remove(fragment);
            }
        }
        for (Cell cell : newState.getGrid().getCells()) {
            int containerId = cell.getContainerId();
            CellType cellType = cell.getType();
            JugglerFragment fragment = newState.createFragment(cellType);
            fragmentTransaction.replace(containerId, fragment);
        }
        fragmentTransaction.commit();
        items.push(item);
        processStateChange(activity, oldState, newState);
        return newState;
    }

    @Nullable
    public State back(JugglerActivity activity) {
        final State state;
        Item current = items.get(items.size() - 1);
        if (items.size() > 1) {
            Item prev = items.get(items.size() - 2);
            int currentLayoutId = current.layoutId;
            int prevLayoutId = prev.layoutId;
            if (currentLayoutId != prevLayoutId) {
                activity.setContentView(prevLayoutId);
            }
            FragmentManager fragmentManager = activity.getSupportFragmentManager();
            String transactionName = prev.transactionName;
            fragmentManager.popBackStack(transactionName, 0);
            state = prev.state;
        } else {
            state = null;
        }
        items.pop();
        processStateChange(activity, current.state, state);
        return state;
    }

    @Nullable
    public State up(JugglerActivity activity) {
        final State state;
        Item current = items.peek();
        if (items.size() > 1) {
            Item prev = items.get(items.size() - 2);
            int currentLayoutId = current.layoutId;
            int prevLayoutId = prev.layoutId;
            if (currentLayoutId != prevLayoutId) {
                activity.setContentView(prevLayoutId);
            }
            FragmentManager fragmentManager = activity.getSupportFragmentManager();
            String transactionName = prev.transactionName;
            fragmentManager.popBackStack(transactionName, 0);
            state = prev.state;
        } else {
            state = null;
        }
        items.pop();
        processStateChange(activity, current.state, state);
        return state;
    }

    public State restore(JugglerActivity activity) {
        Item item = items.peek();
        activity.setContentView(item.layoutId);
        processStateChange(activity, null, item.state);
        return item.state;
    }

    private void processStateChange(JugglerActivity activity, @Nullable State oldState, @Nullable State newState) {
        if (oldState != null) {
            oldState.onDeactivate(activity);
        }
        if (newState != null) {
            newState.onActivate(activity);
        }
    }

    private static class Item {

        @LayoutRes
        private final int layoutId;
        private final String transactionName;
        private final State state;

        private Item(int layoutId, String transactionName, State state) {
            this.layoutId = layoutId;
            this.transactionName = transactionName;
            this.state = state;
        }

        @Override
        public String toString() {
            return transactionName + " " + layoutId + " " + state;
        }

    }

}
