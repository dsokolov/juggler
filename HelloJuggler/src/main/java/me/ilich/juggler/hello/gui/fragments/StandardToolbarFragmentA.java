package me.ilich.juggler.hello.gui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import me.ilich.juggler.gui.JugglerFragment;
import me.ilich.juggler.gui.JugglerToolbarFragment;
import me.ilich.juggler.hello.R;

public class StandardToolbarFragmentA extends JugglerToolbarFragment {

    public static StandardToolbarFragmentA create() {
        StandardToolbarFragmentA f = new StandardToolbarFragmentA();
        Bundle b = new Bundle();
        addDisplayOptionsToBundle(b, ActionBar.DISPLAY_SHOW_TITLE);
        //addNavigationIcon(b, android.R.drawable.ic_menu_gallery);
        f.setArguments(b);
        return f;
    }

    public static StandardToolbarFragmentA createTitleBack() {
        StandardToolbarFragmentA f = new StandardToolbarFragmentA();
        Bundle b = new Bundle();
        addDisplayOptionsToBundle(b, ActionBar.DISPLAY_HOME_AS_UP | ActionBar.DISPLAY_SHOW_TITLE);
        //addNavigationIcon(b, android.R.drawable.ic_menu_gallery);
        f.setArguments(b);
        return f;
    }

    public static JugglerFragment createBack() {
        StandardToolbarFragmentA f = new StandardToolbarFragmentA();
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
