package me.ilich.juggler.annotations

class ClassTools<T>(private val cls: Class<T>) {

    fun newInstance(): T {
        val name = Tools.getJugglerFullClassName(cls)
        try {
            @Suppress("UNCHECKED_CAST")
            return Class.forName(name).newInstance() as T
        } catch (e: InstantiationException) {
            throw RuntimeException(e)
        } catch (e: IllegalAccessException) {
            throw RuntimeException(e)
        } catch (e: ClassNotFoundException) {
            throw RuntimeException(e)
        }
    }

}
