package net.chigita.illuminat.feature.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import net.chigita.illuminat.R
import net.chigita.illuminat.databinding.FragmentRegisterPattaernBinding
import net.chigita.illuminat.di.Injectable
import net.chigita.illuminat.util.changed
import javax.inject.Inject

/**
 * Created by chigichan24 on 2019-06-14.
 */
class RegisterPatternFragment : Fragment(), Injectable {
  private lateinit var binding: FragmentRegisterPattaernBinding
  @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
  lateinit var registerPatternViewModel: RegisterPatternViewModel

  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
  ): View? {
    binding = DataBindingUtil.inflate(
        inflater,
        R.layout.fragment_register_pattaern,
        container,
        false
    )
    return binding.root
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    registerPatternViewModel = ViewModelProviders.of(this, viewModelFactory)
        .get(RegisterPatternViewModel::class.java)
    registerPatternViewModel.registeredPatternStateLiveData.changed(viewLifecycleOwner) {
      if (it == RegisteredPatternState.LOADING) {
        //TODO Progress bar(?)
      }
      if (it == RegisteredPatternState.FINISHED) {
        navigateToHome()
      }
    }
    binding.colorPickerView.showAlpha(false)
    binding.registerPatternButton.setOnClickListener {
      val color = binding.colorPickerView.color
      registerPatternViewModel.registerPattern(
          binding.patternEditText.text.toString(),
          color
      )
    }
  }

  private fun navigateToHome() = findNavController().popBackStack()
}