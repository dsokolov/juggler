package me.ilich.juggler.gui;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;

public abstract class JugglerToolbarFragment extends JugglerFragment {

    private static final String EXTRA_OPTIONS = "options";

    protected static Bundle addDisplayOptionsToBundle(Bundle bundle, @ActionBar.DisplayOptions int displayOptions) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putInt(EXTRA_OPTIONS, displayOptions);
        return bundle;
    }

    private Toolbar toolbar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    @SuppressWarnings("all")
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        @ActionBar.DisplayOptions final int opt;
        if (getArguments() != null) {
            opt = getArguments().getInt(EXTRA_OPTIONS, 0);
        } else {
            opt = 0;
        }
        toolbar = (Toolbar) view.findViewById(getToolbarId());
        getJugglerActivity().setSupportActionBar(toolbar);
        ActionBar actionBar = getJugglerActivity().getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayOptions(opt);
            actionBar.show();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
/*        ActionBar actionBar = getJugglerActivity().getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        getJugglerActivity().setSupportActionBar(null);*/
    }

    @IdRes
    protected abstract int getToolbarId();

    public Toolbar getToolbar() {
        return toolbar;
    }

}
