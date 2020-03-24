package at.allaboutapps.epoxycarouseldemo.di.app

import android.app.Application
import at.allaboutapps.epoxycarouseldemo.application.App
import at.allaboutapps.epoxycarouseldemo.di.ActivityModule
import at.allaboutapps.epoxycarouseldemo.di.FragmentModule
import at.allaboutapps.epoxycarouseldemo.networking.services.ApiService
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
        modules = [
            AndroidSupportInjectionModule::class,
            AppModule::class,
            ActivityModule::class,
            FragmentModule::class
        ]
)
interface AppComponent : AndroidInjector<App> {

    fun apiService(): ApiService

    @Component.Builder
    abstract class Builder : AndroidInjector.Factory<App> {

        /**
         * Create an AppComponent with a bound Application.
         */
        override fun create(app: App): AppComponent {
            seedInstance(app)
            application(app)
            return build()
        }

        @BindsInstance
        abstract fun application(application: Application): Builder

        @BindsInstance
        abstract fun seedInstance(application: App): Builder

        abstract fun build(): AppComponent
    }
}