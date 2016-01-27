package me.ilich.juggler.hello.old.screens;

import android.support.v7.app.ActionBar;

import me.ilich.juggler.old.fragments.Navigator_;
import me.ilich.juggler.Screen;
import me.ilich.juggler.ScreensManager;
import me.ilich.juggler.old.fragments.Transition_;
import me.ilich.juggler.old.fragments.JugglerLayout;
import me.ilich.juggler.old.fragments.content.JugglerContent;
import me.ilich.juggler.old.fragments.toolbar.JugglerToolbar;
import me.ilich.juggler.hello.old.gui.ListFragment;
import me.ilich.juggler.hello.old.gui.StandardToolbarFragment;

@JugglerLayout(me.ilich.juggler.R.layout.juggler_layout_toolbar)
@JugglerToolbar(value = StandardToolbarFragment.class, options = ActionBar.DISPLAY_HOME_AS_UP | ActionBar.DISPLAY_SHOW_TITLE)
@JugglerContent(ListFragment.class)
public class ListScreen extends Screen {

    public ListScreen(Navigator_ navigator) {
        super(navigator);
    }

    public void itemDetails(int item) {
        Transition_ itemDetails = new Transition_(ListScreen.class, ItemDetailScreen.class, ScreensManager.MODE.ADD, HelloScreensManager.STACK_DEFAULT);
        navigate(itemDetails, new ItemDetailScreen.Params(item));
    }

}
