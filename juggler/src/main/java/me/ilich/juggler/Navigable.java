package me.ilich.juggler;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import me.ilich.juggler.change.Add;
import me.ilich.juggler.change.Remove;
import me.ilich.juggler.states.State;
import me.ilich.juggler.states.TargetBound;

public interface Navigable {

    boolean backState();

    boolean upState();

    void restore();

    void state(@Nullable Remove.Interface remove);

    void state(@Nullable Add.Interface add);

    void state(@Nullable Remove.Interface remove, @Nullable Add.Interface add);

}
