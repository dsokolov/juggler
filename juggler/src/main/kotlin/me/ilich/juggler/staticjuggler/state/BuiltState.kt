package me.ilich.juggler.staticjuggler.state

import android.support.v4.app.Fragment

internal class BuiltState(
        val grid: Grid,
        val title: String,
        val fragmentFactory: (Cell) -> (Fragment?)
) : State {

    override fun grid(): Grid = grid

    override fun title(): String = title

    override fun fragmentFactory(): (Cell) -> (Fragment?) = fragmentFactory

}