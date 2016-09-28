package me.ilich.juggler.staticjuggler.state;

import android.content.Context;
import android.support.v4.app.Fragment;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import me.ilich.juggler.staticjuggler.Juggler;
import me.ilich.juggler.staticjuggler.ListFragment;
import me.ilich.juggler.staticjuggler.ToolbarFragment;
import me.ilich.juggler.usage.R;

public class ListState extends AbstractState<VoidParams> {

    public ListState() {
        super(new Grid(R.layout.juggler_layout_content_below_toolbar, Cell.toolbar(), Cell.content()), VoidParams.INSTANCE);
    }

    @Nullable
    @Override
    protected String onTitle(@NotNull Context context, @NotNull VoidParams params) {
        return "List";
    }

    @Nullable
    @Override
    protected Integer onNavigationIcon(@NotNull Context context, @NotNull VoidParams params) {
        return android.R.drawable.ic_menu_upload;
    }

    @Override
    protected void onNavigationClick(@NotNull Context context, @NotNull VoidParams params) {
        Juggler.on(context).backOrFinish();
    }

    @Nullable
    @Override
    protected Fragment onFragment(@NotNull Cell cell, @NotNull VoidParams params) {
        switch (cell.getType()) {
            case Cell.TYPE_TOOLBAR:
                return ToolbarFragment.create();
            case Cell.TYPE_CONTENT:
                return ListFragment.create();
            default:
                return null;
        }
    }

}
