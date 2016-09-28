package me.ilich.juggler.staticjuggler.state

import android.content.Context
import android.support.v4.app.Fragment

abstract class AbstractState<in P : Params>(
        private val grid: Grid,
        private val params: P
) : State {

    final override fun grid(): Grid = grid

    final override fun title(context: Context): String? = onTitle(context, params)

    final override fun navigationIcon(context: Context): Int? = onNavigationIcon(context, params)

    final override fun navigationClick(context: Context) = onNavigationClick(context, params)

    final override fun displayOptions(): Int? = onDisplayOptions()

    final override fun fragment(cell: Cell): Fragment? = onFragment(cell, params)


    open protected fun onTitle(context: Context, params: P): String? = null

    open protected fun onNavigationIcon(context: Context, params: P): Int? = null

    open protected fun onNavigationClick(context: Context, params: P) = Unit

    open protected fun onDisplayOptions(): Int? = null

    abstract protected fun onFragment(cell: Cell, params: P): Fragment?

}