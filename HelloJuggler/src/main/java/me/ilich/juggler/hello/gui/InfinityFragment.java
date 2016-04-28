package me.ilich.juggler.hello.gui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import me.ilich.juggler.change.Add;
import me.ilich.juggler.gui.JugglerFragment;
import me.ilich.juggler.hello.R;
import me.ilich.juggler.hello.states.InfinityState;
import me.ilich.juggler.states.TargetBound;

public class InfinityFragment extends JugglerFragment {

    private static final String EXTRA_PARAM = "param";

    public static JugglerFragment create(int i) {
        InfinityFragment infinityFragment = new InfinityFragment();
        Bundle b = new Bundle();
        b.putInt("I", i);
        infinityFragment.setArguments(b);
        return infinityFragment;
    }

    private int i;
    private TextView textView;
    private String s = null;
    private EditText editText;

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
        editText = (EditText) view.findViewById(R.id.edit);
        processI();
        view.findViewById(R.id.next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateTo().state(Add.linear(new InfinityState(i + 1), TargetBound.contentToContent(10)));
            }
        });
        view.findViewById(R.id.prev).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateTo().backState();
            }
        });
        view.findViewById(R.id.send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getTargetFragment() != null) {
                    Intent intent = new Intent().putExtra(EXTRA_PARAM, editText.getText().toString());
                    getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, intent);
                    navigateTo().backState();
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        s = data.getStringExtra(EXTRA_PARAM);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (s != null) {
            editText.setText(s);
            s = null;
        }
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
            i--;
            processI();
            b = true;
        }
        return b;
    }

    @Override
    public boolean onUpPressed() {
        boolean b;
        if (i == 10) {
            b = false;
        } else {
            i++;
            processI();
            b = true;
        }
        return b;
    }

}
