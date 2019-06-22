package net.chigita.illuminat.apply

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import net.chigita.illuminat.R
import net.chigita.illuminat.databinding.FragmentApplyPatternBinding
import net.chigita.illuminat.di.Injectable

/**
 * Created by chigichan24 on 2019-06-22.
 */
class ApplyPAtternFragment : Fragment(), Injectable {
  private lateinit var binding: FragmentApplyPatternBinding

  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
  ): View? {
    binding = DataBindingUtil.inflate(
        inflater,
        R.layout.fragment_apply_pattern,
        container,
        false
    )
    return binding.root
  }
}