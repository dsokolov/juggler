package me.ilich.juggler;

public abstract class ScreensManager {

    public final void init() {
        onInit();
    }

    protected abstract void onInit();

}
