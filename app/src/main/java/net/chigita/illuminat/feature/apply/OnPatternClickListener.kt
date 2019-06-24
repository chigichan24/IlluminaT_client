package net.chigita.illuminat.feature.apply

import net.chigita.illuminat.vo.PatternWithColor

interface OnPatternClickListener {
  fun onClick(pattern: PatternWithColor)
}