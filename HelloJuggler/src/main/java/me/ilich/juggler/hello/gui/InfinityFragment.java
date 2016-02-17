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

    private int i;
    private TextView textView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        i = getArguments().getInt("I", -10);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_infinity, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textView = (TextView) view.findViewById(R.id.text);
        processI();
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

    private void processI() {
        textView.setText(i + "");
    }

    @Override
    public boolean onBackPressed() {
        boolean b;
        if (i == 0) {
            b = false;
        } else {
            i = 0;
            processI();
            b = true;
        }
        return b;
    }
}
