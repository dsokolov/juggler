package me.ilich.juggler.staticjuggler.state;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;

import kotlin.jvm.functions.Function1;
import me.ilich.juggler.staticjuggler.MainFragment;
import me.ilich.juggler.staticjuggler.ToolbarFragment;
import me.ilich.juggler.usage.R;

public class MainState extends AbstractState<VoidParams> {

    public MainState() {
        super(VoidParams.INSTANCE);
    }

    @NotNull
    @Override
    protected String onTitle(@NotNull Context context, @NotNull VoidParams params) {
        return "Main";
    }

    @NotNull
    @Override
    protected Grid onGrid() {
        return new Grid(R.layout.juggler_layout_content_below_toolbar, Cell.toolbar(), Cell.content());
    }

    @NotNull
    @Override
    protected Function1<Cell, Fragment> onFragmentFactory() {
        return new Factory();
    }

    @Override
    public Integer icon(@NotNull Context context) {
        return android.R.drawable.ic_menu_info_details;
    }

    @Nullable
    @Override
    public Integer displayOptions() {
        return ActionBar.DISPLAY_SHOW_TITLE;
    }

    private static class Factory implements Function1<Cell, Fragment>, Serializable {
        @Override
        public Fragment invoke(Cell cell) {
            switch (cell.getType()) {
                case Cell.TYPE_TOOLBAR:
                    return ToolbarFragment.create();
                case Cell.TYPE_CONTENT:
                    return MainFragment.create();
                default:
                    return null;
            }
        }
    }

}
