package me.ilich.juggler.gui;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.CallSuper;
import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import me.ilich.juggler.R;
import me.ilich.juggler.states.State;

public abstract class JugglerNavigationFragment extends JugglerFragment {

    private static final String ARG_SELECTED_ITEM = "selected_item";
    private static final String ARG_DRAWER_GRAVITY = "drawer_gravity";


    private int gravity = GravityCompat.START;
    private int defaultSelectedItem = 0;
    private ActionBarDrawerToggle drawerToggle;
    private DrawerLayout drawerLayout;

    public JugglerNavigationFragment() {
        super();
    }

    protected static Bundle addSelectedItemToBundle(@Nullable Bundle bundle, int itemIndex) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putInt(ARG_SELECTED_ITEM, itemIndex);
        return bundle;
    }

    public static Bundle addDrawerGravityToBundle(@Nullable Bundle bundle, int gravity) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putInt(ARG_DRAWER_GRAVITY, gravity);
        return bundle;
    }

    @Override
    @CallSuper
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (getArguments() != null) {
            defaultSelectedItem = getArguments().getInt(ARG_SELECTED_ITEM, 0);
            gravity = getArguments().getInt(ARG_DRAWER_GRAVITY, GravityCompat.START);
        }
    }

    protected final int getDefaultSelectedItem() {
        return defaultSelectedItem;
    }

    @Override
    public boolean onUpPressed() {
        final boolean b;
        if (drawerLayout.isDrawerOpen(gravity)) {
            b = false;
        } else {
            b = true;
            drawerLayout.openDrawer(gravity);
        }
        return b;
    }

    @Override
    @CallSuper
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        drawerLayout = (DrawerLayout) getActivity().findViewById(getDrawerLayoutId());
        drawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, getOpen(), getClose());
        drawerLayout.addDrawerListener(drawerToggle);


        State state = getState();
        if (state != null) {
            drawerToggle.setDrawerIndicatorEnabled(false);
            drawerToggle.setHomeAsUpIndicator(state.getUpNavigationIcon(getContext()));
            //TODO нужен ли этот код? вроде же обработчик в активити в onSupportNavigateUp и onOptionsItemSelected
/*            drawerToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (drawerLayout.isDrawerVisible(gravity)) {
                        drawerLayout.closeDrawer(gravity);
                    } else {
                        drawerLayout.openDrawer(gravity);
                    }
                }
            });*/
        }

        drawerToggle.syncState();
    }

    public DrawerLayout getDrawerLayout() {
        return drawerLayout;
    }

    @IdRes
    protected int getDrawerLayoutId() {
        return R.id.drawer_layout;
    }

    @StringRes
    protected int getOpen() {
        return R.string.drawer_open;
    }

    @StringRes
    protected int getClose() {
        return R.string.drawer_close;
    }

    @Override
    @CallSuper
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    @CallSuper
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        boolean b = drawerToggle.onOptionsItemSelected(menuItem);
        if (!b) {
            b = super.onOptionsItemSelected(menuItem);
        }
        return b;
    }

    /**
     * Use {@link #closeDrawer() closeDrawer()} instead.
     * Deprecated since 27.07.2016
     */
    @Deprecated
    public void close() {
        drawerLayout.closeDrawer(gravity);
    }

    public void openDrawer() {
        drawerLayout.openDrawer(gravity);
    }

    public void closeDrawer() {
        drawerLayout.closeDrawer(gravity);
    }

}
