package me.ilich.juggler;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public abstract class JugglerActivity<SM extends ScreensManager> extends AppCompatActivity {

    private Juggler<SM> juggler;
    @Nullable
    private Screen currentScreen = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SM screensManager = createScreenManager();
        screensManager.init();
        juggler = new Juggler<>(screensManager);
    }

    protected Juggler<SM> getJuggler() {
        return juggler;
    }

    protected abstract SM createScreenManager();

    @IdRes
    public abstract int getContainerContentLayoutId();

    @IdRes
    public abstract int getContainerToolbarLayoutId();

    public final void setCurrentScreen(@Nullable Screen currentScreen) {
        this.currentScreen = currentScreen;
    }

    @Nullable
    public final Screen getCurrentScreen() {
        return currentScreen;
    }

    @Override
    public void onBackPressed() {
        if (currentScreen == null) {
            super.onBackPressed();
        } else {
            Screen backScreen = currentScreen.getBackScreen();
            if (backScreen == null) {
                super.onBackPressed();
            } else {
                backScreen.showOn(this);
            }
        }
    }
}
