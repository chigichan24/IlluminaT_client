package net.chigita.illuminat.vo

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Pattern(
    @PrimaryKey val uuid: String,
    val displayString: String,
    val registeredId: Int
) {
  companion object {
    const val UNREGISTERED_ID = -1
    fun new(
        displayString: String,
        registeredId: Int? = null
    ): Pattern {
      if (displayString.isEmpty()) {
        throw IllegalStateException("cannot allow blank name")
      }
      return Pattern(
          UUID.randomUUID().toString(),
          displayString,
          registeredId ?: UNREGISTERED_ID
      )
    }
  }
}