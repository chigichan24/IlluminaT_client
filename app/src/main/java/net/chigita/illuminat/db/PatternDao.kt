package net.chigita.illuminat.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import net.chigita.illuminat.vo.Pattern

@Dao
interface PatternDao {
  @Insert(onConflict = OnConflictStrategy.ABORT)
  suspend fun insert(pattern: Pattern)

  @Update
  suspend fun update(pattern: Pattern)

  @Query("SELECT * FROM pattern WHERE uuid = :uuid")
  suspend fun find(uuid: String): Pattern
}