package net.chigita.illuminat.apply

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import net.chigita.illuminat.R
import net.chigita.illuminat.databinding.FragmentApplyPatternBinding
import net.chigita.illuminat.di.Injectable
import net.chigita.illuminat.util.changed
import net.chigita.illuminat.vo.PatternWithColor
import javax.inject.Inject

/**
 * Created by chigichan24 on 2019-06-22.
 */
class ApplyPatternFragment : Fragment(), Injectable {
  private lateinit var binding: FragmentApplyPatternBinding
  @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
  lateinit var applyPatternViewModel: ApplyPatternViewModel

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

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    applyPatternViewModel = ViewModelProviders.of(this, viewModelFactory)
        .get(ApplyPatternViewModel::class.java)
    applyPatternViewModel.getPatterns()
    applyPatternViewModel.patternsLiveData.changed(viewLifecycleOwner) {
      binding.recyclerViewRegisteredPattern.adapter = GroupAdapter<ViewHolder>().apply {
        add(
            PatternsSection(
                requireContext().getString(R.string.registered_patterns),
                it,
                viewLifecycleOwner
            ) { Unit }
        )
      }
    }

    applyPatternViewModel.getCurrentPattern()
    applyPatternViewModel.currentPatternStateLiveData.changed(viewLifecycleOwner) {
      Log.d("state", it.name)
      if (it == CurrentPatternState.CANCELED) {

      }
    }
    applyPatternViewModel.currentPatternLiveData.changed(viewLifecycleOwner) {
      binding.recyclerViewCurrentPattern.adapter = GroupAdapter<ViewHolder>().apply {
        add(
            PatternsSection(
                requireContext().getString(R.string.displayed_pattern),
                listOf(it),
                viewLifecycleOwner
            ) { Unit }
        )
      }
    }
  }
}