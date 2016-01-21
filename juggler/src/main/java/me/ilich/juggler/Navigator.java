package me.ilich.juggler;

import android.support.annotation.Nullable;

public interface Navigator {

    void navigate(Transition_ transition, @Nullable Screen.Params params);

}
