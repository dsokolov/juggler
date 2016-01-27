package me.ilich.juggler.hello.gui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.ilich.juggler.JugglerFragment;
import me.ilich.juggler.hello.R;
import me.ilich.juggler.hello.states.MainState;
import me.ilich.juggler.hello.states.WizardTwoState;
import me.ilich.juggler.old.fragments.JugglerNewInstance;

public class WizardThreeFragment extends JugglerFragment {

    @JugglerNewInstance
    public static WizardThreeFragment newInstance() {
        return new WizardThreeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_wizard_three, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateTo().changeState(new MainState());
            }
        });
        view.findViewById(R.id.prev).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateTo().changeState(new WizardTwoState());
            }
        });
    }

}
