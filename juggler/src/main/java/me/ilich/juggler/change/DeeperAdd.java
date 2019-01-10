package me.ilich.juggler.change;

import androidx.annotation.Nullable;
import me.ilich.juggler.Transition;
import me.ilich.juggler.states.State;
import me.ilich.juggler.states.TargetBound;

class DeeperAdd extends AbstractAdd {

    public DeeperAdd(State state, @Nullable String tag, TargetBound... targetBounds) {
        super(state, tag, targetBounds);
    }

    @Override
    protected void onProcessBackUp(Item oldItem, Item newItem) {
        newItem.getState().setBackTransition(Transition.transaction(oldItem.getTransactionName(), null));
        newItem.getState().setUpTransition(Transition.transaction(oldItem.getTransactionName(), null));
    }

}
