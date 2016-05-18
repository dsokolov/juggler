package me.ilich.juggler.hello.gui.fragments;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import me.ilich.juggler.Log;
import me.ilich.juggler.change.Add;
import me.ilich.juggler.change.Remove;
import me.ilich.juggler.gui.JugglerActivity;
import me.ilich.juggler.gui.JugglerFragment;
import me.ilich.juggler.gui.JugglerToolbarFragment;
import me.ilich.juggler.hello.R;
import me.ilich.juggler.hello.gui.activities.SlaveActivity;
import me.ilich.juggler.hello.states.AboutState;
import me.ilich.juggler.hello.states.InfinityState;
import me.ilich.juggler.hello.states.ItemsListState;
import me.ilich.juggler.hello.states.LoginState;
import me.ilich.juggler.hello.states.PreviewState;
import me.ilich.juggler.hello.states.WizardOneState;
import me.ilich.juggler.hello.states.toolbarpos.ToolbarStateB;

public class MainFragment extends JugglerToolbarFragment {

    public static MainFragment newInstance() {
        MainFragment f = new MainFragment();
        f.setArguments(addDisplayOptionsToBundle(null, ActionBar.DISPLAY_HOME_AS_UP | ActionBar.DISPLAY_SHOW_TITLE));
        return f;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    //@Override
    protected int getToolbarId() {
        return R.id.toolbar;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.navigate_to_list).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateTo().state(Add.newActivity(new ItemsListState()));
            }
        });
        view.findViewById(R.id.navigate_to_about).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateTo().state(Remove.none(), Add.deeper(new AboutState()));
            }
        });
        view.findViewById(R.id.navigate_to_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        view.findViewById(R.id.navigate_to_wizzard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateTo().state(Add.newActivity(new WizardOneState()));
            }
        });
        view.findViewById(R.id.navigate_to_toolbar_explain).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        view.findViewById(R.id.navigate_to_infinity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateTo().state(Add.deeper(new InfinityState(1)));
            }
        });
        view.findViewById(R.id.navigate_to_preview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateTo().state(Add.deeper(new PreviewState()));
            }
        });
        view.findViewById(R.id.navigate_to_slave_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), SlaveActivity.class));
            }
        });
        view.findViewById(R.id.navigate_to_activity_param).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = JugglerActivity.state(getContext(), new LoginState(), null);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.show_notification).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent notificationIntent = new Intent(getContext(), SlaveActivity.class);
                PendingIntent contentIntent = PendingIntent.getActivity(getContext(), 0, notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT);
                Notification.Builder builder = new Notification.Builder(getContext());

                builder.setContentIntent(contentIntent)
                        .setSmallIcon(android.R.drawable.ic_media_pause)
                        .setTicker("Последнее китайское предупреждение!")
                        .setWhen(System.currentTimeMillis())
                        .setAutoCancel(true)
                        .setContentTitle("Напоминание")
                        .setContentText("Пора покормить кота");

                Notification notification = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    notification = builder.build();
                } else {
                    notification = builder.getNotification();
                }

                NotificationManager notificationManager = (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(123, notification);
            }
        });
        view.findViewById(R.id.navigate_to_toolbars).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateTo().state(Add.newActivity(new ToolbarStateB()));
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final boolean b;
        switch (item.getItemId()) {
            case R.id.about:
                Toast.makeText(getContext(), "about", Toast.LENGTH_SHORT).show();
                navigateTo().state(Add.deeper(new AboutState()));
                b = true;
                break;
            default:
                b = super.onOptionsItemSelected(item);
                break;
        }
        return b;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.v(getClass(), "actionbar = " + getJugglerActivity().getSupportActionBar());
    }

}
