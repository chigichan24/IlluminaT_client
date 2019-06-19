package net.chigita.illuminat.db

import androidx.room.Database
import androidx.room.RoomDatabase
import net.chigita.illuminat.vo.Color
import net.chigita.illuminat.vo.Pattern

@Database(
    entities = [
      Color::class,
      Pattern::class
    ],
    version = 1,
    exportSchema = false
)

abstract class IlluminatDb : RoomDatabase() {
  abstract fun colorDao(): ColorDao

  abstract fun patternDao(): PatternDao
}