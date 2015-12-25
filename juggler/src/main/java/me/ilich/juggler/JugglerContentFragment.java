package me.ilich.juggler;

import android.support.v4.app.Fragment;

public abstract class JugglerContentFragment<SM extends ScreensManager> extends Fragment {

    protected Juggler<SM> getJuggler() {
        return ((JugglerActivity<SM>) getActivity()).getJuggler();
    }

}
