package me.ilich.juggler;

public abstract class Action {

    public static Action nothing() {
        return new NothingAction();
    }

    public static Action toScreen(Screen screen) {
        return null;
    }

    private static class NothingAction extends Action {

    }

}
