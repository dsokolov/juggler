package me.ilich.juggler.gui;

import android.support.v4.app.Fragment;

import me.ilich.juggler.Juggler;
import me.ilich.juggler.Navigable;

public class JugglerFragment extends Fragment {

    private Juggler juggler = Juggler.getInstance();

    protected Navigable navigateTo() {
        return juggler;
    }

    /**
     * Called when back button pressed
     * @return true if press processed
     * false by default
     */
    public boolean onBackPressed() {
        return false;
    }

}
