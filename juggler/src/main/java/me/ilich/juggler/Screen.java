package me.ilich.juggler;

public abstract class Screen {

    private Action backAction = Action.nothing();
    private Action upAction = Action.nothing();

    public void setBack(Action action) {
        this.backAction = action;
    }

    public void setUp(Action action) {
        this.upAction = action;
    }

}
