package me.ilich.juggler.fragments.navigation;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import me.ilich.juggler.R;
import me.ilich.juggler.fragments.JugglerFragment_;

public abstract class JugglerNavigationFragment extends JugglerFragment_ {

    private ActionBarDrawerToggle drawerToggle;
    private int initialSelectedItem;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        onSetSelectedItem(initialSelectedItem);
    }

    public void init(DrawerLayout drawerLayout, Toolbar toolbar) {
        if (drawerLayout != null) {
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

            //drawerToggle.setDrawerIndicatorEnabled(true);
            drawerLayout.setDrawerListener(drawerToggle);
            drawerToggle.syncState();
            //drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        }
    }

    public void deinit(DrawerLayout drawerLayout) {
        //drawerToggle.setDrawerIndicatorEnabled(false);
        drawerToggle = null;
        if (drawerLayout != null) {
            drawerLayout.setDrawerListener(null);
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        }
    }

    public void setSelectedItem(int item) {
        if (isAdded()) {
            onSetSelectedItem(item);
        } else {
            initialSelectedItem = item;
        }
    }

    protected abstract void onSetSelectedItem(int itemId);

}
