package me.ilich.juggler.hello.simple;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import me.ilich.juggler.hello.R;

public class SimpleFragmentsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_1_2);
        getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                android.util.Log.i("Sokolov", "onBackStackChanged");
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
            case R.id.menu_replace_A_to_1:
                getSupportFragmentManager().beginTransaction().replace(R.id.container1, new FragmentA()).addToBackStack("A").commit();
                return true;
            case R.id.menu_replace_B_to_2:
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                Fragment f = getSupportFragmentManager().findFragmentById(R.id.container1);
                transaction.remove(f);
                transaction.replace(R.id.container2, new FragmentB()).addToBackStack("B").commit();
                break;
            case R.id.menu_pop_imm:
                getSupportFragmentManager().popBackStackImmediate();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
