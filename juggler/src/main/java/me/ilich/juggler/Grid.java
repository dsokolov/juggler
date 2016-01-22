package me.ilich.juggler;

import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;

import java.util.ArrayList;
import java.util.List;

public class Grid {

    public static Grid contentOnly() {
        return new Grid(R.layout.juggler_layout_content_only, new Cell(R.id.container_content, Cell.TYPE.CONTENT));
    }

    @LayoutRes
    private final int layoutId;
    private List<Cell> cells = new ArrayList<>();

    private Grid(int layoutId, Cell... cells) {
        this.layoutId = layoutId;
        for (Cell cell : cells) {
            this.cells.add(cell);
        }
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

    public static class Cell {

        public enum TYPE {
            CONTENT,
            TOOLBAR
        }

        @IdRes
        private final int containerId;
        private final TYPE type;

        public Cell(int containerId, TYPE type) {
            this.containerId = containerId;
            this.type = type;
        }

        @IdRes
        public int getContainerId() {
            return containerId;
        }

        public TYPE getType() {
            return type;
        }

    }

}
