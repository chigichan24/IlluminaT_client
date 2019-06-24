package net.chigita.illuminat.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import net.chigita.illuminat.feature.apply.ApplyPatternViewModel
import net.chigita.illuminat.feature.register.RegisterPatternViewModel

/**
 * Created by chigichan24 on 2019-06-12.
 */
@Module
abstract class ViewModelModule {
  @Binds
  abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

  @Binds
  @IntoMap
  @ViewModelKey(RegisterPatternViewModel::class)
  abstract fun bindRegisterPatternViewModel(viewModel: RegisterPatternViewModel): ViewModel

  @Binds
  @IntoMap
  @ViewModelKey(ApplyPatternViewModel::class)
  abstract fun bindApplyPatternViewModel(viewModel: ApplyPatternViewModel): ViewModel
}