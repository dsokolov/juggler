package me.ilich.juggler;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.UUID;

import me.ilich.juggler.grid.Cell;
import me.ilich.juggler.gui.JugglerActivity;
import me.ilich.juggler.gui.JugglerFragment;
import me.ilich.juggler.states.State;
import me.ilich.juggler.states.TargetBound;

//TODO do a refactoring!
public class StateChanger {

    public enum Mode {
        ADD_CLEAR,
        ADD_LINEAR,
        ADD_DEEPER
    }

    private static String generateTransactionName(State oldState, State newState) {
        String fromStr = oldState == null ? "null" : oldState.getClass().getSimpleName();
        String toStr = newState == null ? "null" : newState.getClass().getSimpleName();
        String hashStr = UUID.randomUUID().toString();
        return String.format("%s -> %s (%s)", fromStr, toStr, hashStr);
    }

    private Stack<Item> items = new Stack<>();

    public State transaction(String transactionName, JugglerActivity activity, @Nullable String tag) {
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

    public State add(State newState, JugglerActivity activity, Mode mode, @Nullable String tag, TargetBound... targetBounds) {
        final Item oldItem;
        final State oldState;
        if (items.isEmpty() || mode == Mode.ADD_CLEAR) {
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
                    newState.setBackTransition(Transition.transaction(oldItem.transactionName, null));
                    newState.setUpTransition(Transition.transaction(oldItem.transactionName, null));
                    break;
                case ADD_LINEAR:
                    newState.setBackTransition(Transition.transaction(oldItem.transactionName, null));
                    newState.setUpTransition(oldState.getUpTransition());
                    break;
            }
        }
        String transactionName = generateTransactionName(oldState, newState);
        String itemTag = null;
        if (tag != null) {
            itemTag = tag;
        } else {
            if (newState.getTag() != null) {
                itemTag = newState.getTag();
            }
        }
        Item item = new Item(newLayoutId, transactionName, newState, itemTag);

        if (mode == Mode.ADD_CLEAR) {
            FragmentManager fm = activity.getSupportFragmentManager();
            while (fm.getBackStackEntryCount() > 0) {
                fm.popBackStackImmediate();
            }
        }


        if (!sameLayout) {
            activity.setContentView(newLayoutId);
        }

        processFragmentTransaction(activity, newState, oldState, sameLayout, transactionName, targetBounds);

        items.push(item);
        processStateChange(activity, oldState, newState);
        return newState;
    }

    public State digAdd(String digTag, JugglerActivity activity, Mode mode, State newState, @Nullable String tag, TargetBound... targetBounds) {

        final Item oldItem;
        final State oldState;
        if (items.isEmpty()) {
            oldItem = null;
            oldState = null;
        } else {
            oldItem = items.peek();
            oldState = oldItem.state;
        }

        boolean work = true;
        while (work) {
            if (items.isEmpty()) {
                work = false;
            } else {
                Item item = items.peek();
                if (item.tag != null && item.tag.equals(digTag)) {
                    work = false;
                } else {
                    items.pop();
                }
            }
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
                    newState.setBackTransition(Transition.transaction(oldItem.transactionName, null));
                    newState.setUpTransition(Transition.transaction(oldItem.transactionName, null));
                    break;
                case ADD_LINEAR:
                    newState.setBackTransition(Transition.transaction(oldItem.transactionName, null));
                    newState.setUpTransition(oldState.getUpTransition());
                    break;
            }
        }
        String transactionName = generateTransactionName(oldState, newState);
        String itemTag = null;
        if (tag != null) {
            itemTag = tag;
        } else {
            if (newState.getTag() != null) {
                itemTag = newState.getTag();
            }
        }
        Item item = new Item(newLayoutId, transactionName, newState, itemTag);


        if (!sameLayout) {
            activity.setContentView(newLayoutId);
        }

        processFragmentTransaction(activity, newState, oldState, sameLayout, transactionName, targetBounds);

        items.push(item);
        processStateChange(activity, oldState, newState);
        return newState;
    }

    private void processFragmentTransaction(JugglerActivity activity, State newState, State oldState, boolean sameLayout, String transactionName, TargetBound[] targetBounds) {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack(transactionName);

        Map<TargetBound, Fragment> bounds = new HashMap<>();

        if (oldState != null) {
            for (Cell cell : oldState.getGrid().getCells()) {
                int containerId = cell.getContainerId();
                Fragment fragment = fragmentManager.findFragmentById(containerId);
                for (TargetBound targetBound : targetBounds) {
                    if (targetBound.getCellIdFrom() == cell.getType()) {
                        bounds.put(targetBound, fragment);
                        break;
                    }
                }
                if (!sameLayout) {
                    fragmentTransaction.remove(fragment);
                }
            }
        }
        for (Cell cell : newState.getGrid().getCells()) {
            int containerId = cell.getContainerId();
            int cellType = cell.getType();
            JugglerFragment fragment = newState.createFragment(cellType);
            for (TargetBound targetBound : bounds.keySet()) {
                if (targetBound.getCellIdTo() == cellType) {
                    Fragment targetFragment = bounds.get(targetBound);
                    fragment.setTargetFragment(targetFragment, targetBound.getRequestCode());
                }
            }
            fragmentTransaction.replace(containerId, fragment);
        }
        fragmentTransaction.commit();
    }


    public State dig(String tag, JugglerActivity activity) {
        Item oldItem = items.peek();
        Item newItem = null;
        boolean work = true;
        while (work) {
            if (items.isEmpty()) {
                work = false;
            } else {
                Item item = items.peek();
                if (item.tag != null && item.tag.equals(tag)) {
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
        @Nullable
        private final String tag;

        private Item(int layoutId, String transactionName, State state, String tag) {
            this.layoutId = layoutId;
            this.transactionName = transactionName;
            this.state = state;
            this.tag = tag;
        }

        @Override
        public String toString() {
            return transactionName + " " + layoutId + " " + state;
        }

    }

}
