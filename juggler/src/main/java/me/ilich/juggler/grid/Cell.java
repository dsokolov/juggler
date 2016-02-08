package me.ilich.juggler.grid;

import android.support.annotation.IdRes;

import me.ilich.juggler.R;

public class Cell {

    public static final int CELL_TYPE_CONTENT = 0;
    public static final int CELL_TYPE_TOOLBAR = 1;
    public static final int CELL_TYPE_NAVIGATION = 2;

    public static Cell content(@IdRes int layoutId){
        return new Cell(layoutId, CELL_TYPE_CONTENT);
    }

    public static Cell toolbar(@IdRes int layoutId){
        return new Cell(layoutId, CELL_TYPE_TOOLBAR);
    }

    public static Cell navigation(@IdRes int layoutId){
        return new Cell(layoutId, CELL_TYPE_NAVIGATION);
    }

    @IdRes
    private final int containerId;
    private final int type;

    public Cell(@IdRes int containerId, int type) {
        this.containerId = containerId;
        this.type = type;
    }

    @IdRes
    public int getContainerId() {
        return containerId;
    }

    public int getType() {
        return type;
    }

}
