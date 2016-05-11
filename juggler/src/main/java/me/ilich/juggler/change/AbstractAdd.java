package me.ilich.juggler.change;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import me.ilich.juggler.grid.Cell;
import me.ilich.juggler.gui.JugglerActivity;
import me.ilich.juggler.gui.JugglerFragment;
import me.ilich.juggler.states.State;
import me.ilich.juggler.states.TargetBound;

public abstract class AbstractAdd implements Add.Interface {

    private final State newState;
    private final String tag;
    private final TargetBound[] targetBounds;

    public AbstractAdd(State state, @Nullable String tag, TargetBound... targetBounds) {
        this.newState = state;
        this.tag = tag;
        this.targetBounds = targetBounds;
    }

    @Override
    public Item add(JugglerActivity activity, Stack<Item> items) {
        int newLayoutId = newState.getGrid().getLayoutId();

        final Item newItem;
        final boolean needToSetLayout;
        final String transactionName;
        final String tag = this.tag == null ? newState.getTag() : this.tag;
        if (items.empty()) {
            needToSetLayout = true;
            transactionName = StateChanger.generateTransactionName(null, newState);
            newItem = new Item(newLayoutId, transactionName, newState, tag);
        } else {
            Item oldItem = items.peek();
            State oldState = oldItem.getState();
            transactionName = StateChanger.generateTransactionName(oldState, newState);
            int oldLayoutId = oldState.getGrid().getLayoutId();
            needToSetLayout = oldLayoutId != newLayoutId;
            newItem = new Item(newLayoutId, transactionName, newState, tag);
            onProcessBackUp(oldItem, newItem);
        }
        if (needToSetLayout) {
            activity.setContentView(newLayoutId);
        }


        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack(transactionName);

        Map<TargetBound, Fragment> bounds = new HashMap<>();

        if (!items.empty()) {
            Item oldItem = items.peek();
            State oldState = oldItem.getState();
            for (Cell cell : oldState.getGrid().getCells()) {
                int containerId = cell.getContainerId();
                Fragment fragment = fragmentManager.findFragmentById(containerId);
                for (TargetBound targetBound : targetBounds) {
                    if (targetBound.getCellIdFrom() == cell.getType()) {
                        bounds.put(targetBound, fragment);
                        break;
                    }
                }
                if (needToSetLayout) {
                    //fragmentTransaction.remove(fragment); //TODO wtf???
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
        newState.onFragmentTransitionBeforeCommit(fragmentTransaction);
        fragmentTransaction.commit();

        items.push(newItem);
        return newItem;
    }

    protected abstract void onProcessBackUp(Item oldItem, Item newItem);

}
