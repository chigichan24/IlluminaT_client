package net.chigita.illuminat

import android.app.Activity
import android.app.Application
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import net.chigita.illuminat.di.AppInjector
import javax.inject.Inject

/**
 * Created by chigichan24 on 2019-06-12.
 */
class App : Application(), HasActivityInjector {
  @Inject
  lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

  override fun onCreate() {
    super.onCreate()
    AppInjector.init(this)
  }

  override fun activityInjector() = dispatchingAndroidInjector
}