package net.chigita.illuminat.apply

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.chigita.illuminat.repository.ColorRepository
import net.chigita.illuminat.repository.PatternRepository
import net.chigita.illuminat.util.onError
import net.chigita.illuminat.vo.Pattern
import java.lang.Exception
import javax.inject.Inject

class ApplyPatternViewModel @Inject constructor(
    private val app: Application,
    private val patternRepository: PatternRepository,
    private val colorRepository: ColorRepository
) : AndroidViewModel(app) {

  private val mutableCurrentPatternLiveData = MutableLiveData<Pattern>()
  val currentPatternLiveData: LiveData<Pattern>
    get() = mutableCurrentPatternLiveData
  private val mutableCurrentPatternStateLiveData = MutableLiveData<CurrentPatternState>().apply {
    value = CurrentPatternState.INITIALIZED
  }
  val currentPatternStateLiveData: LiveData<CurrentPatternState>
    get() = mutableCurrentPatternStateLiveData

  fun getCurrentPattern() {
    mutableCurrentPatternStateLiveData.value = CurrentPatternState.LOADING
    viewModelScope.launch(Dispatchers.IO) {
      try {
        val pattern = patternRepository.loadCurrentPattern()
        mutableCurrentPatternLiveData.value = pattern
        mutableCurrentPatternStateLiveData.value = CurrentPatternState.FINISHED
      } catch (e: Exception) {
        onError(app.applicationContext, e)
        mutableCurrentPatternStateLiveData.value = CurrentPatternState.CANCELED
      }
    }

  }
}