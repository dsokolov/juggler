package me.ilich.juggler.staticjuggler.state

import android.content.Context
import android.support.annotation.DrawableRes
import android.support.v4.app.Fragment

internal class BuiltState<P : Params>(
        val params: P?,
        val grid: Grid,
        val title: String?,
        @DrawableRes val icon: Int?,
        val displayOptions: Int?,
        val navigationClick: ((Context) -> (Unit))? = null
) : State {

    override fun fragment(cell: Cell): Fragment? {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun navigationClick(context: Context) {
        //TODO
    }

    override fun grid(): Grid = grid

    override fun title(context: Context): String? = title

    override fun displayOptions(): Int? = displayOptions

    @DrawableRes override fun navigationIcon(context: Context): Int? = icon

}