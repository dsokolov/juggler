package me.ilich.juggler.hello.gui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.ilich.juggler.gui.JugglerFragment;
import me.ilich.juggler.gui.JugglerToolbarFragment;
import me.ilich.juggler.hello.R;

public class StandardToolbarFragmentC extends JugglerToolbarFragment {

    public static StandardToolbarFragmentC create() {
        StandardToolbarFragmentC f = new StandardToolbarFragmentC();
        Bundle b = new Bundle();
        addDisplayOptionsToBundle(b, ActionBar.DISPLAY_SHOW_TITLE);
        //addNavigationIcon(b, android.R.drawable.ic_menu_gallery);
        f.setArguments(b);
        return f;
    }

    public static StandardToolbarFragmentC createTitleBack() {
        StandardToolbarFragmentC f = new StandardToolbarFragmentC();
        Bundle b = new Bundle();
        addDisplayOptionsToBundle(b, ActionBar.DISPLAY_HOME_AS_UP | ActionBar.DISPLAY_SHOW_TITLE);
        //addNavigationIcon(b, android.R.drawable.ic_menu_gallery);
        f.setArguments(b);
        return f;
    }

    public static JugglerFragment createBack() {
        StandardToolbarFragmentC f = new StandardToolbarFragmentC();
        Bundle b = new Bundle();
        addDisplayOptionsToBundle(b, ActionBar.DISPLAY_HOME_AS_UP);
        //addNavigationIcon(b, android.R.drawable.ic_menu_gallery);
        f.setArguments(b);
        return f;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_toolbar_standart, container, false);
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
