package me.ilich.juggler.change;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import me.ilich.juggler.states.State;

public class Item {

    @LayoutRes
    private final int layoutId;
    private final String transactionName;
    private final State state;
    @Nullable
    private final String tag;

    Item(int layoutId, String transactionName, State state, String tag) {
        this.layoutId = layoutId;
        this.transactionName = transactionName;
        this.state = state;
        this.tag = tag;
    }

    @Override
    public String toString() {
        return transactionName + " " + layoutId + " " + state;
    }

    public State getState() {
        return state;
    }

    public int getLayoutId() {
        return layoutId;
    }

    public String getTransactionName() {
        return transactionName;
    }

    @Nullable
    public String getTag() {
        return tag;
    }
}
