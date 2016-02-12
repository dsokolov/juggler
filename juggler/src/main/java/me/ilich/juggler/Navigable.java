package me.ilich.juggler;

import me.ilich.juggler.states.State;

public interface Navigable {

    boolean backState();

    boolean upState();

    void linearState(State state);

    void linearState(State state, String tag);

    void deeperState(State state);

    void deeperState(State state, String tag);

    void clearState(State state);

    void clearState(State state, String tag);

    void dig(String tag);

    void digLinearState(String tag, State state);

    void digDeeperState(String tag, State state);

    void restore();

}
