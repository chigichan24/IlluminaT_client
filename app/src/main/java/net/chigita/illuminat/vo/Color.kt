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
    val color: String
) {
  companion object {
    fun new(
        patternUuid: String,
        index: Int,
        color: String?
    ): Color {
      return Color(
          UUID.randomUUID().toString(),
          patternUuid,
          index,
          color ?: "FFFFFF"
      )
    }
  }
}