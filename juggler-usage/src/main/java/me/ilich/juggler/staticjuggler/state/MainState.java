package me.ilich.juggler.staticjuggler.state;

import android.content.Context;
import android.support.v4.app.Fragment;

import org.jetbrains.annotations.NotNull;

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

    @Override
    protected int onLayoutId(@NotNull Context context, @NotNull VoidParams params) {
        return R.layout.juggler_layout_content_below_toolbar;
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
