package me.ilich.juggler;

import android.support.v4.app.Fragment;

public class JugglerFragment extends Fragment {

    private Juggler juggler = Juggler.getInstance();

    protected Navigable navigateTo() {
        return juggler;
    }

}
