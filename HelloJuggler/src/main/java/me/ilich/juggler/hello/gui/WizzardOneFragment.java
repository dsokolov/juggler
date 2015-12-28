package me.ilich.juggler.hello.gui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.ilich.juggler.JugglerContentFragment;
import me.ilich.juggler.JugglerNewInstance;
import me.ilich.juggler.ScreensManager;
import me.ilich.juggler.hello.HelloScreensManager;
import me.ilich.juggler.hello.R;

public class WizzardOneFragment extends JugglerContentFragment<HelloScreensManager> {

    @JugglerNewInstance
    public static WizzardOneFragment newInstance(){
        return new WizzardOneFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_wizzard_one, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getScreenManager().wizzardTwo();
            }
        });
    }

}
