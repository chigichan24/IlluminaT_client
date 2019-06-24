package net.chigita.illuminat.feature.apply

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.xwray.groupie.Group
import com.xwray.groupie.Section
import net.chigita.illuminat.R
import net.chigita.illuminat.util.changed
import net.chigita.illuminat.vo.PatternWithColor

class PatternsSection(
    private val context: Context,
    private val applyPatternViewModel: ApplyPatternViewModel,
    private val lifecycleOwner: LifecycleOwner,
    private val onItemClick: (PatternWithColor) -> Unit
) : Section() {
  private val onPatternItemClickListener = object : OnPatternClickListener {
    override fun onClick(pattern: PatternWithColor) {
      onItemClick(pattern)
    }
  }

  init {
    applyPatternViewModel.patternsLiveData.changed(lifecycleOwner) {
      reload()
    }
    applyPatternViewModel.currentPatternLiveData.changed(lifecycleOwner) {
      reload()
    }
  }

  fun reload(){
    val items = mutableListOf<Group>()
    items.add(TitleItem(context.getString(R.string.displayed_pattern)))
    val current = applyPatternViewModel.currentPatternLiveData.value
    current?.let {items.add(PatternItem(it, onPatternItemClickListener))}
    items.add(TitleItem(context.getString(R.string.registered_patterns)))
    applyPatternViewModel.patternsLiveData.value?.forEach {
      items.add(PatternItem(it, onPatternItemClickListener))
    }
    update(items)
  }

}