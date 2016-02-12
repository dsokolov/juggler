package me.ilich.juggler.hello.gui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.ilich.juggler.gui.JugglerToolbarFragment;
import me.ilich.juggler.hello.R;

public class StandardToolbarFragment extends JugglerToolbarFragment {

    public static StandardToolbarFragment create(@ActionBar.DisplayOptions int displayOptions) {
        StandardToolbarFragment f = new StandardToolbarFragment();
        Bundle b = new Bundle();
        addDisplayOptionsToBundle(b, displayOptions);
        addNavigationIcon(b, android.R.drawable.ic_menu_gallery);
        f.setArguments(b);
        return f;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_toolbar_standart, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getToolbar().setNavigationIcon(android.R.drawable.ic_menu_camera);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected int getToolbarId() {
        return R.id.toolbar;
    }

}
