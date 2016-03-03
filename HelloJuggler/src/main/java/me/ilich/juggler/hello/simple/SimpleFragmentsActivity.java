package me.ilich.juggler.hello.simple;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import me.ilich.juggler.hello.R;

public class SimpleFragmentsActivity extends AppCompatActivity {

    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP);
        }
    }

    @Override
    public boolean onNavigateUp() {
        Log.v("Sokolov", "onNavigateUp");
        return super.onNavigateUp();
    }

    @Override
    public boolean onSupportNavigateUp() {
        Log.v("Sokolov", "onSupportNavigateUp");
        return super.onSupportNavigateUp();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.simple, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
/*        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }*/
        switch (item.getItemId()) {
            case R.id.item1:
                do1();
                break;
            case R.id.item2:
                do2();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void do1() {
        Fragment1 f = new Fragment1();
        setContentView(R.layout.activity_simple_1);
        getSupportFragmentManager().
                beginTransaction().
                replace(R.id.container1, f).
                addToBackStack("1").
                commit();
    }

    private void do2() {
        Fragment fr = getSupportFragmentManager().findFragmentById(R.id.container1);

        Fragment2 f = new Fragment2();
        f.setTargetFragment(fr, 10);
        setContentView(R.layout.activity_simple_1);
        getSupportFragmentManager().
                beginTransaction().
                replace(R.id.container1, f).
                addToBackStack("2").
                commit();
    }

}
