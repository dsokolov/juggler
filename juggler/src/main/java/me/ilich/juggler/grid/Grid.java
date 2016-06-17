package me.ilich.juggler.grid;

import android.support.annotation.LayoutRes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import me.ilich.juggler.R;

public class Grid implements Serializable {

    public static Grid contentOnly() {
        return new Grid(R.layout.juggler_layout_content_only, Cell.content());
    }

    public static Grid contentBelowToolbar() {
        return new Grid(R.layout.juggler_layout_content_below_toolbar, Cell.content(), Cell.toolbar());
    }

    public static Grid contentUnderToolbar() {
        return new Grid(R.layout.juggler_layout_content_under_toolbar, Cell.content(), Cell.toolbar());
    }

    public static Grid contentDoubleToolbar() {
        return new Grid(R.layout.juggler_layout_content_double_toolbar, Cell.contentBelow(), Cell.contentUnder(), Cell.toolbar());
    }

    public static Grid contentDoubleToolbarNavigation() {
        return new Grid(R.layout.juggler_layout_content_double_toolbar_navigation, Cell.contentBelow(), Cell.contentUnder(), Cell.navigation(), Cell.toolbar());
    }

    public static Grid contentToolbarNavigation() {
        return new Grid(R.layout.juggler_layout_content_toolbar_navigation, Cell.content(), Cell.toolbar(), Cell.navigation());
    }

    public static Grid contentUnderToolbarNavigation() {
        return new Grid(R.layout.juggler_layout_content_under_toolbar_navigation, Cell.content(), Cell.toolbar(), Cell.navigation());
    }

    public static Grid contentNavigation() {
        return new Grid(R.layout.juggler_layout_content_navigation, Cell.content(), Cell.navigation());
    }

    public static Grid custom(@LayoutRes int layoutId, Cell... cells) {
        return new Grid(layoutId, cells);
    }

    @LayoutRes
    private final int layoutId;
    private List<Cell> cells = new ArrayList<>();

    private Grid(int layoutId, Cell... cells) {
        this.layoutId = layoutId;
        Collections.addAll(this.cells, cells);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grid grid = (Grid) o;
        return layoutId == grid.layoutId;
    }

    @Override
    public int hashCode() {
        return layoutId;
    }

    public int getLayoutId() {
        return layoutId;
    }

    public List<Cell> getCells() {
        return cells;
    }

}
