package me.ilich.juggler.staticjuggler

import android.app.Application
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import me.ilich.juggler.annotations.ClassTools

class Juggler {

    companion object {

        @JvmStatic fun on(activity: AppCompatActivity): Navigator {
            return AppCompatActivityNavigator(activity)
        }

        @JvmStatic fun on(fragment: Fragment): Navigator {
            return FragmentNavigator(fragment)
        }

        @JvmStatic fun on(application: Application): ApplicationTools {
            return ApplicationTools(application)
        }

        @JvmStatic fun <T> on(cls: Class<T>): ClassTools<T> = ClassTools(cls)

    }

}

