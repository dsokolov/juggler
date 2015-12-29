package me.ilich.juggler;

import android.support.annotation.StringRes;
import android.support.v7.app.ActionBar;

import me.ilich.juggler.fragments.toolbar.JugglerToolbarFragment;

public class Juggler<SM extends ScreensManager> {

    private SM screensManager;

    public Juggler(SM screensManager) {
        this.screensManager = screensManager;
    }

    public void setTitle(String title) {
        screensManager.setTitle(title);
    }

    public void setTitle(@StringRes int title) {
        screensManager.setTitle(title);
    }

    public void setToolbarMode(JugglerToolbarFragment.Mode mode) {
        screensManager.setToolbarMode(mode);
    }

    public SM getScreenManager() {
        return screensManager;
    }

    public void setToolbarOptions(@ActionBar.DisplayOptions int options) {
        screensManager.setToolbarOptions(options);
    }

    public int getToolbarOptions() {
        return screensManager.getToolbarOptions();
    }
}
