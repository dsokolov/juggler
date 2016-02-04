package me.ilich.juggler;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

public abstract class JugglerToolbarFragment extends JugglerFragment {

    private static final String STATE_OPTIONS = "state_options";

    private Toolbar toolbar;
    @Nullable
    private ActionBar actionBar;
    private AppCompatActivity activity;
    @ActionBar.DisplayOptions
    private int initialOptions = 0;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (AppCompatActivity) getActivity();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toolbar = (Toolbar) view.findViewById(getToolbarId());
        activity.setSupportActionBar(toolbar);
        actionBar = activity.getSupportActionBar();
        if (actionBar != null) {
            if (savedInstanceState != null) {
                int options = savedInstanceState.getInt(STATE_OPTIONS, initialOptions);
                actionBar.setDisplayOptions(options);
            } else {
                actionBar.setDisplayOptions(initialOptions);
            }
            actionBar.show();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (actionBar != null) {
            outState.putInt(STATE_OPTIONS, actionBar.getDisplayOptions());
        } else {
            Log.w("Sokolov", "null");
        }
    }

    @IdRes
    protected abstract int getToolbarId();

    public Toolbar getToolbar() {
        return toolbar;
    }

    public final void setDisplayOptions(@ActionBar.DisplayOptions int options) {
        if (isAdded()) {
            ActionBar actionBar = activity.getSupportActionBar();
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
