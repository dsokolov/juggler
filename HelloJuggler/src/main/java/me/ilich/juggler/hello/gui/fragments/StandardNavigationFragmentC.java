package me.ilich.juggler.hello.gui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.navigation.NavigationView;

import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;
import me.ilich.juggler.change.Add;
import me.ilich.juggler.change.Remove;
import me.ilich.juggler.gui.JugglerNavigationFragment;
import me.ilich.juggler.hello.R;
import me.ilich.juggler.hello.states.AboutState;
import me.ilich.juggler.hello.states.MainState;
import me.ilich.juggler.hello.states.NoTitleNavagationState;
import me.ilich.juggler.hello.states.StateA;
import me.ilich.juggler.hello.states.StateB;
import me.ilich.juggler.hello.states.StateC;

public class StandardNavigationFragmentC extends JugglerNavigationFragment {

    public static StandardNavigationFragmentC create(int itemId) {
        StandardNavigationFragmentC f = new StandardNavigationFragmentC();
        Bundle b = new Bundle();
        addSelectedItemToBundle(b, itemId);
        f.setArguments(b);
        return f;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_navigation_standard, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        NavigationView navigationView = (NavigationView) view.findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                final boolean b;
                switch (item.getItemId()) {
                    case R.id.menu_main:
                        navigateTo().state(Remove.dig(MainState.TAG));
                        b = true;
                        break;
                    case R.id.menu_state_a:
                        navigateTo().state(Remove.dig(MainState.TAG), Add.deeper(new StateA()));
                        b = true;
                        break;
                    case R.id.menu_state_b:
                        navigateTo().state(Remove.dig(MainState.TAG), Add.deeper(new StateB()));
                        b = true;
                        break;
                    case R.id.menu_state_c:
                        navigateTo().state(Remove.dig(MainState.TAG), Add.deeper(new StateC()));
                        b = true;
                        break;
                    case R.id.menu_about:
                        navigateTo().state(Remove.dig(MainState.TAG), Add.deeper(new AboutState()));
                        b = true;
                        break;
                    case R.id.menu_only_content:
                        navigateTo().state(Remove.dig(MainState.TAG), Add.deeper(new NoTitleNavagationState()));
                        b = true;
                        break;
                    default:
                        b = false;
                        break;
                }
                close();
                return b;
            }
        });
        //navigationView.getMenu().getItem(getDefaultSelectedItem()).setChecked(true);

        getDrawerLayout().addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
    }

}
