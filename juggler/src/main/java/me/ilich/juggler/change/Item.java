package me.ilich.juggler.change;

import android.support.annotation.Nullable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import me.ilich.juggler.states.State;

public class Item implements Serializable {

    private final String transactionName;
    private final State state;
    @Nullable
    private final String tag;
    private final List<Integer> visibleIds = new ArrayList<>();
    private final List<Integer> goneIds = new ArrayList<>();

    Item(String transactionName, State state, String tag) {
        this.transactionName = transactionName;
        this.state = state;
        this.tag = tag;
    }

    public void addVisibleId(int id) {
        visibleIds.add(id);
    }

    public void addGoneId(int id) {
        goneIds.add(id);
    }

    @Override
    public String toString() {
        return transactionName + " " + state;
    }

    public State getState() {
        return state;
    }

    public String getTransactionName() {
        return transactionName;
    }

    @Nullable
    public String getTag() {
        return tag;
    }

    public List<Integer> getVisibleIds() {
        return visibleIds;
    }

    public List<Integer> getGoneIds() {
        return goneIds;
    }

}
