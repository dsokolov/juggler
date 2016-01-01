package me.ilich.juggler.hello.gui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.ilich.juggler.fragments.content.JugglerContentFragment;
import me.ilich.juggler.fragments.toolbar.JugglerToolbarFragment;
import me.ilich.juggler.hello.R;
import me.ilich.juggler.hello.screens.HelloScreensManager;
import me.ilich.juggler.hello.screens.MainScreen;

public class MainFragment extends JugglerContentFragment<HelloScreensManager> {

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
        getActivity().setTitle("main");
    }
}
