package me.ilich.juggler.hello.screens;

import android.support.annotation.Nullable;

import me.ilich.juggler.JugglerContentFragment;
import me.ilich.juggler.JugglerToolbarFragment;
import me.ilich.juggler.Screen;
import me.ilich.juggler.hello.gui.ItemDetailsFragment;
import me.ilich.juggler.hello.gui.StandardToolbarFragment;

public class ItemDetailScreen extends Screen<ItemDetailScreen.Params> {


    @Override
    protected Class<? extends JugglerToolbarFragment> toolbarClass() {
        return StandardToolbarFragment.class;
    }

    @Override
    protected Class<? extends JugglerContentFragment> contentClass() {
        return ItemDetailsFragment.class;
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
