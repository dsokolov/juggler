package me.ilich.juggler;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import me.ilich.juggler.fragments.content.JugglerContentFragment;
import me.ilich.juggler.fragments.navigation.JugglerNavigationFragment;
import me.ilich.juggler.fragments.toolbar.JugglerToolbarFragment;

public class Juggler<SM extends ScreensManager> {

    private SM screensManager;
    private JugglerActivity<SM> activity;
    private JugglerNavigationFragment navigationFragment;
    private JugglerToolbarFragment toolbarFragment;

    public Juggler(SM screensManager, JugglerActivity<SM> activity) {
        this.screensManager = screensManager;
        this.activity = activity;
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

    public void onContentAttached(JugglerContentFragment fragment) {

    }

    public void onContentDetached(JugglerContentFragment fragment) {

    }

    public void onToolbarAttached(JugglerToolbarFragment fragment) {
        toolbarFragment = fragment;
    }

    public void onToolbarDetached(JugglerToolbarFragment fragment) {
        toolbarFragment = null;
    }

    public void onNavigationAttached(JugglerNavigationFragment fragment) {
        navigationFragment = fragment;
        if (navigationFragment != null && toolbarFragment != null) {
            DrawerLayout drawerLayout = activity.getDrawerLayout();
            Toolbar toolbar = toolbarFragment.getToolbar();
            navigationFragment.init(drawerLayout, toolbar);
        }
    }

    public void onNavigationDetached(JugglerNavigationFragment fragment) {
        navigationFragment = null;
    }

}
