package me.ilich.juggler.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;

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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void setInitialSavedState(SavedState state) {
        super.setInitialSavedState(state);
    }


}
