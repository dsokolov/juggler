package me.ilich.juggler;

import android.support.annotation.NonNull;

import me.ilich.juggler.change.Add;
import me.ilich.juggler.change.Remove;
import me.ilich.juggler.states.State;
import me.ilich.juggler.states.TargetBound;

public interface Navigable {

    boolean backState();

    boolean upState();

    void restore();

    void state(@NonNull Remove.Interface pop);

    void state(@NonNull Add.Interface add);

    void state(@NonNull Remove.Interface pop, @NonNull Add.Interface add);

}
