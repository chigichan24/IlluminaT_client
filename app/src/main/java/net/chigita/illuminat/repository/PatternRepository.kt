package net.chigita.illuminat.repository

import net.chigita.illuminat.api.IlluminatService
import net.chigita.illuminat.db.PatternDao
import net.chigita.illuminat.vo.Pattern
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by chigichan24 on 2019-06-21.
 */
@Singleton
class PatternRepository @Inject constructor(
    private val patternDao: PatternDao,
    private val illuminatService: IlluminatService
) {
  suspend fun insert(pattern: Pattern): Pattern {
    patternDao.insert(pattern)
    return pattern
  }

  suspend fun loadCurrentPattern(): Pattern {
    val id = illuminatService.getCurrentPattern().id
    return patternDao.findWithRegisteredId(id)
  }

  suspend fun load(uuid: String): Pattern {
    return patternDao.find(uuid)
  }
}