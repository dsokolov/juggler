package me.ilich.juggler.hello.simple;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import me.ilich.juggler.hello.R;

public class SimpleFragmentsActivity extends AppCompatActivity {

    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_2);
        getSupportFragmentManager().beginTransaction().replace(R.id.container2, new CoordinatorFragment()).commit();
/*        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        *//*getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        myToolbar.setTitle("13345");*//*

        DrawerLayout mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close);
        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

   *//*     if (savedInstanceState == null) {
            do1();
        } else {
            //Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.container1);
            //Log.v("Sokolov", fragment + "");
        }*/
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
//        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.simple, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
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
