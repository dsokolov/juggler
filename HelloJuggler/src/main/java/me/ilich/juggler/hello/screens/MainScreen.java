package me.ilich.juggler.hello.screens;

import me.ilich.juggler.JugglerContent;
import me.ilich.juggler.JugglerToolbar;
import me.ilich.juggler.JugglerToolbarFragment;
import me.ilich.juggler.Screen;
import me.ilich.juggler.hello.gui.MainFragment;
import me.ilich.juggler.hello.gui.StandardToolbarFragment;

@JugglerToolbar(aClass = StandardToolbarFragment.class)
@JugglerContent(aClass = MainFragment.class)
public class MainScreen extends Screen<MainScreen.Params> {

    public static class Params extends Screen.Params {

    }

}
