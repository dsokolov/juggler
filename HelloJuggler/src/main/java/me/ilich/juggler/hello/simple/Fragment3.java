package me.ilich.juggler.hello.simple;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import me.ilich.juggler.hello.R;

public class Fragment3 extends Fragment {

    private EditText editText;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v("Sokolov", "Fragment3 onCreate");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.v("Sokolov", "Fragment3 onActivityCreated");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.v("Sokolov", "Fragment3 onCreateView");
        return inflater.inflate(R.layout.framgment_simple_3, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.v("Sokolov", "Fragment3 onViewCreated");
        editText = (EditText) view.findViewById(R.id.edit2);
        view.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = editText.getText().toString();
                getTargetFragment().onActivityResult(getTargetRequestCode(), 0, new Intent().putExtra("A", s));
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.v("Sokolov", "Fragment3 " + requestCode + " " + resultCode + " " + data);
    }

}
