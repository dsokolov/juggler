package me.ilich.juggler;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;

public abstract class JugglerToolbarFragment<SM extends ScreensManager> extends Fragment {

    private Toolbar toolbar;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toolbar = (Toolbar) view.findViewById(getToolbarId());
        ((JugglerActivity) getActivity()).setSupportActionBar(toolbar);
        ActionBar actionBar = ((JugglerActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("123");
            actionBar.show();
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }


    protected SM getScreenManager() {
        return ((JugglerActivity<SM>) getActivity()).getScreensManager();
    }

    @IdRes
    protected abstract int getToolbarId();

}
