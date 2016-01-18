package me.ilich.juggler.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import me.ilich.juggler.Juggler;
import me.ilich.juggler.Transition;
import me.ilich.juggler.activity.JugglerActivity;
import me.ilich.juggler.Screen;
import me.ilich.juggler.ScreensManager;

public abstract class JugglerFragment<SM extends ScreensManager> extends Fragment {

    public Juggler<SM> getJuggler() {
        if (getActivity() == null) {
            throw new NullPointerException("activity");
        }
        if (getActivity() instanceof JugglerActivity) {
            return ((JugglerActivity<SM>) getActivity()).getJuggler();
        } else {
            throw new RuntimeException("activity should be instance of JugglerActivity");
        }
    }

    public <S extends Screen> S navigateTo(Class<S> sClass) {
        ScreensManager screensManager = ((JugglerActivity) getActivity()).getJuggler().getScreenManager();
        if (!sClass.isAssignableFrom(screensManager.getClass())) {
            throw new RuntimeException("ScreenManager should implements " + sClass);
        }
        return (S) screensManager;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Juggler juggler = getJuggler();
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
