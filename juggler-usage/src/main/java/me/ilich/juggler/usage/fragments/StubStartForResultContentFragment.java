package me.ilich.juggler.usage.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import me.ilich.juggler.change.Add;
import me.ilich.juggler.gui.JugglerFragment;
import me.ilich.juggler.usage.MainActivity;
import me.ilich.juggler.usage.R;
import me.ilich.juggler.usage.StateFactory;

public class StubStartForResultContentFragment extends JugglerFragment {

    public static JugglerFragment create() {
        return new StubStartForResultContentFragment();
    }

    private TextView resultCodeTextView;
    private TextView requestCodeTextView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_stub_start_for_result_content, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.start_for_result).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateTo().state(Add.newActivityForResult(StateFactory.contentOnlyState(StubSetResultContentFragment.class), MainActivity.class, 123));
            }
        });
        resultCodeTextView = (TextView) view.findViewById(R.id.result_code);
        requestCodeTextView = (TextView) view.findViewById(R.id.request_code);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        resultCodeTextView.setText(Integer.toString(resultCode));
        requestCodeTextView.setText(Integer.toString(requestCode));
    }
}
