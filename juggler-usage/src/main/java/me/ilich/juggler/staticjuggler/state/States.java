package me.ilich.juggler.staticjuggler.state;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import kotlin.jvm.functions.Function2;
import me.ilich.juggler.staticjuggler.DetailsFragment;
import me.ilich.juggler.staticjuggler.ListFragment;
import me.ilich.juggler.staticjuggler.ToolbarFragment;
import me.ilich.juggler.usage.R;

public class States {

/*    public static State list(Context context) {
        return new StateBuilder<VoidParams>(new Grid(R.layout.juggler_layout_content_below_toolbar, Cell.toolbar(), Cell.content())).
                title(new Function2<Context, Params, String>() {
                    @Override
                    public String invoke(Context context, Params params) {
                        return "list";
                    }
                }).
                icon(new Function2<Context, VoidParams, Integer>() {
                    @Override
                    public Integer invoke(Context context, VoidParams voidParams) {
                        return android.R.drawable.ic_menu_add;
                    }
                }).
                addFragmentFactory(Cell.toolbar(), new FragmentFactory<VoidParams>() {
                    @Nullable
                    @Override
                    public Fragment onCreate(@NonNull VoidParams params) {
                        return ToolbarFragment.create();
                    }
                }).
                addFragmentFactory(Cell.content(), new FragmentFactory<VoidParams>() {
                    @Nullable
                    @Override
                    public Fragment onCreate(@NonNull VoidParams params) {
                        return ListFragment.create();
                    }
                }).
                build(context, VoidParams.INSTANCE);
    }*/

  /*  public static State details(Context context, int id) {
        return new StateBuilder<DetailsParams>(new Grid(R.layout.juggler_layout_content_below_toolbar, Cell.toolbar(), Cell.content())).
                title(new Function2<Context, Params, String>() {
                    @Override
                    public String invoke(Context context, Params params) {
                        return null;
                    }
                }).
                addFragmentFactory(Cell.toolbar(), new FragmentFactory<DetailsParams>() {

                    @Nullable
                    @Override
                    public Fragment onCreate(@NotNull DetailsParams params) {
                        return ToolbarFragment.create();
                    }
                }).
                addFragmentFactory(Cell.content(), new FragmentFactory<DetailsParams>() {
                    @Nullable
                    @Override
                    public Fragment onCreate(@NotNull DetailsParams params) {
                        return DetailsFragment.create(params.id);
                    }
                }).
                build(context, new DetailsParams(id));
    }

    private static class DetailsParams extends Params {

        public int getId() {
            return id;
        }

        private final int id;

        DetailsParams(int id) {
            this.id = id;
        }
    }*/

}
