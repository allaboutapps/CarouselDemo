package at.allaboutapps.epoxycarouseldemo.application

import at.allaboutapps.epoxycarouseldemo.BuildConfig
import at.allaboutapps.epoxycarouseldemo.di.AppInjector
import at.allaboutapps.epoxycarouseldemo.di.app.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import timber.log.Timber

class App : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
            DaggerAppComponent.builder().create(this)

    override fun onCreate() {
        super.onCreate()

        initLogging()

        SplashScreenHelper.register(this)
        AppInjector.init(this)
    }

    private fun initLogging() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
