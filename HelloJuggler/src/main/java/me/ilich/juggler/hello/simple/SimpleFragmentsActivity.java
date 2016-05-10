package me.ilich.juggler.hello.simple;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import me.ilich.juggler.hello.R;

public class SimpleFragmentsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                Log.v("Sokolov", "onBackStackChanged");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.fragments, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_add_fragment_1:
                Log.v("Sokolov", "add fragment1 start");
                getSupportFragmentManager().beginTransaction().replace(R.id.container2, new Fragment1()).addToBackStack("A").commit();
                Log.v("Sokolov", "add fragment1 end");
                break;
            case R.id.menu_add_fragment_2:
                Log.v("Sokolov", "add fragment2 start");
                getSupportFragmentManager().beginTransaction().replace(R.id.container2, new Fragment2()).addToBackStack("B").commit();
                Log.v("Sokolov", "add fragment2 end");
                break;
            case R.id.menu_pop:
                Log.v("Sokolov", "popBackStack start");
                getSupportFragmentManager().popBackStack();
                Log.v("Sokolov", "popBackStack end");
                break;
            case R.id.menu_pop_imm:
                Log.v("Sokolov", "popBackStackImmediate start");
                getSupportFragmentManager().popBackStackImmediate();
                Log.v("Sokolov", "popBackStackImmediate end");
                break;
            case R.id.menu_replace_last:
                Log.v("Sokolov", "replace last start");
                getSupportFragmentManager().popBackStack();
                getSupportFragmentManager().beginTransaction().replace(R.id.container2, new Fragment2()).addToBackStack("B").commit();
                Log.v("Sokolov", "replace last end");
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
