package me.ilich.juggler.hello.gui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.ilich.juggler.change.Add;
import me.ilich.juggler.change.Remove;
import me.ilich.juggler.gui.JugglerFragment;
import me.ilich.juggler.hello.R;
import me.ilich.juggler.hello.states.StateB;

public class FragmentA extends JugglerFragment {

    public static FragmentA newInstance() {
        return new FragmentA();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_a, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.navigate_to_b).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateTo().state(Remove.none(), Add.deeper(new StateB()));
            }
        });
    }

    @Override
    public boolean onBackPressed() {
        navigateTo().backState();
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
        getJugglerActivity().getJuggler().dump();
    }

}
