package me.ilich.juggler.hello.gui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.ilich.juggler.fragments.JugglerNewInstance;
import me.ilich.juggler.fragments.content.JugglerContentFragment;
import me.ilich.juggler.hello.HelloScreensManager;
import me.ilich.juggler.hello.R;

public class LoginFragment extends JugglerContentFragment<HelloScreensManager> implements View.OnClickListener {

    @JugglerNewInstance
    public static LoginFragment create() {
        return new LoginFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.login).setOnClickListener(this);
        view.findViewById(R.id.register).setOnClickListener(this);
        view.findViewById(R.id.restore_password).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                getScreenManager().main();
                break;
            case R.id.register:
                break;
            case R.id.restore_password:
                break;
        }
    }

}
