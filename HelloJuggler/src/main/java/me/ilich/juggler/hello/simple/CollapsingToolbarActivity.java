package me.ilich.juggler.hello.simple;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import me.ilich.juggler.hello.R;

public class CollapsingToolbarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collapsing_toolbar_with_fragments);
        getSupportFragmentManager().
                beginTransaction().
                replace(R.id.container_content, new NestedScrollViewFragment()).
                replace(R.id.container1, new ImageFragment()).
                replace(R.id.container_toolbar, new ToolbarFragment()).
                        commit();
    }

}
