package net.chigita.illuminat.repository

import net.chigita.illuminat.api.IlluminatService
import net.chigita.illuminat.api.request.IdRequest
import net.chigita.illuminat.api.request.PatternRequest
import net.chigita.illuminat.db.PatternDao
import net.chigita.illuminat.util.FontAlphabetMatrix
import net.chigita.illuminat.util.FontHiraganaMatrix
import net.chigita.illuminat.util.FontKatakanaMatrix
import net.chigita.illuminat.util.FontSignMatrix
import net.chigita.illuminat.vo.Pattern
import net.chigita.illuminat.vo.PatternWithColor
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

  suspend fun update(pattern: Pattern): Pattern {
    patternDao.update(pattern)
    return pattern
  }

  suspend fun loadCurrentPattern(): Pattern {
    val id = illuminatService.getCurrentPattern().id
    if (id == 0) {
      return Pattern.EMPTY
    }
    return patternDao.findWithRegisteredId(id)
  }

  suspend fun load(uuid: String): Pattern {
    return patternDao.find(uuid)
  }

  suspend fun loadAll(): List<Pattern> {
    return patternDao.findAll()
  }

  suspend fun reflect(id: Int) {
    illuminatService.playPattern(IdRequest(id))
  }

  suspend fun registerSample(): Int {
    val sample = listOf(
        listOf(
            0, 0, 1, 0, 0, 0, 0, 0,
            1, 1, 1, 1, 1, 1, 0, 0,
            0, 0, 1, 0, 0, 0, 0, 0,
            0, 0, 1, 1, 1, 1, 0, 0,
            0, 1, 1, 0, 1, 0, 1, 0,
            1, 0, 1, 1, 0, 0, 1, 0,
            0, 1, 1, 0, 0, 1, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0
        ).map { 255 * it }
    )
    val ret = illuminatService.registerPattern(PatternRequest(sample))
    return ret.id
  }

  suspend fun register(pattern: PatternWithColor): Int {
    val matrix = pattern.displayName.map {
      val font = FontSignMatrix.matrixMap.toMutableMap() + FontAlphabetMatrix.matrixMap.toMutableMap() + FontHiraganaMatrix.matrixMap.toMutableMap() + FontKatakanaMatrix.matrixMap.toMutableMap()
      font[it.toString()]?.map {
        it * (((pattern.color?.get(0) ?: 0) shl 16) + ((pattern.color?.get(1)
            ?: 0) shl 8) + (pattern.color?.get(2) ?: 0))
      } ?: listOf(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
          1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
          1, 1, 1, 1, 1, 1, 1)
    }
    val ret = illuminatService.registerPattern(PatternRequest(matrix))
    return ret.id
  }

  suspend fun stop() {
    illuminatService.playPattern(IdRequest(0))
  }
}