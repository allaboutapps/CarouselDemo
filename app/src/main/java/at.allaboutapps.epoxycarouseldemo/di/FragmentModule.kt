package at.allaboutapps.epoxycarouseldemo.di

import at.allaboutapps.epoxycarouseldemo.features.start.ui.main.MainFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FragmentModule {

    @PerFragment
    @ContributesAndroidInjector
    fun provideMainFragment(): MainFragment
}