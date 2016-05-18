package me.ilich.juggler.grid;

import android.support.annotation.IdRes;

import java.io.Serializable;

import me.ilich.juggler.R;

public class Cell implements Serializable {

    public static final int CELL_TYPE_CONTENT = 0;
    public static final int CELL_TYPE_TOOLBAR = 1;
    public static final int CELL_TYPE_NAVIGATION = 2;
    public static final int CELL_TYPE_CONTENT_BELOW = 3;
    public static final int CELL_TYPE_CONTENT_UNDER = 4;

    public static Cell content() {
        return new Cell(R.id.container_content, CELL_TYPE_CONTENT);
    }

    public static Cell contentBelow() {
        return new Cell(R.id.container_content_below, CELL_TYPE_CONTENT_BELOW);
    }

    public static Cell contentUnder() {
        return new Cell(R.id.container_content_under, CELL_TYPE_CONTENT_UNDER);
    }

    public static Cell toolbar() {
        return new Cell(R.id.container_toolbar, CELL_TYPE_TOOLBAR);
    }

    public static Cell navigation() {
        return new Cell(R.id.container_navigation, CELL_TYPE_NAVIGATION);
    }

    public static Cell custom(@IdRes int layoutId, int cellType) {
        return new Cell(layoutId, cellType);
    }

    @IdRes
    private final int containerId;
    private final int type;

    private Cell(@IdRes int containerId, int type) {
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
