package me.ilich.juggler.grid;

import android.support.annotation.IdRes;

public class Cell<T extends CellType> {

    @IdRes
    private final int containerId;
    private final T type;

    public Cell(int containerId, T type) {
        this.containerId = containerId;
        this.type = type;
    }

    @IdRes
    public int getContainerId() {
        return containerId;
    }

    public T getType() {
        return type;
    }

}
