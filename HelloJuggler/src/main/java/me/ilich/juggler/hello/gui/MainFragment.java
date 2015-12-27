package me.ilich.juggler.hello.gui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.ilich.juggler.JugglerContentFragment;
import me.ilich.juggler.JugglerNewInstance;
import me.ilich.juggler.hello.R;
import me.ilich.juggler.hello.HelloScreensManager;
import me.ilich.juggler.hello.screens.MainScreen;

public class MainFragment extends JugglerContentFragment<HelloScreensManager> {

    @JugglerNewInstance
    public static MainFragment create() {
        return new MainFragment();
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
                getScreenManager().list();
            }
        });
        view.findViewById(R.id.navigate_to_about).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*getJuggler().getScreenManager().about();*/
            }
        });
        view.findViewById(R.id.navigate_to_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*getJuggler().getScreenManager().login();*/
            }
        });
        getScreenManager().setTitle("main");
    }
}
