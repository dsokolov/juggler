package me.ilich.juggler;

public class Juggler<SM extends ScreensManager> {

    private SM screensManager;

    public Juggler(SM screensManager) {
        this.screensManager = screensManager;
    }

    public void setTitle(String title) {
        screensManager.setTitle(title);
    }

    public SM getScreenManager() {
        return screensManager;
    }
}
