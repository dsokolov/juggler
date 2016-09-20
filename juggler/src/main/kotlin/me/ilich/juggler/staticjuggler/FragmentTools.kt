package me.ilich.juggler.staticjuggler

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View

class FragmentTools(val fragment: Fragment) {

    companion object {
        @JvmStatic fun <F : Fragment> instance(cls: Class<F>): F? {
            val name = "${cls.`package`.name}.Juggler${cls.simpleName}"
            try {
                @Suppress("UNCHECKED_CAST")
                return Class.forName(name).newInstance() as F
            } catch (e: InstantiationException) {
                e.printStackTrace()
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            } catch (e: ClassNotFoundException) {
                e.printStackTrace()
            }
            return null
        }
    }

    fun toolbar(view: View, toolbarId: Int) {
        val toolbar = view.findViewById(toolbarId) as Toolbar
        (fragment.activity as AppCompatActivity).setSupportActionBar(toolbar)
    }

}