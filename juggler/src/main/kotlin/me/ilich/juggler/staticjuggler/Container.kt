package me.ilich.juggler.staticjuggler

import android.support.annotation.IdRes
import me.ilich.juggler.R

class Container(
        @IdRes val id: Int,
        val type: Type
) {

    companion object {
        @JvmStatic fun toolbar(): Container = Container(R.id.container_toolbar, Type.TOOLBAR)
        @JvmStatic fun content(): Container = Container(R.id.container_content, Type.CONTENT)
    }

    class Type(
            val type: Int
    ) {

        companion object {
            val TOOLBAR = Type(1)
            val CONTENT = Type(2)
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other?.javaClass != javaClass) return false

            other as Type

            if (type != other.type) return false

            return true
        }

        override fun hashCode(): Int {
            return type
        }

    }

}