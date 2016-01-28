package me.ilich.juggler.hello.old.screens;

import android.support.v7.app.ActionBar;

import me.ilich.juggler.old.fragments.Navigator_;
import me.ilich.juggler.old.fragments.JugglerLayout;
import me.ilich.juggler.old.fragments.content.JugglerContent;
import me.ilich.juggler.old.fragments.toolbar.JugglerToolbar;
import me.ilich.juggler.Screen;
import me.ilich.juggler.hello.old.gui.ItemDetailsFragment;
import me.ilich.juggler.hello.old.gui.StandardToolbarFragment;

@JugglerLayout(me.ilich.juggler.R.layout.juggler_layout_content_below_toolbar)
@JugglerToolbar(value = StandardToolbarFragment.class, options = ActionBar.DISPLAY_SHOW_TITLE | ActionBar.DISPLAY_HOME_AS_UP)
@JugglerContent(ItemDetailsFragment.class)
public class ItemDetailScreen extends Screen {

    public ItemDetailScreen(Navigator_ navigator) {
        super(navigator);
    }

    public static class Params extends Screen.Params {

        private final int itemId;

        public Params(int itemId) {
            this.itemId = itemId;
        }

        public int getItemId() {
            return itemId;
        }

    }

}
