package me.ilich.juggler.fragments.toolbar;

import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;

import me.ilich.juggler.JugglerActivity_;
import me.ilich.juggler.fragments.JugglerFragment_;

public abstract class JugglerToolbarFragment extends JugglerFragment_ {

    private Toolbar toolbar;
    @ActionBar.DisplayOptions
    private int initialOptions = 0;
    @DrawableRes
    private int initialNavigationIcon = 0;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toolbar = (Toolbar) view.findViewById(getToolbarId());
        if (initialNavigationIcon != 0) {
            toolbar.setNavigationIcon(initialNavigationIcon);
        }
        ((JugglerActivity_) getActivity()).setSupportActionBar(toolbar);
        ActionBar actionBar = ((JugglerActivity_) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayOptions(initialOptions);
            actionBar.show();
        }
    }

    @IdRes
    protected abstract int getToolbarId();

    @ActionBar.DisplayOptions
    public int getOption() {
        final int r;
        if (isAdded()) {
            ActionBar actionBar = ((JugglerActivity_) getActivity()).getSupportActionBar();
            if (actionBar == null) {
                r = initialOptions;
            } else {
                r = actionBar.getDisplayOptions();
            }
        } else {
            r = initialOptions;
        }
        return r;
    }

    public void setOptions(@ActionBar.DisplayOptions int options) {
        if (isAdded()) {
            ActionBar actionBar = ((JugglerActivity_) getActivity()).getSupportActionBar();
            if (actionBar == null) {
                initialOptions = options;
            } else {
                actionBar.setDisplayOptions(options);
            }
        } else {
            initialOptions = options;
        }
    }

    public void setNavigationIcon(@DrawableRes int navigationIcon) {
        if (isAdded()) {
            if (navigationIcon != 0) {
                toolbar.setNavigationIcon(navigationIcon);
            }
        } else {
            initialNavigationIcon = navigationIcon;
        }
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    public void setTitle(CharSequence title, int color) {

    }

    public boolean isCustomToolbar() {
        return false;
    }

}
