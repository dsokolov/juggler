package me.ilich.juggler.staticjuggler.state

import android.content.Context
import android.support.v4.app.Fragment

class StateBuilder<P : Params>(
        private val grid: Grid
) {

    private var titleFactory: ((Context, P?) -> (String))? = null
    private val factories = mutableMapOf<Cell, (P?) -> (Fragment?)>()

    fun title(titleFactory: (Context, Params?) -> (String)): StateBuilder<P> {
        this.titleFactory = titleFactory
        return this
    }

    fun addFragmentFactory(cell: Cell, factory: (P?) -> (Fragment?)): StateBuilder<P> {
        factories.put(cell, factory)
        return this
    }

    fun build(context: Context, params: P? = null): State {
        val f = fun(cell: Cell): Fragment? {
            return factories[cell]?.let { it(params) }
        }
        val title = titleFactory?.let { it(context, params) } ?: ""
        return BuiltState(grid, title, f)
    }

}