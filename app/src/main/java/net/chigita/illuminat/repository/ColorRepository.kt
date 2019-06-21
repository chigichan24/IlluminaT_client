package net.chigita.illuminat.repository

import net.chigita.illuminat.db.ColorDao
import net.chigita.illuminat.vo.Color
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by chigichan24 on 2019-06-21.
 */
@Singleton
class ColorRepository @Inject constructor(
    private val colorDao: ColorDao
) {
  suspend fun insert(color: Color) {
    colorDao.insert(color)
  }

  suspend fun load(uuid: String): Color {
    return colorDao.find(uuid)
  }
}