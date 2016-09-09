package me.ilich.juggler.staticjuggler

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class ApplicationTools(
        private val application: Application
) {

    fun init() {
        application.registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {

            override fun onActivityPaused(activity: Activity?) {
            }

            override fun onActivityStarted(activity: Activity?) {
            }

            override fun onActivityDestroyed(activity: Activity?) {
                Juggler.on(activity as AppCompatActivity).destroy()
            }

            override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle) {
                Juggler.on(activity as AppCompatActivity).save(outState)
            }

            override fun onActivityStopped(activity: Activity?) {
            }

            override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
            }

            override fun onActivityResumed(activity: Activity?) {
            }

        })
    }


}