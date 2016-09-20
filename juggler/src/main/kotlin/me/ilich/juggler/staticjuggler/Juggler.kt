package me.ilich.juggler.staticjuggler

import android.app.Application
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity

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

        @JvmStatic fun tools(fragment: Fragment): FragmentTools {
            return FragmentTools(fragment = fragment)
        }

    }

}

