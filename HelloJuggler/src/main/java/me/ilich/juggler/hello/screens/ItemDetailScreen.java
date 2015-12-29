package me.ilich.juggler.hello.screens;

import me.ilich.juggler.fragments.content.JugglerContent;
import me.ilich.juggler.fragments.toolbar.JugglerToolbar;
import me.ilich.juggler.Screen;
import me.ilich.juggler.hello.gui.ItemDetailsFragment;
import me.ilich.juggler.hello.gui.StandardToolbarFragment;

@JugglerToolbar(StandardToolbarFragment.class)
@JugglerContent(ItemDetailsFragment.class)
public interface ItemDetailScreen extends Screen {

    class Params extends Screen.Params {

        private final int itemId;

        public Params(int itemId) {
            this.itemId = itemId;
        }

        public int getItemId() {
            return itemId;
        }

    }

}
