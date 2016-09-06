package me.ilich.juggler.staticjuggler

import android.content.Context
import android.support.v4.app.Fragment

class State(
        val layoutId: Int,
        val title: String?,
        val containers: List<Container>,
        val fragmentFactory: (Container.Type) -> (Fragment?)
) {

    class Builder {

        private var layoutId: Int? = null
        private var titleFactory: ((Context, Params?) -> (String))? = null
        private val containers = mutableSetOf<Container>()
        private val factories = mutableMapOf<Container.Type, (Params?) -> (Fragment?)>()

        fun layoutId(layoutId: Int): Builder {
            this.layoutId = layoutId
            return this
        }

        fun title(titleFactory: (Context, Params?) -> (String)): Builder {
            this.titleFactory = titleFactory
            return this
        }

        fun addContainerType(container: Container): Builder {
            containers.add(container)
            return this
        }

        fun addFragmentFactory(container: Container, factory: (Params?) -> (Fragment?)): Builder {
            containers.add(container)
            factories.put(container.type, factory)
            return this
        }

        fun build(context: Context, params: Params? = null): State {
            val f = fun(type: Container.Type): Fragment? {
                return factories[type]?.let { it(params) }
            }
            val title = titleFactory?.let { it(context, params) }
            return State(layoutId as Int, title, containers.toList(), f)
        }

    }

    class Params {

    }

    fun fragment(type: Container.Type): Fragment? = fragmentFactory.invoke(type)

}