package me.ilich.juggler.hello.screens;

import android.support.v7.app.ActionBar;

import me.ilich.juggler.Screen;
import me.ilich.juggler.fragments.content.JugglerContent;
import me.ilich.juggler.fragments.navigation.JugglerNavigation;
import me.ilich.juggler.fragments.toolbar.JugglerToolbar;
import me.ilich.juggler.hello.gui.MainFragment;
import me.ilich.juggler.hello.gui.MenuFragment;
import me.ilich.juggler.hello.gui.StandardToolbarFragment;

@JugglerToolbar(value = StandardToolbarFragment.class, options = ActionBar.DISPLAY_SHOW_TITLE)
@JugglerNavigation(value = MenuFragment.class, menuItem = 5)
@JugglerContent(MainFragment.class)
public interface MainScreen extends Screen {

    void wizardOne();

    void list();

    void about();

    void login();

    void toolbarExplain();

}
