package me.ilich.juggler.gui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;

import me.ilich.juggler.Juggler;
import me.ilich.juggler.Navigable;

public class JugglerFragment extends Fragment {

    private static final String STATE_TARGET_CELL_TYPE = "target_cell_type";

    private JugglerActivity activity;
    private int targetCellType;


    @Override
    @CallSuper
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.v(Juggler.TAG, "onAttach " + this);
        if (!(getActivity() instanceof JugglerActivity)) {
            throw new RuntimeException("JugglerFragment can be attached only to JugglerActivity");
        }
        activity = (JugglerActivity) getActivity();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(Juggler.TAG, "onCreate " + this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.v(Juggler.TAG, "onActivityCreated " + this);
        if (savedInstanceState != null) {
            targetCellType = savedInstanceState.getInt(STATE_TARGET_CELL_TYPE);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.v(Juggler.TAG, "onStart " + this);
        getJugglerActivity().getJuggler().onFragmentStart(this); //TODO при реюзе не вызывается. перенести в onResume?
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.v(Juggler.TAG, "onStop " + this);
        getJugglerActivity().getJuggler().onFragmentStop(this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE_TARGET_CELL_TYPE, targetCellType);

    }

    protected JugglerActivity getJugglerActivity() {
        return activity;
    }

    protected Navigable navigateTo() {
        return getJugglerActivity().getJuggler();
    }

    /**
     * Called when back button pressed
     *
     * @return true if press processed
     * false by default
     */
    public boolean onBackPressed() {
        return false;
    }

    public boolean onUpPressed() {
        return false;
    }

    @Override
    @CallSuper
    public void onDetach() {
        super.onDetach();
    }

    public int getTargetCell() {
        return targetCellType;
    }

    public void setTargetCellType(int cellType) {
        this.targetCellType = cellType;
    }

    public boolean isReusable() {
        return false;
    }

}
