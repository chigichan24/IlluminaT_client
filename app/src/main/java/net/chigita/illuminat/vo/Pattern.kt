package net.chigita.illuminat.vo

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.lang.IllegalStateException
import java.util.UUID

@Entity
data class Pattern(
    @PrimaryKey val uuid: String,
    val displayString: String
) {
  companion object {
    fun new(
        displayString: String
    ): Pattern {
      if (displayString.isEmpty()) {
        throw IllegalStateException("cannot allow blank name")
      }
      return Pattern(
          UUID.randomUUID().toString(),
          displayString
      )
    }
  }
}