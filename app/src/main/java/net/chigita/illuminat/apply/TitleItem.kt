package net.chigita.illuminat.apply

import com.xwray.groupie.databinding.BindableItem
import net.chigita.illuminat.R
import net.chigita.illuminat.databinding.ItemTitleBinding
import net.chigita.illuminat.util.EqualableContentsProvider

class TitleItem (
    private val displayString: String
) : BindableItem<ItemTitleBinding>(displayString.hashCode().toLong()), EqualableContentsProvider {
  override fun getLayout(): Int = R.layout.item_title

  override fun bind(viewBinding: ItemTitleBinding, position: Int) {
    viewBinding.title.text = displayString
  }

  override fun providerEqualableContents(): Array<*> = arrayOf(displayString)

  override fun equals(other: Any?): Boolean = isSameContents(other)

  override fun hashCode(): Int = contentsHash()
}