package me.ilich.juggler.staticjuggler.state

import android.content.Context
import android.support.annotation.DrawableRes
import android.support.v4.app.Fragment

internal class BuiltState(
        val grid: Grid,
        val title: String?,
        @DrawableRes val icon: Int?,
        val displayOptions: Int?,
        val fragmentFactory: (Cell) -> (Fragment?)
) : State {

    override fun grid(): Grid = grid

    override fun title(context: Context): String? = title

    override fun displayOptions(): Int? = displayOptions

    @DrawableRes override fun icon(context: Context): Int? = icon

    override fun fragmentFactory(): (Cell) -> (Fragment?) = fragmentFactory

}