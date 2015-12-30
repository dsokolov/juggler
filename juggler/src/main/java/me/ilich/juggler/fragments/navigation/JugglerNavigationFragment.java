package me.ilich.juggler.fragments.navigation;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import me.ilich.juggler.JugglerActivity;
import me.ilich.juggler.R;
import me.ilich.juggler.ScreensManager;
import me.ilich.juggler.fragments.JugglerFragment;

public abstract class JugglerNavigationFragment<SM extends ScreensManager> extends JugglerFragment<SM> {

    private ActionBarDrawerToggle drawerToggle;

    @Override
    public void onDetach() {
        super.onDetach();
        ((JugglerActivity) getActivity()).getJuggler().onNavigationDetached(this);
    }

    public void init(DrawerLayout drawerLayout, Toolbar toolbar) {
        drawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getActivity().invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getActivity().invalidateOptionsMenu();
            }
        };

        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }

}
