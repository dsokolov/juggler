package me.ilich.juggler.gui;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;

import me.ilich.juggler.Log;
import me.ilich.juggler.states.State;

public abstract class JugglerToolbarFragment extends JugglerFragment {

    private static final String EXTRA_OPTIONS = "options";

    /**
     * Use {@link State#getToolbarDisplayOptions()}  State.getToolbarDisplayOptions()} instead.
     * Deprecated since 03.08.2016
     */
    @Deprecated
    public static Bundle addDisplayOptionsToBundle(Bundle bundle, @ActionBar.DisplayOptions int displayOptions) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putInt(EXTRA_OPTIONS, displayOptions);
        return bundle;
    }

    private Toolbar toolbar;

    @Override
    @SuppressWarnings("all")
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        @ActionBar.DisplayOptions int opt = 0;
        if (getArguments() != null) {
            opt = getArguments().getInt(EXTRA_OPTIONS, 0);
        }
        if (opt == 0) {
            opt = getState().getToolbarDisplayOptions();
        }
        toolbar = (Toolbar) view.findViewById(getToolbarId());
        if (getState() != null) {
            toolbar.setNavigationIcon(getState().getUpNavigationIcon(getContext()));
        }
        getJugglerActivity().setSupportActionBar(toolbar);
        ActionBar actionBar = getJugglerActivity().getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayOptions(opt);
            actionBar.show();
        }
    }

    @IdRes
    protected abstract int getToolbarId();

    public Toolbar getToolbar() {
        return toolbar;
    }

}
