package me.ilich.juggler.staticjuggler.state

import android.content.Context
import android.support.v4.app.Fragment

interface State {

    fun title(context: Context): String?

    fun icon(context: Context): Int?

    fun displayOptions(): Int?

    fun grid(): Grid

    fun fragmentFactory(): (Cell) -> Fragment?

}