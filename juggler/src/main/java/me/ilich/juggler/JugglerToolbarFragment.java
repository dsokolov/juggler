package me.ilich.juggler;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;

public abstract class JugglerToolbarFragment extends JugglerFragment {

    private Toolbar toolbar;
    private JugglerActivity jugglerActivity;
    @ActionBar.DisplayOptions
    private int initialOptions = 0;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        jugglerActivity = (JugglerActivity) getActivity();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toolbar = (Toolbar) view.findViewById(getToolbarId());
        jugglerActivity.setSupportActionBar(toolbar);
        ActionBar actionBar = jugglerActivity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayOptions(initialOptions);
            actionBar.show();
        }
    }

    @IdRes
    protected abstract int getToolbarId();

    public Toolbar getToolbar() {
        return toolbar;
    }

    public final void setDisplayOptions(@ActionBar.DisplayOptions int options) {
        if (isAdded()) {
            ActionBar actionBar = jugglerActivity.getSupportActionBar();
            if (actionBar == null) {
                initialOptions = options;
            } else {
                actionBar.setDisplayOptions(options);
            }
        } else {
            initialOptions = options;
        }
    }

}
