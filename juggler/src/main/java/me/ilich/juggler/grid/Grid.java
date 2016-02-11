package me.ilich.juggler.grid;

import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import me.ilich.juggler.R;

public class Grid {

    public static Grid contentOnly() {
        return new Grid(R.layout.juggler_layout_content_only, Cell.content(R.id.container_content));
    }

    public static Grid contentBelowToolbar() {
        return new Grid(R.layout.juggler_layout_content_below_toolbar, Cell.content(R.id.container_content), Cell.toolbar(R.id.container_toolbar));
    }

    public static Grid contentToolbarNavigation() {
        return new Grid(R.layout.juggler_layout_content_toolbar_navigation, Cell.content(R.id.container_content), Cell.toolbar(R.id.container_toolbar), Cell.navigation(R.id.container_navigation));
    }

    public static Grid contentNavigation() {
        return new Grid(R.layout.juggler_layout_content_navigation, Cell.content(R.id.container_content), Cell.navigation(R.id.container_navigation));
    }

    public static Grid content(@LayoutRes int layoutId, @IdRes int contentId){
        return new Grid(layoutId, Cell.content(contentId));
    }

    public static Grid contentWithToolbar(@LayoutRes int layoutId, @IdRes int contentId, @IdRes int toolbarId){
        return new Grid(layoutId, Cell.content(contentId), Cell.toolbar(toolbarId));
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
