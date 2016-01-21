package me.ilich.juggler.hello.screens;

import android.support.v7.app.ActionBar;

import me.ilich.juggler.Navigator;
import me.ilich.juggler.Screen;
import me.ilich.juggler.ScreensManager;
import me.ilich.juggler.Transition_;
import me.ilich.juggler.fragments.JugglerLayout;
import me.ilich.juggler.fragments.content.JugglerContent;
import me.ilich.juggler.fragments.navigation.JugglerNavigation;
import me.ilich.juggler.fragments.toolbar.JugglerToolbar;
import me.ilich.juggler.hello.R;
import me.ilich.juggler.hello.gui.CollapsingToolbarFragment;
import me.ilich.juggler.hello.gui.MainFragment;
import me.ilich.juggler.hello.gui.MenuFragment;

@JugglerLayout(R.layout.layout_main)
@JugglerToolbar(value = CollapsingToolbarFragment.class, options = ActionBar.DISPLAY_SHOW_TITLE)
@JugglerNavigation(value = MenuFragment.class, menuItem = 5)
@JugglerContent(MainFragment.class)
public class MainScreen extends Screen {

    private Transition_ about = new Transition_(MainScreen.class, AboutScreen.class, ScreensManager.MODE.ADD, HelloScreensManager.STACK_DEFAULT);

    public MainScreen(Navigator navigator) {
        super(navigator);
    }

    public void about() {
        navigate(about, null);
    }

    Transition_ wizardOne = new Transition_(MainScreen.class, WizardOneScreen.class, ScreensManager.MODE.ADD, HelloScreensManager.STACK_DEFAULT);
    Transition_ list = new Transition_(MainScreen.class, AboutScreen.class, ScreensManager.MODE.ADD, HelloScreensManager.STACK_DEFAULT);
    Transition_ login = new Transition_(MainScreen.class, LoginScreen.class, ScreensManager.MODE.ADD, HelloScreensManager.STACK_DEFAULT);
    Transition_ toolbarExplain = new Transition_(MainScreen.class, ToolbarExplainScreen.class, ScreensManager.MODE.ADD, HelloScreensManager.STACK_DEFAULT);

}
