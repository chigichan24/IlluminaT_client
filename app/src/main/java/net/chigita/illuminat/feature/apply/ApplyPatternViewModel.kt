package net.chigita.illuminat.feature.apply

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import net.chigita.illuminat.repository.ColorRepository
import net.chigita.illuminat.repository.PatternRepository
import net.chigita.illuminat.util.onError
import net.chigita.illuminat.vo.Pattern
import net.chigita.illuminat.vo.PatternWithColor
import javax.inject.Inject

class ApplyPatternViewModel @Inject constructor(
    private val app: Application,
    private val patternRepository: PatternRepository,
    private val colorRepository: ColorRepository
) : AndroidViewModel(app) {

  private val mutableCurrentPatternLiveData = MutableLiveData<PatternWithColor>()
  val currentPatternLiveData: LiveData<PatternWithColor>
    get() = mutableCurrentPatternLiveData
  private val mutableCurrentPatternStateLiveData = MutableLiveData<CurrentPatternState>().apply {
    value = CurrentPatternState.INITIALIZED
  }
  val currentPatternStateLiveData: LiveData<CurrentPatternState>
    get() = mutableCurrentPatternStateLiveData

  private val mutablePatternsLiveData = MutableLiveData<List<PatternWithColor>>()
  val patternsLiveData: LiveData<List<PatternWithColor>>
    get() = mutablePatternsLiveData
  private val mutablePatternsStateLiveData = MutableLiveData<PatternsState>().apply {
    value = PatternsState.INITIALIZED
  }
  val patternsStateLiveData: LiveData<PatternsState>
    get() = mutablePatternsStateLiveData

  fun getCurrentPattern() {
    mutableCurrentPatternStateLiveData.value = CurrentPatternState.LOADING
    viewModelScope.launch {
      try {
        val pattern = withContext(Dispatchers.IO) {
          patternRepository.loadCurrentPattern()
        }
        if (pattern == Pattern.EMPTY) {
          throw IllegalStateException("pattern not showing now")
        }
        val colors = withContext(Dispatchers.IO) {
          colorRepository.loadWithPatternUuid(pattern.uuid)
        }
        mutableCurrentPatternLiveData.value = PatternWithColor.load(pattern, colors)
        mutableCurrentPatternStateLiveData.value = CurrentPatternState.FINISHED
      } catch (e: Exception) {
        mutableCurrentPatternStateLiveData.value = CurrentPatternState.CANCELED
      }
    }
  }

  fun getPatterns() {
    mutablePatternsStateLiveData.value = PatternsState.LOADING
    viewModelScope.launch {
      try {
        val result = withContext(Dispatchers.IO) {
          val patterns = patternRepository.loadAll()
          patterns.map {
            val colors = colorRepository.loadWithPatternUuid(it.uuid)
            PatternWithColor.load(it, colors)
          }
        }
        mutablePatternsLiveData.value = result
        mutablePatternsStateLiveData.value = PatternsState.FINISHED
      } catch (e: Exception) {
        onError(app.applicationContext, e)
        mutablePatternsStateLiveData.value = PatternsState.CANCELED
      }
    }
  }

  fun applyPattern(pattern: PatternWithColor) {
    viewModelScope.launch {
      try {
        withContext(Dispatchers.IO){
          if (pattern.registeredId == Pattern.UNREGISTERED_ID) {
            val id = patternRepository.registerSample()
            patternRepository.update(
              Pattern(
                pattern.id,
                pattern.displayName,
                id
              )
            )
            patternRepository.reflect(id)
          } else {
            patternRepository.reflect(pattern.registeredId)
          }
        }
        getCurrentPattern()
      } catch (e: Exception) {
        onError(app.applicationContext, e)
      }
    }
  }

  fun stopPattern() {
    viewModelScope.launch {
      try {
        withContext(Dispatchers.IO) {
          patternRepository.stop()
        }
        getCurrentPattern()
      } catch (e: Exception) {
        onError(app.applicationContext, e)
      }
    }
  }
}