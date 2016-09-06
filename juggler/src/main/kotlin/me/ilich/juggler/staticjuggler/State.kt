package me.ilich.juggler.staticjuggler

import android.content.Context
import android.support.v4.app.Fragment

class State(
        val layoutId: Int,
        val title: String,
        val containers: List<Container>,
        val fragmentFactory: (Container.Type) -> (Fragment?)
) {

    class Builder<P : Params> {

        private var layoutId: Int? = null
        private var titleFactory: ((Context, P?) -> (String))? = null
        private val containers = mutableSetOf<Container>()
        private val factories = mutableMapOf<Container.Type, (P?) -> (Fragment?)>()

        fun layoutId(layoutId: Int): Builder<P> {
            this.layoutId = layoutId
            return this
        }

        fun title(titleFactory: (Context, Params?) -> (String)): Builder<P> {
            this.titleFactory = titleFactory
            return this
        }

        fun addContainerType(container: Container): Builder<P> {
            containers.add(container)
            return this
        }

        fun addFragmentFactory(container: Container, factory: (P?) -> (Fragment?)): Builder<P> {
            containers.add(container)
            factories.put(container.type, factory)
            return this
        }

        fun build(context: Context, params: P? = null): State {
            val f = fun(type: Container.Type): Fragment? {
                return factories[type]?.let { it(params) }
            }
            val title = titleFactory?.let { it(context, params) } ?: ""
            return State(layoutId as Int, title, containers.toList(), f)
        }

    }

    open class Params {

    }

    class VoidParams : Params()

    fun fragment(type: Container.Type): Fragment? = fragmentFactory.invoke(type)

}