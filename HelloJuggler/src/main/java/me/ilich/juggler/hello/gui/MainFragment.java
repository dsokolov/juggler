package me.ilich.juggler.hello.gui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import me.ilich.juggler.fragments.content.JugglerContentFragment;
import me.ilich.juggler.fragments.toolbar.JugglerToolbarFragment;
import me.ilich.juggler.hello.R;
import me.ilich.juggler.hello.screens.HelloScreensManager;
import me.ilich.juggler.hello.screens.MainScreen;

public class MainFragment extends JugglerContentFragment<HelloScreensManager> {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.navigate_to_list).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateTo(MainScreen.class).list();
            }
        });
        view.findViewById(R.id.navigate_to_about).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateTo(MainScreen.class).about();
            }
        });
        view.findViewById(R.id.navigate_to_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateTo(MainScreen.class).login();
            }
        });
        view.findViewById(R.id.navigate_to_wizzard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateTo(MainScreen.class).wizardOne();
            }
        });
        view.findViewById(R.id.navigate_to_toolbar_explain).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateTo(MainScreen.class).toolbarExplain();
            }
        });
        getActivity().setTitle("main screen");
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final boolean b;
        switch (item.getItemId()) {
            case R.id.about:
                Toast.makeText(getContext(), "about", Toast.LENGTH_SHORT).show();
                b = true;
                break;
            default:
                b = super.onOptionsItemSelected(item);
                break;
        }
        return b;
    }
}
