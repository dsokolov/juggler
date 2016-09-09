package me.ilich.juggler.staticjuggler.state

import android.support.annotation.IdRes
import me.ilich.juggler.R
import java.io.Serializable

class Cell(
        @IdRes val id: Int,
        val type: Int
) : Serializable {

    companion object {

        const val TYPE_TOOLBAR = 1
        const val TYPE_CONTENT = 2

        @JvmStatic fun toolbar(): Cell = Cell(R.id.container_toolbar, TYPE_TOOLBAR)
        @JvmStatic fun content(): Cell = Cell(R.id.container_content, TYPE_CONTENT)

    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as Cell

        if (id != other.id) return false
        if (type != other.type) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + type
        return result
    }

}