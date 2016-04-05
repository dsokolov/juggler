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

public class Fragment1 extends Fragment {

    private String s;
    private EditText editText;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v("Sokolov", "Fragment1 onCreate");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.v("Sokolov", "Fragment1 onActivityCreated");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.v("Sokolov", "Fragment1 onCreateView");
        return inflater.inflate(R.layout.framgment_simple_1, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.v("Sokolov", "Fragment1 onViewCreated");
        editText = (EditText) view.findViewById(R.id.edit1);
        editText.setText(s);
        editText.setText("213f");
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        editText.setText(s);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        s = data.getStringExtra("A");
        editText.setText(s);
        Log.v("Sokolov", "Fragment1 " + requestCode + " " + resultCode + " " + data);
    }

}
