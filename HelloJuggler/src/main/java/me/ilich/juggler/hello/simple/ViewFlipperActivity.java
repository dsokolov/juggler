package me.ilich.juggler.hello.simple;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import me.ilich.juggler.hello.R;

public class ViewFlipperActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_flipper);
        ViewFlipper viewFlipper = (ViewFlipper) findViewById(R.id.view_flipper);
        for (int i = 0; i < 10; i++) {
            TextView textView = new TextView(this);
            textView.setText(i + "");
            viewFlipper.addView(textView);
        }
    }

}
