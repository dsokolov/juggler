package me.ilich.juggler.hello.simple;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import me.ilich.juggler.hello.R;

public class SimpleFragmentsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_1);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container1, new Fragment1()).addToBackStack("A").commit();
            showBackstack();
        }

        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                //getSupportFragmentManager().popBackStackImmediate();
                getSupportFragmentManager().beginTransaction().replace(R.id.container1, new Fragment2()).addToBackStack("B").commit();
                showBackstack();
            }

        }.execute();

        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {
                try {
                    Thread.sleep(6000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                getSupportFragmentManager().popBackStack();
                getSupportFragmentManager().beginTransaction().replace(R.id.container1, new Fragment3()).addToBackStack("C").commit();
                showBackstack();
            }

        }.execute();
    }

    private void showBackstack() {
        StringBuilder sb = new StringBuilder();
        sb.append("backstack: ");
        for (int i = 0; i < getSupportFragmentManager().getBackStackEntryCount(); i++) {
            if (i != 0) {
                sb.append(" -> ");
            }
            sb.append(getSupportFragmentManager().getBackStackEntryAt(i).getName());
        }
        Log.v("Sokolov", sb.toString());
    }

    @Override
    public void onBackPressed() {
        showBackstack();
        getSupportFragmentManager().popBackStack();
    }

}
