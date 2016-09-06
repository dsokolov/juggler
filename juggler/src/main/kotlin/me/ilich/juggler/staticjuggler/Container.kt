package me.ilich.juggler.staticjuggler

import android.support.annotation.IdRes

class Container(
        @IdRes val id: Int,
        val type: Type
) {

    class Type(
            val type: Int
    ) {

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