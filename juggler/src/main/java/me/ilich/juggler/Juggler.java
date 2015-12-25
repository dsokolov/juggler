package me.ilich.juggler;

public class Juggler<SM extends ScreensManager> {

    private final SM screenManager;

    public Juggler(SM screenManager) {
        this.screenManager = screenManager;
    }

    public SM getScreenManager() {
        return screenManager;
    }

}
