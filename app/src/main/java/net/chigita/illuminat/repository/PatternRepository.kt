package net.chigita.illuminat.repository

import net.chigita.illuminat.db.PatternDao
import net.chigita.illuminat.vo.Pattern
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by chigichan24 on 2019-06-21.
 */
@Singleton
class PatternRepository @Inject constructor(
    private val patternDao: PatternDao
) {
  suspend fun insert(pattern: Pattern) {
    patternDao.insert(pattern)
  }

  suspend fun load(uuid: String): Pattern {
    return patternDao.find(uuid)
  }
}