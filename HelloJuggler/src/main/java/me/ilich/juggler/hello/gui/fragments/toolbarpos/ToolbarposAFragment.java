package me.ilich.juggler.hello.gui.fragments.toolbarpos;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.ilich.juggler.gui.JugglerToolbarFragment;
import me.ilich.juggler.hello.R;

public class ToolbarposAFragment extends JugglerToolbarFragment {

    public static ToolbarposAFragment create() {
        ToolbarposAFragment f = new ToolbarposAFragment();
        f.setArguments(addDisplayOptionsToBundle(null, ActionBar.DISPLAY_SHOW_TITLE | ActionBar.DISPLAY_HOME_AS_UP));
        return f;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_toolbarpos_a, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Controller.assing(navigateTo(), view);
    }

    @Override
    protected int getToolbarId() {
        return R.id.toolbar;
    }

}
