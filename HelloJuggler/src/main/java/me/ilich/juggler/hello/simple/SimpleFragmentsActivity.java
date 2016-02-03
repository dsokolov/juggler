package me.ilich.juggler.hello.simple;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import me.ilich.juggler.hello.R;

public class SimpleFragmentsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_1);
        if (savedInstanceState == null) {
            do1();
        } else {
            //Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.container1);
            //Log.v("Sokolov", fragment + "");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.simple, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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
        setContentView(R.layout.activity_simple_1);
        getSupportFragmentManager().
                beginTransaction().
                replace(R.id.container1, new Fragment1()).
                addToBackStack("1").
                commit();
    }

    private void do2() {
        setContentView(R.layout.activity_simple_1);
        getSupportFragmentManager().
                beginTransaction().
                replace(R.id.container1, new Fragment2()).
                addToBackStack("2").
                commit();
    }

}
