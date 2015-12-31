package me.ilich.juggler.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

import me.ilich.juggler.Juggler;
import me.ilich.juggler.JugglerActivity;
import me.ilich.juggler.Screen;
import me.ilich.juggler.ScreensManager;

public abstract class JugglerFragment<SM extends ScreensManager> extends Fragment {

    public Juggler<SM> getJuggler() {
        return ((JugglerActivity<SM>) getActivity()).getJuggler();
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
        getJuggler().onAttach(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        getJuggler().onDetach(this);
    }

}
