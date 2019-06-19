package net.chigita.illuminat.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import net.chigita.illuminat.vo.Color

@Dao
interface ColorDao {
  @Insert(onConflict = OnConflictStrategy.ABORT)
  suspend fun insert(color: Color)

  @Update
  suspend fun update(color: Color)

  @Query("SELECT * FROM color WHERE uuid = :uuid")
  suspend fun find(uuid: String): Color

  @Query("SELECT * FROM color WHERE patternUuid = :patternUuid")
  suspend fun findColors(patternUuid: String): List<Color>
}