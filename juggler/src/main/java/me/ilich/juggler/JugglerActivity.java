package me.ilich.juggler;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import me.ilich.juggler.fragments.content.JugglerContentFragment;
import me.ilich.juggler.fragments.navigation.JugglerNavigationFragment;
import me.ilich.juggler.fragments.toolbar.JugglerToolbarFragment;

public abstract class JugglerActivity<SM extends ScreensManager> extends AppCompatActivity {

    private Juggler<SM> juggler;
    private ViewGroup toolbarViewGroup;
    private ViewGroup navigationViewGroup;
    private ViewGroup contentViewGroup;
    private DrawerLayout drawerLayout;

    @Override
    @CallSuper
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        juggler = new Juggler<>(createScreenManager(), this);
        setContentView(getContentViewId());
        toolbarViewGroup = (ViewGroup) findViewById(getContainerToolbarLayoutId());
        navigationViewGroup = (ViewGroup) findViewById(getContainerNavigationLayoutId());
        contentViewGroup = (ViewGroup) findViewById(getContainerContentLayoutId());

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
    }

    @LayoutRes
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    protected abstract SM createScreenManager();

    public final Juggler<SM> getJuggler() {
        return juggler;
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

    public void hideToolbarContainer() {
        toolbarViewGroup.setVisibility(View.GONE);
    }

    public void showToolbarContainer() {
        toolbarViewGroup.setVisibility(View.VISIBLE);
    }

    public void hideNavigationContainer() {
        navigationViewGroup.setVisibility(View.GONE);
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

    public void showNavigationContainer() {
        navigationViewGroup.setVisibility(View.VISIBLE);
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
    }

    @Override
    public void onBackPressed() {
        boolean b = juggler.getScreenManager().back();
        if (!b) {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        return juggler.getScreenManager().up();
    }

    public <S> S navigateTo(Class<S> sClass) {
        ScreensManager screensManager = juggler.getScreenManager();
        if (!sClass.isAssignableFrom(screensManager.getClass())) {
            throw new RuntimeException("ScreenManager should implements " + sClass);
        }
        return (S) screensManager;
    }

    public DrawerLayout getDrawerLayout() {
        return drawerLayout;
    }

}
