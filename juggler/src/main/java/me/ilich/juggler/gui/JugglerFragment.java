package me.ilich.juggler.gui;

import android.support.v4.app.Fragment;

import me.ilich.juggler.Juggler;
import me.ilich.juggler.Navigable;

public class JugglerFragment extends Fragment {

    private Juggler juggler = Juggler.getInstance();

    protected Navigable navigateTo() {
        return juggler;
    }

    public boolean onBackPressed() {
        return false;
    }

}
