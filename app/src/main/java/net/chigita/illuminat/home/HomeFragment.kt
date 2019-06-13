package net.chigita.illuminat.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import net.chigita.illuminat.R
import net.chigita.illuminat.databinding.FragmentHomeBinding
import net.chigita.illuminat.di.Injectable

/**
 * Created by chigichan24 on 2019-06-12.
 */
class HomeFragment : Fragment(), Injectable {
  private lateinit var binding: FragmentHomeBinding
  //@Inject lateinit var viewModelFactory: ViewModelProvider.Factory

  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
  ): View? {
    binding = DataBindingUtil.inflate(
        inflater,
        R.layout.fragment_home,
        container,
        false
    )
    return binding.root
  }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
      super.onActivityCreated(savedInstanceState)
      binding.lottieLight.setOnClickListener {
        binding.lottieLight.playAnimation()
      }
    }
}