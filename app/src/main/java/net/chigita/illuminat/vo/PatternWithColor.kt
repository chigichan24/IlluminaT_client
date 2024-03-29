package net.chigita.illuminat.vo

data class PatternWithColor(
    val id: String,
    val registeredId: Int,
    val displayName: String,
    val color: List<Int>? = null
) {
  companion object {
    fun load(
        pattern: Pattern,
        colors: List<Color>? = null
    ): PatternWithColor {
      return PatternWithColor(
          pattern.uuid,
          pattern.registeredId,
          pattern.displayString,
          colors?.map { android.graphics.Color.rgb(it.red, it.green, it.blue) }
      )
    }
  }
}