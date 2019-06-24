package net.chigita.illuminat.feature.register

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import net.chigita.illuminat.repository.ColorRepository
import net.chigita.illuminat.repository.PatternRepository
import net.chigita.illuminat.util.onError
import net.chigita.illuminat.vo.Color
import net.chigita.illuminat.vo.Pattern
import javax.inject.Inject

/**
 * Created by chigichan24 on 2019-06-18.
 */
class RegisterPatternViewModel @Inject constructor(
    private val app: Application,
    private val patternRepository: PatternRepository,
    private val colorRepository: ColorRepository
) : AndroidViewModel(app) {

  private val mutableRegisteredPatternStateLiveData = MutableLiveData<RegisteredPatternState>().apply {
    value = RegisteredPatternState.INITIALIZED
  }
  val registeredPatternStateLiveData: LiveData<RegisteredPatternState>
    get() = mutableRegisteredPatternStateLiveData

  fun registerPattern(
      name: String,
      colorInfo: Int
  ) {
    mutableRegisteredPatternStateLiveData.value = RegisteredPatternState.LOADING
    viewModelScope.launch {
      try {
        val pattern = Pattern.new(name)
        val red = colorInfo shr 16 and 0xff
        val green = colorInfo shr 8 and 0xff
        val blue = colorInfo and 0xff
        val inserted = patternRepository.insert(pattern)
        inserted.displayString.forEachIndexed { index, _ ->
          val color = Color.new(
              inserted.uuid,
              index,
              red,
              green,
              blue
          )
          colorRepository.insert(color)
        }
        mutableRegisteredPatternStateLiveData.value = RegisteredPatternState.FINISHED
      } catch (e: Exception) {
        onError(app.applicationContext, e)
        mutableRegisteredPatternStateLiveData.value = RegisteredPatternState.CANCELED
      }
    }
  }
}