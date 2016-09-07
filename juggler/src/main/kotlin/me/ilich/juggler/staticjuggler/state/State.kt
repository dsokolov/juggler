package me.ilich.juggler.staticjuggler.state

import android.support.v4.app.Fragment

interface State {

    fun title(): String

    fun grid(): Grid

    fun fragmentFactory(): (Cell) -> Fragment?

}