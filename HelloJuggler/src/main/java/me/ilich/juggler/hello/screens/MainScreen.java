package me.ilich.juggler.hello.screens;

import me.ilich.juggler.fragments.content.JugglerContent;
import me.ilich.juggler.fragments.toolbar.JugglerToolbar;
import me.ilich.juggler.Screen;
import me.ilich.juggler.hello.gui.MainFragment;
import me.ilich.juggler.hello.gui.StandardToolbarFragment;

@JugglerToolbar(StandardToolbarFragment.class)
@JugglerContent(MainFragment.class)
public interface MainScreen extends Screen {

    void wizardOne();

    void list();

    void about();

    void login();

    void toolbarExplain();

}
