package me.ilich.juggler.hello.gui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.ilich.juggler.Log;
import me.ilich.juggler.gui.JugglerFragment;
import me.ilich.juggler.hello.R;

public class FragmentC extends JugglerFragment {

    public static FragmentC newInstance() {
        return new FragmentC();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_c, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.v(getClass(), "actionbar = " + getJugglerActivity().getSupportActionBar());
    }

}
