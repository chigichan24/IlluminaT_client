package net.chigita.illuminat.feature.apply

import com.xwray.groupie.databinding.BindableItem
import net.chigita.illuminat.R
import net.chigita.illuminat.databinding.ItemPatternBinding
import net.chigita.illuminat.util.EqualableContentsProvider
import net.chigita.illuminat.vo.PatternWithColor

class PatternItem(
    private val pattern: PatternWithColor,
    private val onPatternItemClickListener: OnPatternClickListener
) : BindableItem<ItemPatternBinding>(pattern.id.hashCode().toLong()), EqualableContentsProvider {
  override fun getLayout(): Int = R.layout.item_pattern

  override fun bind(viewBinding: ItemPatternBinding, position: Int) {
    viewBinding.text.text = pattern.displayName
    viewBinding.cardView.setOnClickListener {
      onPatternItemClickListener.onClick(pattern)
    }
  }

  override fun providerEqualableContents(): Array<*> = arrayOf(pattern)

  override fun equals(other: Any?): Boolean = isSameContents(other)

  override fun hashCode(): Int = contentsHash()
}