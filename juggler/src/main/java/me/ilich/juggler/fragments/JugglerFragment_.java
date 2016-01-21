package me.ilich.juggler.fragments;

import android.content.Context;
import android.support.v4.app.Fragment;

import me.ilich.juggler.Juggler_;
import me.ilich.juggler.JugglerActivity_;

public abstract class JugglerFragment_ extends Fragment {

    public Juggler_ getJuggler() {
        if (getActivity() == null) {
            throw new NullPointerException("activity");
        }
        if (getActivity() instanceof JugglerActivity_) {
            return ((JugglerActivity_) getActivity()).getJuggler();
        } else {
            throw new RuntimeException("activity should be instance of JugglerActivity");
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Juggler_ juggler = getJuggler();
        if (juggler == null) {
            throw new NullPointerException("juggler");
        }
        getJuggler().onAttach(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        getJuggler().onDetach(this);
    }

}
