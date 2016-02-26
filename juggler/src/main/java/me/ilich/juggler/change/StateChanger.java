package me.ilich.juggler.change;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.UUID;

import me.ilich.juggler.Transition;
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

    public static String generateTransactionName(State oldState, State newState) {
        String fromStr = oldState == null ? "null" : oldState.getClass().getSimpleName();
        String toStr = newState == null ? "null" : newState.getClass().getSimpleName();
        String hashStr = UUID.randomUUID().toString();
        return String.format("%s -> %s (%s)", fromStr, toStr, hashStr);
    }

    private Stack<Item> items = new Stack<>();

    public State change(JugglerActivity activity, PopCondition popStateCondition, Add addCondition) {
        return doChange(activity, popStateCondition, addCondition);
    }

    @Nullable
    private State doChange(JugglerActivity activity, PopCondition popStateCondition, Add addCondition) {
        if (activity == null) {
            throw new NullPointerException("activity");
        }

        final Item resultItem;
        final Item oldItem;
        if (items.empty()) {
            oldItem = null;
        } else {
            oldItem = items.peek();
        }
        State oldState = oldItem == null ? null : oldItem.getState();

        boolean hasPop = popStateCondition != null;
        boolean hasAdd = addCondition != null;

        if (hasPop && hasAdd) {
            popStateCondition.pop(activity, items);
            resultItem = addCondition.add(activity, items);
        } else if (hasPop) {
            resultItem = popStateCondition.pop(activity, items);
        } else if (hasAdd) {
            resultItem = addCondition.add(activity, items);
        } else {
            if (items.empty()) {
                resultItem = null;
            } else {
                resultItem = items.peek();
            }
        }

        final State resultState;
        if (resultItem == null) {
            resultState = null;
        } else {
            resultState = resultItem.getState();
        }
        processStateChange(activity, oldState, resultState);

        return resultState;
    }


    public State transaction(String transactionName, JugglerActivity activity, @Nullable String tag) {
        Item oldItem = items.peek();
        Item newItem = null;
        boolean work = true;
        while (work) {
            if (items.isEmpty()) {
                work = false;
            } else {
                Item item = items.peek();
                if (item.getTransactionName().equals(transactionName)) {
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
            newState = newItem.getState();
            if (oldItem.getLayoutId() != newItem.getLayoutId()) {
                activity.setContentView(newItem.getLayoutId());
            }
            activity.getSupportFragmentManager().popBackStack(newItem.getTransactionName(), 0);
        }
        processStateChange(activity, oldItem.getState(), newState);
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
            oldState = oldItem.getState();
        }
        int newLayoutId = newState.getGrid().getLayoutId();
        String firstTransactionName = null;
        final boolean sameLayout;
        if (oldItem == null || mode == Mode.ADD_CLEAR) {
            if (items.size() > 0) {
                firstTransactionName = items.get(0).getTransactionName();
            }
            items.clear();
            activity.setContentView(newLayoutId);
            sameLayout = true;
        } else {
            int oldLayoutId = oldState.getGrid().getLayoutId();
            sameLayout = oldLayoutId == newLayoutId;
            switch (mode) {
                case ADD_DEEPER:
                    newState.setBackTransition(Transition.transaction(oldItem.getTransactionName(), null));
                    newState.setUpTransition(Transition.transaction(oldItem.getTransactionName(), null));
                    break;
                case ADD_LINEAR:
                    newState.setBackTransition(Transition.transaction(oldItem.getTransactionName(), null));
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
            fm.popBackStackImmediate(firstTransactionName, FragmentManager.POP_BACK_STACK_INCLUSIVE);
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
            oldState = oldItem.getState();
        }

        boolean work = true;
        while (work) {
            if (items.isEmpty()) {
                work = false;
            } else {
                Item item = items.peek();
                if (item.getTag() != null && item.getTag().equals(digTag)) {
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
                    newState.setBackTransition(Transition.transaction(oldItem.getTransactionName(), null));
                    newState.setUpTransition(Transition.transaction(oldItem.getTransactionName(), null));
                    break;
                case ADD_LINEAR:
                    newState.setBackTransition(Transition.transaction(oldItem.getTransactionName(), null));
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
                if (item.getTag() != null && item.getTag().equals(tag)) {
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
            newState = newItem.getState();
            if (oldItem.getLayoutId() != newItem.getLayoutId()) {
                activity.setContentView(newItem.getLayoutId());
            }
            activity.getSupportFragmentManager().popBackStack(newItem.getTransactionName(), 0);
        }
        processStateChange(activity, oldItem.getState(), newState);
        return newState;
    }

    public State restore(JugglerActivity activity) {
        Item item = items.peek();
        activity.setContentView(item.getLayoutId());
        processStateChange(activity, null, item.getState());
        return item.getState();
    }

    private void processStateChange(JugglerActivity activity, @Nullable State oldState, @Nullable State newState) {
        if (oldState != null) {
            oldState.onDeactivate(activity);
        }
        if (newState != null) {
            newState.onActivate(activity);
        }
    }

}
