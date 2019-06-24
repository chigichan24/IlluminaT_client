package net.chigita.illuminat.apply

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import net.chigita.illuminat.R
import net.chigita.illuminat.databinding.FragmentApplyPatternBinding
import net.chigita.illuminat.di.Injectable
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
    applyPatternViewModel.getCurrentPattern()
    binding.recyclerView.adapter = GroupAdapter<ViewHolder>().apply {
      add(
          PatternsSection(
              requireContext(),
              applyPatternViewModel,
              viewLifecycleOwner
          ) {
            AlertDialog.Builder(requireContext())
                .setTitle(R.string.confirmation)
                .setMessage(R.string.apply_pattern_message)
                .setPositiveButton(R.string.ok
                ) { _, _ ->
                  applyPatternViewModel.applyPattern(it)
                }
                .setNegativeButton(R.string.cancel, null)
                .show()
          }
      )
    }
  }
}