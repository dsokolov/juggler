package me.ilich.juggler.staticjuggler.state

import android.support.annotation.LayoutRes
import java.io.Serializable

class Grid(
        @LayoutRes val layoutId: Int,
        vararg cells: Cell
) : Serializable {

    val cells = listOf(*cells)

}