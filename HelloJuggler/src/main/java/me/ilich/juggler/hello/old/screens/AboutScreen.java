package me.ilich.juggler.hello.old.screens;

import android.support.v7.app.ActionBar;

import me.ilich.juggler.old.fragments.Navigator_;
import me.ilich.juggler.Screen;
import me.ilich.juggler.old.fragments.JugglerLayout;
import me.ilich.juggler.old.fragments.content.JugglerContent;
import me.ilich.juggler.old.fragments.toolbar.JugglerToolbar;
import me.ilich.juggler.hello.R;
import me.ilich.juggler.hello.old.gui.AboutFragment;
import me.ilich.juggler.hello.old.gui.StandardToolbarFragment;

@JugglerLayout(R.layout.juggler_layout_toolbar)
@JugglerToolbar(value = StandardToolbarFragment.class, options = ActionBar.DISPLAY_HOME_AS_UP | ActionBar.DISPLAY_SHOW_TITLE)
@JugglerContent(AboutFragment.class)
public class AboutScreen extends Screen {

    public AboutScreen(Navigator_ navigator) {
        super(navigator);
    }

}
