package me.ilich.juggler.gui;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public abstract class JugglerToolbarFragment extends JugglerFragment {

    private static final int NOT_SET = -1;

    private static final String EXTRA_OPTIONS = "options";
    private static final String EXTRA_NAVIGATION_ICON = "navigation_icon";

    protected static Bundle addDisplayOptionsToBundle(Bundle bundle, @ActionBar.DisplayOptions int displayOptions) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putInt(EXTRA_OPTIONS, displayOptions);
        return bundle;
    }

    protected static Bundle addNavigationIcon(Bundle bundle, @DrawableRes int navigationIcon) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putInt(EXTRA_NAVIGATION_ICON, navigationIcon);
        return bundle;
    }

    private Toolbar toolbar;
    @Nullable
    private ActionBar actionBar;
    private Handler handler;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handler = new Handler();
    }

    @Override
    @SuppressWarnings("all")
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        @ActionBar.DisplayOptions final int opt;
        @DrawableRes final int navigationIcon;
        if (getArguments() != null) {
            opt = getArguments().getInt(EXTRA_OPTIONS, 0);
            navigationIcon = getArguments().getInt(EXTRA_NAVIGATION_ICON, NOT_SET);
        } else {
            opt = 0;
            navigationIcon = NOT_SET;
        }
        toolbar = (Toolbar) view.findViewById(getToolbarId());
        getJugglerActivity().setSupportActionBar(toolbar);
        actionBar = getJugglerActivity().getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayOptions(opt);
            actionBar.show();
        }
        if (navigationIcon != NOT_SET) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    toolbar.setNavigationIcon(navigationIcon);
                }
            };
            handler.postDelayed(runnable, 1);
        }
    }

    @IdRes
    protected abstract int getToolbarId();

    public Toolbar getToolbar() {
        return toolbar;
    }

}
