package net.chigita.illuminat.apply

import androidx.lifecycle.LifecycleOwner
import com.xwray.groupie.Group
import com.xwray.groupie.Section
import net.chigita.illuminat.vo.PatternWithColor

class PatternsSection(
    private val title: String,
    private val patterns: List<PatternWithColor>?,
    private val lifecycleOwner: LifecycleOwner,
    private val onItemClick: (PatternWithColor) -> Unit
) : Section() {
  private val onPatternItemClickListener = object : OnPatternClickListener {
    override fun onClick(pattern: PatternWithColor) {
      onItemClick(pattern)
    }
  }

  init {
    patterns?.let { reload(it) }
  }

  fun reload(patterns: List<PatternWithColor>) {
    val items = mutableListOf<Group>()
    items.add(TitleItem(title))
    patterns.forEach {
      items.add(PatternItem(it, onPatternItemClickListener))
    }
    update(items)
  }

}