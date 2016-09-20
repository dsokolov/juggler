package me.ilich.juggler.staticjuggler.state

import android.content.Context
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment

abstract class AbstractState<P : Params>(val params: P) : State {

    override fun title(context: Context): String = onTitle(context, params)

    override fun grid(): Grid = onGrid()

    override fun fragmentFactory(): (Cell) -> Fragment? = onFragmentFactory()

    protected abstract fun onTitle(context: Context, params: P): String

    @LayoutRes protected abstract fun onLayoutId(context: Context, params: P): Int

    protected abstract fun onGrid(): Grid

    protected abstract fun onFragmentFactory(): (Cell) -> Fragment?

}