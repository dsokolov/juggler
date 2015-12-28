package me.ilich.juggler.hello.gui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.ilich.juggler.JugglerContentFragment;
import me.ilich.juggler.JugglerNewInstance;
import me.ilich.juggler.hello.HelloScreensManager;
import me.ilich.juggler.hello.R;

public class WizzardTwoFragment extends JugglerContentFragment<HelloScreensManager> {

    @JugglerNewInstance
    public static WizzardTwoFragment newInstance(){
        return new WizzardTwoFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_wizzard_two, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getScreenManager().wizzardThree();
            }
        });
    }
}
