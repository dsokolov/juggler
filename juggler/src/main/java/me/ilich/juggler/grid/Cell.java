package me.ilich.juggler.grid;

import android.support.annotation.IdRes;

public class Cell {

    @IdRes
    private final int containerId;
    private final CellType type;

    public Cell(int containerId, CellType type) {
        this.containerId = containerId;
        this.type = type;
    }

    @IdRes
    public int getContainerId() {
        return containerId;
    }

    public CellType getType() {
        return type;
    }

}
