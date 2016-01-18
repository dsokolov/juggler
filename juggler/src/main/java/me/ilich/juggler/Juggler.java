package me.ilich.juggler;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.util.Log;

import me.ilich.juggler.activity.JugglerActivity;
import me.ilich.juggler.activity.LayoutController;
import me.ilich.juggler.fragments.JugglerFragment;
import me.ilich.juggler.fragments.content.JugglerContentFragment;
import me.ilich.juggler.fragments.navigation.JugglerNavigationFragment;
import me.ilich.juggler.fragments.toolbar.JugglerToolbarFragment;

public class Juggler<SM extends ScreensManager> {

    private SM screensManager;
    private LayoutController layoutController = null;
    private JugglerActivity<SM> activity;

    public Juggler(SM screensManager, JugglerActivity<SM> activity) {
        this.screensManager = screensManager;
        this.screensManager.setJuggler(this);
        this.activity = activity;
        layoutController = new LayoutController(activity);
    }

    public void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            screensManager.onFirstScreen();
        } else {
            screensManager.onRestore(savedInstanceState);
        }
    }

    public void onDestroy() {
        screensManager.deattachAll();
    }

    public void onSaveInstanceState(Bundle outState) {
        screensManager.onSaveInstanceState(outState);
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

    public void onAttach(JugglerFragment<SM> fragment) {
        if (fragment instanceof JugglerToolbarFragment) {
            screensManager.onToolbarAttached((JugglerToolbarFragment) fragment);
        } else if (fragment instanceof JugglerNavigationFragment) {
            screensManager.onNavigationAttached((JugglerNavigationFragment) fragment);
        } else if (fragment instanceof JugglerContentFragment) {
            screensManager.onContentAttached((JugglerContentFragment) fragment);
        }
    }

    public void onDetach(JugglerFragment<SM> fragment) {
        if (fragment instanceof JugglerToolbarFragment) {
            screensManager.onToolbarDetached((JugglerToolbarFragment) fragment);
        } else if (fragment instanceof JugglerNavigationFragment) {
            screensManager.onNavigationDetached((JugglerNavigationFragment) fragment);
        } else if (fragment instanceof JugglerContentFragment) {
            screensManager.onContentDetached((JugglerContentFragment) fragment);
        }
    }

    public void onBeginNewScreen(Screen.Instance screenInstance) {
        Log.v("Sokolov", "begin " + screenInstance);
    }

    public void onEndNewScreen(Screen.Instance screenInstance) {
        Log.v("Sokolov", "end " + screenInstance);
    }

    public boolean onBack() {
        boolean b = layoutController.onBackPressed();
        if (!b) {
            b = screensManager.back();
        }
        return b;
    }

    public boolean onUp() {
        return screensManager.up();
    }

    public LayoutController getLayoutController() {
        return layoutController;
    }

    public boolean hasToolbarContainer() {
        return layoutController.getToolbarViewGroup() != null;
    }

    public void setToolbarTitle(CharSequence title, int color) {
        if (screensManager.getToolbarFragment() != null) {
            screensManager.getToolbarFragment().setTitle(title, color);
        }
    }

    public void navigateTo(Transition transition) {
        Screen.Instance instance = screensManager.getCurrentScreen();
        String currentScreen = instance.getScreenClassName();
        String sourceScreen = transition.getSource().getName();
        if (currentScreen.equals(sourceScreen)) {
            transition.execute(screensManager);
        } else {
            throw new RuntimeException("");
        }
    }

}
