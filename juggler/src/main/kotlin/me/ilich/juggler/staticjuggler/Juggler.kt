package me.ilich.juggler.staticjuggler

import android.app.Application
import android.content.Context
import android.content.ContextWrapper
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import me.ilich.juggler.annotations.ClassTools

class Juggler {

    companion object {

        @JvmStatic internal val EXTRA_JUGGLER_STATE = "_juggler_state"

        @JvmStatic fun on(activity: AppCompatActivity): Navigator {
            return AppCompatActivityNavigator(activity)
        }

        @JvmStatic fun on(fragment: Fragment): Navigator {
            return FragmentNavigator(fragment)
        }

        @JvmStatic fun on(context: Context): Navigator? {
            if (context is AppCompatActivity) {
                return AppCompatActivityNavigator(context)
            } else if (context is ContextWrapper) {
                if (context.baseContext is AppCompatActivity) {
                    return AppCompatActivityNavigator(context.baseContext as AppCompatActivity)
                } else {
                    if (context.baseContext is ContextWrapper) {
                        if ((context.baseContext as ContextWrapper).baseContext is AppCompatActivity) {
                            return AppCompatActivityNavigator((context.baseContext as ContextWrapper).baseContext as AppCompatActivity)
                        }
                    }
                }
            }
            return null
        }

        @JvmStatic fun on(application: Application): ApplicationTools {
            return ApplicationTools(application)
        }

        @JvmStatic fun <T> on(cls: Class<T>): ClassTools<T> = ClassTools(cls)

    }

}

