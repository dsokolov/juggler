package me.ilich.juggler.staticjuggler;

import android.content.Context;
import android.support.v4.app.Fragment;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import me.ilich.juggler.usage.R;

public class MainState {

    public static State.Builder main = new State.Builder().
            layoutId(R.layout.juggler_layout_content_below_toolbar).
            title(new Function2<Context, State.Params, String>() {
                @Override
                public String invoke(Context context, State.Params params) {
                    return "main";
                }
            }).
            addFragmentFactory(new Container(R.id.container_toolbar, new Container.Type(0)), new Function1<State.Params, Fragment>() {
                @Override
                public Fragment invoke(State.Params params) {
                    return ToolbarFragment.create();
                }
            }).
            addFragmentFactory(new Container(R.id.container_content, new Container.Type(1)), new Function1<State.Params, Fragment>() {
                @Override
                public Fragment invoke(State.Params params) {
                    return ContentFragment.create();
                }
            });

    public static State.Builder details = new State.Builder().
            layoutId(R.layout.juggler_layout_content_below_toolbar).
            title(new Function2<Context, State.Params, String>() {
                @Override
                public String invoke(Context context, State.Params params) {
                    return "details";
                }
            }).
            addFragmentFactory(new Container(R.id.container_toolbar, new Container.Type(0)), new Function1<State.Params, Fragment>() {
                @Override
                public Fragment invoke(State.Params params) {
                    return ToolbarFragment.create();
                }
            }).
            addFragmentFactory(new Container(R.id.container_content, new Container.Type(1)), new Function1<State.Params, Fragment>() {
                @Override
                public Fragment invoke(State.Params params) {
                    return ContentFragment.create();
                }
            });

}
