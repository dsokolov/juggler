package me.ilich.juggler.hello.gui;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.ilich.juggler.change.ClearPopCondition;
import me.ilich.juggler.change.DeeperAdd;
import me.ilich.juggler.gui.JugglerFragment;
import me.ilich.juggler.hello.R;
import me.ilich.juggler.hello.states.MainState;

public class OnlyContentFragment extends JugglerFragment {

    public static JugglerFragment create() {
        return new OnlyContentFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_content_only, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                navigateTo().state(new ClearPopCondition(), new DeeperAdd(new MainState(), null));
            }
        }, 1000);
    }

}
