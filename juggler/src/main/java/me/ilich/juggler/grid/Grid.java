package me.ilich.juggler.grid;

import android.support.annotation.LayoutRes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import me.ilich.juggler.R;

public class Grid<C extends Cell> {

    public static Grid<Cell<CellType>> contentOnly() {
        return new Grid<>(R.layout.juggler_layout_content_only, new Cell<>(R.id.container_content, CellType.CONTENT));
    }

    public static Grid<Cell<CellType>> contentBelowToolbar() {
        return new Grid<>(R.layout.juggler_layout_content_below_toolbar, new Cell<>(R.id.container_content, CellType.CONTENT), new Cell<>(R.id.container_toolbar, CellType.TOOLBAR));
    }

    @LayoutRes
    private final int layoutId;
    private List<C> cells = new ArrayList<>();

    @SafeVarargs
    private Grid(int layoutId, C... cells) {
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

    public List<C> getCells() {
        return cells;
    }

}
