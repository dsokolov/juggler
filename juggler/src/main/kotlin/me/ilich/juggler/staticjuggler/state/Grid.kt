package me.ilich.juggler.staticjuggler.state

import android.support.annotation.LayoutRes

class Grid(
        @LayoutRes val layoutId: Int,
        vararg cells: Cell
) {

    val cells = listOf(*cells)

}