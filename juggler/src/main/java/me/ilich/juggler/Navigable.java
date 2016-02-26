package me.ilich.juggler;

import me.ilich.juggler.change.Add;
import me.ilich.juggler.change.PopCondition;

public interface Navigable {

    boolean backState();

    boolean upState();

/*    void linearState(State state, TargetBound... targetBounds);

    void linearState(State state, String tag);

    void deeperState(State state, TargetBound... targetBounds);

    void deeperState(State state, String tag);

    void clearState(State state);

    void clearState(State state, String tag);

    void dig(String tag);

    void digLinearState(String tag, State state);

    void digDeeperState(String tag, State state);*/

    void restore();

    void state(PopCondition popCondition, Add addCondition);

}
