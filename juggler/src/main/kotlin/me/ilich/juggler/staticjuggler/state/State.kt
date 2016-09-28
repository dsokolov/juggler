package me.ilich.juggler.staticjuggler.state

import android.content.Context
import android.support.v4.app.Fragment
import java.io.Serializable

interface State : Serializable {

    fun grid(): Grid

    fun fragment(cell: Cell): Fragment?

    fun title(context: Context): String?

    fun navigationIcon(context: Context): Int?

    fun navigationClick(context: Context)

    fun displayOptions(): Int?

}