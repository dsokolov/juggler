package me.ilich.juggler.hello.gui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.ilich.juggler.CrossActivity;
import me.ilich.juggler.change.Add;
import me.ilich.juggler.change.Remove;
import me.ilich.juggler.gui.JugglerFragment;
import me.ilich.juggler.hello.R;
import me.ilich.juggler.hello.gui.activities.HelloActivity;
import me.ilich.juggler.hello.states.LoginState;
import me.ilich.juggler.hello.states.MainState;

public class ProfileExitFragment extends JugglerFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile_exit, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.yes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateTo().state(Remove.closeAllActivities(), Add.newActivity(new LoginState(), HelloActivity.class));
            }
        });
        view.findViewById(R.id.no).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateTo().state(Remove.closeCurrentActivity());
            }
        });
        view.findViewById(R.id.dig_prev_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CrossActivity.getInstance().addRemove(Remove.dig(MainState.TAG));
                navigateTo().state(Remove.closeCurrentActivity());
            }
        });
    }

}
