package me.ilich.juggler.activity;

import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.ViewGroup;

import me.ilich.juggler.R;
import me.ilich.juggler.ScreensManager;

public class LayoutController {

    private JugglerActivity activity;

    private ViewGroup toolbarViewGroup;
    private ViewGroup navigationViewGroup;
    private ViewGroup contentViewGroup;
    private DrawerLayout drawerLayout;

    public LayoutController(JugglerActivity activity) {
        this.activity = activity;
    }

    public void init(@LayoutRes int layoutId) {
        activity.setContentView(layoutId);
        toolbarViewGroup = (ViewGroup) activity.findViewById(getContainerToolbarLayoutId());
        navigationViewGroup = (ViewGroup) activity.findViewById(getContainerNavigationLayoutId());
        contentViewGroup = (ViewGroup) activity.findViewById(getContainerContentLayoutId());
        drawerLayout = (DrawerLayout) activity.findViewById(R.id.drawer_layout);
    }

    @IdRes
    public int getContainerContentLayoutId() {
        return R.id.container_content;
    }

    @IdRes
    public int getContainerToolbarLayoutId() {
        return R.id.container_toolbar;
    }

    @IdRes
    public int getContainerNavigationLayoutId() {
        return R.id.container_navigation;
    }

    public boolean onBackPressed() {
        final boolean b;
        if (drawerLayout == null) {
            b = false;
        } else {
            b = drawerLayout.isDrawerOpen(GravityCompat.START);
            if (b) {
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        }
        return b;
    }

    public DrawerLayout getDrawerLayout() {
        return drawerLayout;
    }

    public ViewGroup getToolbarViewGroup() {
        return toolbarViewGroup;
    }
}
