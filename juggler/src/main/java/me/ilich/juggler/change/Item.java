package me.ilich.juggler.change;

import android.support.annotation.Nullable;

import java.io.Serializable;

import me.ilich.juggler.states.State;

public class Item implements Serializable {

    private final String transactionName;
    private final State state;
    @Nullable
    private final String tag;

    Item(String transactionName, State state, String tag) {
        this.transactionName = transactionName;
        this.state = state;
        this.tag = tag;
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
}
