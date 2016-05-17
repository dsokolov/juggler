package me.ilich.juggler.hello.states;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;

import me.ilich.juggler.gui.JugglerFragment;
import me.ilich.juggler.hello.R;
import me.ilich.juggler.hello.gui.fragments.ItemDetailsFragment;
import me.ilich.juggler.hello.gui.fragments.StandardToolbarFragment;
import me.ilich.juggler.states.ContentBelowToolbarState;
import me.ilich.juggler.states.State;

public class ItemDetailsState extends ContentBelowToolbarState<ItemDetailsState.Params> {

    public ItemDetailsState(int id) {
        super(new Params(id));
    }

    @Override
    protected JugglerFragment onConvertContent(ItemDetailsState.Params params, @Nullable JugglerFragment fragment) {
        return ItemDetailsFragment.newInstance(params.id);
    }

    @Override
    protected JugglerFragment onConvertToolbar(Params params, @Nullable JugglerFragment fragment) {
        return StandardToolbarFragment.createTitleBack();
    }

    @Nullable
    @Override
    public String getTitle(Context context, Params params) {
        return params.id + "";
    }

    @Override
    public Drawable getUpNavigationIcon(Context context, Params params) {
        return context.getResources().getDrawable(android.R.drawable.ic_menu_zoom);
    }

    public static class Params extends State.Params {

        private final int id;

        public Params(int id) {
            this.id = id;
        }

    }

}
