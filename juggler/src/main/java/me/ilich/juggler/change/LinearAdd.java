package me.ilich.juggler.change;

import me.ilich.juggler.Transition;
import me.ilich.juggler.states.State;
import me.ilich.juggler.states.TargetBound;

class LinearAdd extends AbstractAdd {

    public LinearAdd(State state, String tag, TargetBound... targetBounds) {
        super(state, tag, targetBounds);
    }

    @Override
    protected void onProcessBackUp(Item oldItem, Item newItem) {
        newItem.getState().setBackTransition(Transition.transaction(oldItem.getTransactionName(), null));
        newItem.getState().setUpTransition(oldItem.getState().getUpTransition());
    }

}
