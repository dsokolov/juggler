package me.ilich.juggler.staticjuggler.state

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.v4.app.Fragment

interface State {

    fun title(context: Context): String

    fun icon(context: Context): Int

    fun grid(): Grid

    fun fragmentFactory(): (Cell) -> Fragment?

}