package net.chigita.illuminat.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import net.chigita.illuminat.home.HomeFragment

/**
 * Created by chigichan24 on 2019-06-12.
 */
@Module
abstract class FragmentBuildersModule {
  @ContributesAndroidInjector
  abstract fun contribubteHomeFragment(): HomeFragment
}