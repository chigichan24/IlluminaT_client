package net.chigita.illuminat.vo

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(
    foreignKeys = [
      ForeignKey(
          entity = Pattern::class,
          parentColumns = ["uuid"],
          childColumns = ["patternUuid"],
          onDelete = ForeignKey.CASCADE
      )
    ]
)
data class Color(
    @PrimaryKey val uuid: String,
    val patternUuid: String,
    val index: Int,
    val red: Int,
    val green: Int,
    val blue: Int
) {
  companion object {
    fun new(
        patternUuid: String,
        index: Int,
        red: Int,
        green: Int,
        blue: Int
    ): Color {
      return Color(
          UUID.randomUUID().toString(),
          patternUuid,
          index,
          red,
          green,
          blue
      )
    }
  }
}