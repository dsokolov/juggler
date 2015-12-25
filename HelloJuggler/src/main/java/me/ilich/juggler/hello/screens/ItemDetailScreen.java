package me.ilich.juggler.hello.screens;

import android.support.annotation.Nullable;

import me.ilich.juggler.JugglerContentFragment;
import me.ilich.juggler.JugglerToolbarFragment;
import me.ilich.juggler.Screen;
import me.ilich.juggler.hello.gui.ItemDetailsFragment;
import me.ilich.juggler.hello.gui.StandardToolbarFragment;

public class ItemDetailScreen extends Screen {

    private int itemId;

    public void setParams(int itemId){
        this.itemId = itemId;
    }

    @Nullable
    @Override
    protected JugglerToolbarFragment instanceToolbar() {
        return StandardToolbarFragment.create();
    }

    @Nullable
    @Override
    protected JugglerContentFragment instanceContent() {
        return ItemDetailsFragment.create(itemId);
    }
}
