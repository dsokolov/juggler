package me.ilich.juggler;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public abstract class JugglerActivity<SM extends ScreensManager> extends AppCompatActivity {

    private SM screensManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        screensManager = createScreenManager();
        screensManager.init();
    }

    protected abstract SM createScreenManager();

    public SM getScreensManager() {
        return screensManager;
    }

    @IdRes
    public abstract int getContainerContentLayoutId();

    @IdRes
    public abstract int getContainerToolbarLayoutId();

    @Override
    public void onBackPressed() {
        if (screensManager.hasBack()) {
            screensManager.showPrev();
        } else {
            super.onBackPressed();
        }
    }
}
