package me.ilich.juggler.hello.gui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import me.ilich.juggler.gui.JugglerFragment;
import me.ilich.juggler.hello.R;
import me.ilich.juggler.hello.states.InfinityState;

public class InfinityFragment extends JugglerFragment {

    public static JugglerFragment create(int i) {
        InfinityFragment infinityFragment = new InfinityFragment();
        Bundle b = new Bundle();
        b.putInt("I", i);
        infinityFragment.setArguments(b);
        return infinityFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_infinity, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final int i = getArguments().getInt("I", -10);
        ((TextView) view.findViewById(R.id.text)).setText(i + "");
        view.findViewById(R.id.next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateTo().linearState(new InfinityState(i + 1));
            }
        });
        view.findViewById(R.id.prev).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateTo().backState();
            }
        });
    }

}
