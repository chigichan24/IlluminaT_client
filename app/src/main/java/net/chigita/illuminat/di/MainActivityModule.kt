package net.chigita.illuminat.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import net.chigita.illuminat.MainActivity


/**
 * Created by chigichan24 on 2019-06-12.
 */
@Module
abstract class MainActivityModule {

    @ContributesAndroidInjector(
        modules = [FragmentBuildersModule::class]
    )
    abstract fun contributeMainActivity(): MainActivity
}