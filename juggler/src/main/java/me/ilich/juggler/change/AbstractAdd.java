package me.ilich.juggler.change;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import me.ilich.juggler.Juggler;
import me.ilich.juggler.Log;
import me.ilich.juggler.grid.Cell;
import me.ilich.juggler.gui.JugglerActivity;
import me.ilich.juggler.gui.JugglerFragment;
import me.ilich.juggler.gui.JugglerToolbarFragment;
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
    public Item add(JugglerActivity activity, Stack<Item> items, Juggler.StateHolder currentStateHolder, Bundle bundle) {
        int newLayoutId = newState.getGrid().getLayoutId();
        if (activity.getJuggler().hasLayoutId()) {
            if (newLayoutId != activity.getJuggler().getLayoutId()) {
                throw new RuntimeException("Cant add state to activity with different layout id");
            }
        } else {
            activity.getJuggler().setLayoutId(newLayoutId);
            activity.setContentView(newLayoutId);
        }

        final Item newItem;
        final boolean needToSetLayout;
        final String transactionName;
        final String tag = this.tag == null ? newState.getTag() : this.tag;
        if (items.empty()) {
            needToSetLayout = true;
            transactionName = StateChanger.generateTransactionName(null, newState);
            newItem = new Item(transactionName, newState, tag);
        } else {
            Item oldItem = items.peek();
            State oldState = oldItem.getState();
            transactionName = StateChanger.generateTransactionName(oldState, newState);
            int oldLayoutId = oldState.getGrid().getLayoutId();
            needToSetLayout = oldLayoutId != newLayoutId;
            newItem = new Item(transactionName, newState, tag);
            onProcessBackUp(oldItem, newItem);
        }


        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack(transactionName);

        if (needToSetLayout) {
            activity.setContentView(newLayoutId);
            if (!items.empty()) {
                //FragmentTransaction ft = fragmentManager.beginTransaction();
                Item oldItem = items.peek();
                State oldState = oldItem.getState();
                for (Cell cell : oldState.getGrid().getCells()) {
                    int containerId = cell.getContainerId();
                    Fragment fragment = fragmentManager.findFragmentById(containerId);

                    //TODO
                    // если эта строка есть - фрагменты восстанавливаются из бекстека, получается по 2 фрагмента и следовательное не работает тулбар
                    // если нет - не работае навигация через up, пропадает навигейшен вью

                    fragmentTransaction.remove(fragment);
                    //ft.remove(fragment);
                }
                //ft.commit();
                //fragmentManager.executePendingTransactions();
            }
        }

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
            }
        }
        for (Cell cell : newState.getGrid().getCells()) {
            int containerId = cell.getContainerId();
            JugglerFragment oldFragment = (JugglerFragment) fragmentManager.findFragmentById(containerId);
            int cellType = cell.getType();
            JugglerFragment fragment = newState.convertFragment(cellType, oldFragment);
            if (fragment == null) {
                newItem.addGoneId(containerId);
                View v = activity.findViewById(containerId);
                if (v != null) {
                    //v.setVisibility(View.GONE);
                }
                Fragment f = fragmentManager.findFragmentById(containerId);
                Log.i(getClass(), "f = " + f);
                if (f != null) {
                    fragmentTransaction.remove(f);
                }
            } else {
                newItem.addVisibleId(containerId);
                View v = activity.findViewById(containerId);
                if (v != null) {
                    //v.setVisibility(View.VISIBLE);
                }
                for (TargetBound targetBound : bounds.keySet()) {
                    if (targetBound.getCellIdTo() == cellType) {
                        Fragment targetFragment = bounds.get(targetBound);
                        fragment.setTargetFragment(targetFragment, targetBound.getRequestCode());
                    }
                }
                fragmentTransaction.replace(containerId, fragment);
            }
        }
        newState.onFragmentTransitionBeforeCommit(fragmentTransaction);
        fragmentTransaction.commit();

        currentStateHolder.set(newItem.getState());

        fragmentManager.executePendingTransactions();

        items.push(newItem);
        return newItem;
    }

    protected abstract void onProcessBackUp(Item oldItem, Item newItem);

}
