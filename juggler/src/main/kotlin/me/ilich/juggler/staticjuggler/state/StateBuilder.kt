package me.ilich.juggler.staticjuggler.state

import android.content.Context
import android.support.v4.app.Fragment

class StateBuilder<P : Params>(
        private val grid: Grid
) {

    private var titleFactory: ((Context, P?) -> (String))? = null
    private var iconFactory: ((Context, P?) -> (Int))? = null
    private var displayOptionsFactory: ((Context, P?) -> (Int))? = null
    private val factories = mutableMapOf<Cell, (P?) -> (Fragment?)>()

    fun title(titleFactory: (Context, Params?) -> (String)): StateBuilder<P> {
        this.titleFactory = titleFactory
        return this
    }

    fun icon(iconFactory: ((Context, P?) -> (Int))): StateBuilder<P> {
        this.iconFactory = iconFactory
        return this
    }

    fun displayOptions(displayOptionsFactory: ((Context, P?) -> (Int))): StateBuilder<P> {
        this.displayOptionsFactory = displayOptionsFactory
        return this
    }

    fun addFragmentFactory(cell: Cell, factory: (P?) -> (Fragment?)): StateBuilder<P> {
        factories.put(cell, factory)
        return this
    }

    fun build(context: Context, params: P? = null): State {
        val fragmentFactory = fun(cell: Cell): Fragment? {
            return factories[cell]?.let { it(params) }
        }
        val title = titleFactory?.let { it(context, params) } ?: null
        val icon = iconFactory?.let { it(context, params) } ?: null
        val displayOptions = displayOptionsFactory?.let { it(context, params) } ?: null
        return BuiltState(
                grid = grid,
                title = title,
                icon = icon,
                displayOptions = displayOptions,
                fragmentFactory = fragmentFactory
        )
    }

}