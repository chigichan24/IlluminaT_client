package net.chigita.illuminat.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import net.chigita.illuminat.feature.apply.ApplyPatternFragment
import net.chigita.illuminat.feature.home.HomeFragment
import net.chigita.illuminat.feature.register.RegisterPatternFragment

/**
 * Created by chigichan24 on 2019-06-12.
 */
@Module
abstract class FragmentBuildersModule {
  @ContributesAndroidInjector
  abstract fun contribubteHomeFragment(): HomeFragment

  @ContributesAndroidInjector
  abstract fun contriubteRegisterPatterbFragment(): RegisterPatternFragment

  @ContributesAndroidInjector
  abstract fun contributeApplyPatternFragment(): ApplyPatternFragment
}