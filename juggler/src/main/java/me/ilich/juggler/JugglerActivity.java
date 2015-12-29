package me.ilich.juggler;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public abstract class JugglerActivity<SM extends ScreensManager> extends AppCompatActivity {

    private Juggler<SM> juggler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        juggler = new Juggler<>(createScreenManager());
    }

    protected abstract SM createScreenManager();

    public final Juggler<SM> getJuggler() {
        return juggler;
    }

    @IdRes
    public abstract int getContainerContentLayoutId();

    @IdRes
    public abstract int getContainerToolbarLayoutId();

    @IdRes
    public abstract int getContainerNavigationLayoutId();

    @Override
    public void onBackPressed() {
        boolean b = juggler.getScreenManager().back();
        if (!b) {
            super.onBackPressed();
        }
    }

    public <S> S navigateTo(Class<S> sClass) {
        ScreensManager screensManager = juggler.getScreenManager();
        if (!sClass.isAssignableFrom(screensManager.getClass())) {
            throw new RuntimeException("ScreenManager should implements " + sClass);
        }
        return (S) screensManager;
    }

}
