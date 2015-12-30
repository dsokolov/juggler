package me.ilich.juggler.fragments.toolbar;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;

import me.ilich.juggler.JugglerActivity;
import me.ilich.juggler.ScreensManager;
import me.ilich.juggler.fragments.JugglerFragment;

public abstract class JugglerToolbarFragment<SM extends ScreensManager> extends JugglerFragment<SM> {

    private Toolbar toolbar;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toolbar = (Toolbar) view.findViewById(getToolbarId());
        ((JugglerActivity) getActivity()).setSupportActionBar(toolbar);
        ActionBar actionBar = ((JugglerActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.show();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        ((JugglerActivity) getActivity()).getJuggler().onToolbarDetached(this);
    }

    @IdRes
    protected abstract int getToolbarId();

    public void setMode(Mode mode) {
        ActionBar actionBar = ((JugglerActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            switch (mode) {
                case MENU:
                    actionBar.setDefaultDisplayHomeAsUpEnabled(true);
                    break;
                case NONE:
                    actionBar.setDisplayOptions(0);
                    break;
                case UP:
                    actionBar.setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP);
                    break;
            }
        }
    }

    @ActionBar.DisplayOptions
    public int getOption() {
        final int r;
        ActionBar actionBar = ((JugglerActivity) getActivity()).getSupportActionBar();
        if (actionBar == null) {
            r = 0;
        } else {
            r = actionBar.getDisplayOptions();
        }
        return r;
    }

    public void setOptions(@ActionBar.DisplayOptions int options) {
        ActionBar actionBar = ((JugglerActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayOptions(options);
        }
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    public enum Mode {
        NONE,
        UP,
        MENU
    }

}
