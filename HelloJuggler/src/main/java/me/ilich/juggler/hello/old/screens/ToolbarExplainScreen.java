package me.ilich.juggler.hello.old.screens;

import me.ilich.juggler.old.fragments.Navigator_;
import me.ilich.juggler.Screen;
import me.ilich.juggler.old.fragments.content.JugglerContent;
import me.ilich.juggler.old.fragments.toolbar.JugglerToolbar;
import me.ilich.juggler.hello.old.gui.ExplainToolbarFragment;
import me.ilich.juggler.hello.old.gui.StandardToolbarFragment;

@JugglerToolbar(StandardToolbarFragment.class)
@JugglerContent(ExplainToolbarFragment.class)
public class ToolbarExplainScreen extends Screen {

    public ToolbarExplainScreen(Navigator_ navigator) {
        super(navigator);
    }

}
