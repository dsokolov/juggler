package me.ilich.juggler.change;

import me.ilich.juggler.Transition;
import me.ilich.juggler.states.State;
import me.ilich.juggler.states.TargetBound;

public class LinearAdd extends AbstractAdd {

    public LinearAdd(State state, TargetBound... targetBounds) {
        super(state, targetBounds);
    }

    @Override
    protected void onProcessBackUp(Item oldItem, Item newItem) {
        newItem.getState().setBackTransition(Transition.transaction(oldItem.getTransactionName(), null));
        newItem.getState().setUpTransition(oldItem.getState().getUpTransition());
    }

}
