package me.ilich.juggler.states;

import me.ilich.juggler.grid.Cell;

public class TargetBound {

    public static TargetBound contentToContent(int requestCode) {
        return new TargetBound(Cell.CELL_TYPE_CONTENT, Cell.CELL_TYPE_CONTENT, requestCode);
    }

    private final int cellIdFrom;
    private final int cellIdTo;
    private final int requestCode;

    public TargetBound(int cellIdFrom, int cellIdTo, int requestCode) {
        this.cellIdFrom = cellIdFrom;
        this.cellIdTo = cellIdTo;
        this.requestCode = requestCode;
    }

    public int getCellIdFrom() {
        return cellIdFrom;
    }

    public int getCellIdTo() {
        return cellIdTo;
    }

    public int getRequestCode() {
        return requestCode;
    }

}
