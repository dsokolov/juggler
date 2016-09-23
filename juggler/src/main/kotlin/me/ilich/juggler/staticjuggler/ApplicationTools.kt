package me.ilich.juggler.staticjuggler

import android.app.Activity
import android.app.Application
import android.os.Bundle

class ApplicationTools(
        private val application: Application
) {

    fun init() {
        application.registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {

            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                Dispatcher.onActivityCreated(activity, savedInstanceState)
            }

            override fun onActivityStarted(activity: Activity) {
                Dispatcher.onActivityStarted(activity)
            }

            override fun onActivityResumed(activity: Activity) {
                Dispatcher.onActivityResumed(activity)
            }

            override fun onActivityPaused(activity: Activity) {
                Dispatcher.onActivityPaused(activity)
            }

            override fun onActivityStopped(activity: Activity) {
                Dispatcher.onActivityStopped(activity)
            }

            override fun onActivityDestroyed(activity: Activity) {
                Dispatcher.onActivityDestroyed(activity)
            }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
                Dispatcher.onActivitySaveInstanceState(activity, outState)
            }

        })
    }


}