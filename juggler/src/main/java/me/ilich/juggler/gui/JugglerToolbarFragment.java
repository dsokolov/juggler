package me.ilich.juggler.gui;

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

    private static final String EXTRA_OPTIONS = "options";

    protected static Bundle addDisplayOptionsToBundle(Bundle bundle, @ActionBar.DisplayOptions int displayOptions) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putInt(EXTRA_OPTIONS, displayOptions);
        return bundle;
    }

    private Toolbar toolbar;
    @Nullable
    private ActionBar actionBar;
    private AppCompatActivity activity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (AppCompatActivity) getActivity();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        @ActionBar.DisplayOptions int opt = getArguments().getInt(EXTRA_OPTIONS);
        toolbar = (Toolbar) view.findViewById(getToolbarId());
        activity.setSupportActionBar(toolbar);
        actionBar = activity.getSupportActionBar();
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
