package net.chigita.illuminat.apply

import net.chigita.illuminat.vo.PatternWithColor

interface OnPatternClickListener {
  fun onClick(pattern: PatternWithColor)
}