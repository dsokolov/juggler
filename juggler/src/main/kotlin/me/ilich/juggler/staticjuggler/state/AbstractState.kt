package me.ilich.juggler.staticjuggler.state

import android.content.Context
import android.support.v4.app.Fragment

abstract class AbstractState<P : Params>(
        private val grid: Grid,
        private val params: P
) : State {

    final override fun grid(): Grid = grid

    final override fun title(context: Context): String? = onTitle(context, params)

    final override fun navigationIcon(context: Context): Int? = onNavigationIcon(context, params)

    final override fun navigationClick(context: Context): ((Context) -> Unit)? = onNavigationClick(context, params)

    final override fun displayOptions(): Int? = onDisplayOptions()

    final override fun fragmentFactory(): (Cell) -> Fragment? = onFragmentFactory(params)


    open protected fun onTitle(context: Context, params: P): String? = null

    open protected fun onNavigationIcon(context: Context, params: P): Int? = null

    open protected fun onNavigationClick(context: Context, params: P): ((Context) -> Unit)? = null

    open protected fun onDisplayOptions(): Int? = null

    protected abstract fun onFragmentFactory(params: P): (Cell) -> Fragment?

}