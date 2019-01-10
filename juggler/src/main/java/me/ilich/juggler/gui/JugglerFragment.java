package me.ilich.juggler.gui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.CallSuper;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import me.ilich.juggler.Navigable;
import me.ilich.juggler.states.State;

public class JugglerFragment extends Fragment {

    private static final String STATE_TARGET_CELL_TYPE = "target_cell_type";
    private static final String STATE_STATE = "state";

    private JugglerActivity activity;
    private int targetCellType;
    @Nullable
    private State<?> state = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            state = (State<?>) savedInstanceState.getSerializable(STATE_STATE);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        //Log.v(this, "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        //Log.v(getClass(), "onResume");
        //getJugglerActivity().getJuggler().onFragmentResume(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        //Log.v(getClass(), "onPause");
        //getJugglerActivity().getJuggler().onFragmentPause(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        //Log.v(getClass(), "onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //Log.v(getClass(), "onDestroy");
    }

    @Override
    @CallSuper
    public void onAttach(Context context) {
        super.onAttach(context);
        //Log.v(getClass(), "onAttach");
        if (!(getActivity() instanceof JugglerActivity)) {
            throw new RuntimeException("JugglerFragment can be attached only to JugglerActivity");
        }
        activity = (JugglerActivity) getActivity();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //Log.v(getClass(), "onActivityCreated");
        if (savedInstanceState != null) {
            targetCellType = savedInstanceState.getInt(STATE_TARGET_CELL_TYPE);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Log.v(getClass(), "onViewCreated");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Log.v(getClass(), "onCreateView");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //Log.v(getClass(), "onDestroyView");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE_TARGET_CELL_TYPE, targetCellType);
        outState.putSerializable(STATE_STATE, state);
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
        activity = null;
    }

    public int getTargetCell() {
        return targetCellType;
    }

    public void setTargetCellType(int cellType) {
        this.targetCellType = cellType;
    }

    public void setState(@Nullable State<?> state) {
        this.state = state;
    }

    @Nullable
    public State<? extends State.Params> getState() {
        return state;
    }

}
