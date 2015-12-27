package me.ilich.juggler;

import android.support.v4.app.Fragment;

public abstract class JugglerContentFragment<SM extends ScreensManager> extends Fragment {

    protected SM getScreenManager(){
        return ((JugglerActivity<SM>)getActivity()).getScreensManager();
    }

 /*   protected Juggler<SM> getJuggler() {
        return ((JugglerActivity<SM>) getActivity()).getJuggler();
    }*/

}
