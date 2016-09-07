package me.ilich.juggler.staticjuggler.state;

import android.content.Context;
import android.support.v4.app.Fragment;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import me.ilich.juggler.staticjuggler.DetailsFragment;
import me.ilich.juggler.staticjuggler.ListFragment;
import me.ilich.juggler.staticjuggler.ToolbarFragment;
import me.ilich.juggler.usage.R;

public class States {

/*    public static State main(Context context) {
        return new StateBuilder<VoidParams>().
                layoutId(R.layout.juggler_layout_content_below_toolbar).
                title(new Function2<Context, Params, String>() {
                    @Override
                    public String invoke(Context context, Params params) {
                        return "main";
                    }
                }).
                addFragmentFactory(Container.toolbar(), new Function1<Params, Fragment>() {
                    @Override
                    public Fragment invoke(Params params) {
                        return ToolbarFragment.create();
                    }
                }).
                addFragmentFactory(Container.content(), new Function1<Params, Fragment>() {
                    @Override
                    public Fragment invoke(Params params) {
                        return MainFragment.create();
                    }
                }).build(context, null);
    }*/

    public static State list(Context context) {
        return new StateBuilder<VoidParams>(new Grid(R.layout.juggler_layout_content_below_toolbar, Cell.toolbar(), Cell.content())).
                title(new Function2<Context, Params, String>() {
                    @Override
                    public String invoke(Context context, Params params) {
                        return "list";
                    }
                }).
                addFragmentFactory(Cell.toolbar(), new Function1<Params, Fragment>() {
                    @Override
                    public Fragment invoke(Params params) {
                        return ToolbarFragment.create();
                    }
                }).
                addFragmentFactory(Cell.content(), new Function1<Params, Fragment>() {
                    @Override
                    public Fragment invoke(Params params) {
                        return ListFragment.create();
                    }
                }).build(context, null);
    }

    public static State details(Context context, int id) {
        return new StateBuilder<DetailsParams>(new Grid(R.layout.juggler_layout_content_below_toolbar, Cell.toolbar(), Cell.content())).
                title(new Function2<Context, Params, String>() {
                    @Override
                    public String invoke(Context context, Params params) {
                        return "details";
                    }
                }).
                addFragmentFactory(Cell.toolbar(), new Function1<DetailsParams, Fragment>() {
                    @Override
                    public Fragment invoke(DetailsParams params) {
                        return ToolbarFragment.create();
                    }
                }).
                addFragmentFactory(Cell.content(), new Function1<DetailsParams, Fragment>() {
                    @Override
                    public Fragment invoke(DetailsParams params) {
                        return DetailsFragment.create(params.getId());
                    }
                }).build(context, new DetailsParams(id));
    }

    public static class DetailsParams extends Params {

        public int getId() {
            return id;
        }

        private final int id;

        public DetailsParams(int id) {
            this.id = id;
        }
    }

}
