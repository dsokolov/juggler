package me.ilich.juggler.hello.screens;

import me.ilich.juggler.JugglerContent;
import me.ilich.juggler.JugglerToolbar;
import me.ilich.juggler.Screen;
import me.ilich.juggler.hello.gui.ItemDetailsFragment;
import me.ilich.juggler.hello.gui.StandardToolbarFragment;

@JugglerToolbar(StandardToolbarFragment.class)
@JugglerContent(ItemDetailsFragment.class)
public class ItemDetailScreen extends Screen<ItemDetailScreen.Params> {

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
