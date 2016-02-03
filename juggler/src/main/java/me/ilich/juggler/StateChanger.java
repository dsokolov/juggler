package me.ilich.juggler;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;

import me.ilich.juggler.grid.Cell;
import me.ilich.juggler.grid.CellType;
import me.ilich.juggler.states.State;

public class StateChanger {

    private List<Item> items = new ArrayList<>();

    public void change(@Nullable State oldState, State newState, JugglerActivity activity) {
        int newLayoutId = newState.getGrid().getLayoutId();
        if (oldState == null) {
            items.clear();
            activity.setContentView(newLayoutId);
        } else {
            int oldLayoutId = oldState.getGrid().getLayoutId();
            boolean sameLayout = oldLayoutId == newLayoutId;
            if (!sameLayout) {
                activity.setContentView(newLayoutId);
            }
        }
        String transactionName = oldState + " -> " + newState;
        Item item = new Item(newLayoutId, transactionName);
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack(transactionName);
        for (Cell cell : newState.getGrid().getCells()) {
            int containerId = cell.getContainerId();
            CellType cellType = cell.getType();
            JugglerFragment fragment = newState.createFragment(cellType);
            fragmentTransaction.replace(containerId, fragment);
        }
        fragmentTransaction.commit();
        items.add(item);
    }

    public boolean back(JugglerActivity activity) {
        final boolean b;
        if (items.size() > 1) {
            Item current = items.get(items.size() - 1);
            Item prev = items.get(items.size() - 2);
            int currentLayoutId = current.layoutId;
            int prevLayoutId = prev.layoutId;
            if (currentLayoutId != prevLayoutId) {
                activity.setContentView(prevLayoutId);
            }
            FragmentManager fragmentManager = activity.getSupportFragmentManager();
            String transactionName = prev.transactionName;
            fragmentManager.popBackStack(transactionName, 0);
            items.remove(items.size() - 1);
            b = true;
        } else {
            b = false;
        }
        return b;
    }

    private static class Item {

        @LayoutRes
        private final int layoutId;
        private final String transactionName;

        private Item(int layoutId, String transactionName) {
            this.layoutId = layoutId;
            this.transactionName = transactionName;
        }

        @Override
        public String toString() {
            return transactionName + " " + layoutId;
        }

    }

}
