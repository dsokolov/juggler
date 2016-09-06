package me.ilich.juggler.staticjuggler;

import android.content.Context;
import android.support.v4.app.Fragment;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import me.ilich.juggler.usage.R;

public class States {

    public static State main(Context context) {
        return new State.Builder<State.VoidParams>().
                layoutId(R.layout.juggler_layout_content_below_toolbar).
                title(new Function2<Context, State.Params, String>() {
                    @Override
                    public String invoke(Context context, State.Params params) {
                        return "main";
                    }
                }).
                addFragmentFactory(Container.toolbar(), new Function1<State.Params, Fragment>() {
                    @Override
                    public Fragment invoke(State.Params params) {
                        return ToolbarFragment.create();
                    }
                }).
                addFragmentFactory(Container.content(), new Function1<State.Params, Fragment>() {
                    @Override
                    public Fragment invoke(State.Params params) {
                        return MainFragment.create();
                    }
                }).build(context, null);
    }

    public static State list(Context context) {
        return new State.Builder<State.VoidParams>().
                layoutId(R.layout.juggler_layout_content_below_toolbar).
                title(new Function2<Context, State.Params, String>() {
                    @Override
                    public String invoke(Context context, State.Params params) {
                        return "list";
                    }
                }).
                addFragmentFactory(Container.toolbar(), new Function1<State.Params, Fragment>() {
                    @Override
                    public Fragment invoke(State.Params params) {
                        return ToolbarFragment.create();
                    }
                }).
                addFragmentFactory(Container.content(), new Function1<State.Params, Fragment>() {
                    @Override
                    public Fragment invoke(State.Params params) {
                        return ListFragment.create();
                    }
                }).build(context, null);
    }

    public static State details(Context context, int id) {
        return new State.Builder<DetailsParams>().
                layoutId(R.layout.juggler_layout_content_below_toolbar).
                title(new Function2<Context, State.Params, String>() {
                    @Override
                    public String invoke(Context context, State.Params params) {
                        return "details";
                    }
                }).
                addFragmentFactory(Container.toolbar(), new Function1<DetailsParams, Fragment>() {
                    @Override
                    public Fragment invoke(DetailsParams params) {
                        return ToolbarFragment.create();
                    }
                }).
                addFragmentFactory(Container.content(), new Function1<DetailsParams, Fragment>() {
                    @Override
                    public Fragment invoke(DetailsParams params) {
                        return DetailsFragment.create(params.getId());
                    }
                }).build(context, new DetailsParams(id));
    }

    public static class DetailsParams extends State.Params {

        public int getId() {
            return id;
        }

        private final int id;

        public DetailsParams(int id) {
            this.id = id;
        }
    }

}
