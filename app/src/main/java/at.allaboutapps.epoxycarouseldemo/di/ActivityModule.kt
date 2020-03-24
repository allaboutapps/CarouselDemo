package at.allaboutapps.epoxycarouseldemo.di

import at.allaboutapps.epoxycarouseldemo.features.start.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityModule {

    @PerActivity
    @ContributesAndroidInjector
    fun provideMainActivity(): MainActivity
}
