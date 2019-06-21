package net.chigita.illuminat.register

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import net.chigita.illuminat.repository.PatternRepository
import net.chigita.illuminat.util.onError
import net.chigita.illuminat.vo.Pattern
import javax.inject.Inject

/**
 * Created by chigichan24 on 2019-06-18.
 */
class RegisterPatternViewModel @Inject constructor(
    private val app: Application,
    private val repository: PatternRepository
) : AndroidViewModel(app) {

  private val mutableRegisteredPatternStateLiveData = MutableLiveData<RegisteredPatternState>().apply {
    value = RegisteredPatternState.INITIALIZED
  }
  val registeredPatternStateLiveData: LiveData<RegisteredPatternState>
    get() = mutableRegisteredPatternStateLiveData

  fun registerPattern(
      name: String
  ) {
    mutableRegisteredPatternStateLiveData.value = RegisteredPatternState.LOADING
    viewModelScope.launch {
      try {
        val pattern = Pattern.new(name)
        repository.insert(pattern)
        mutableRegisteredPatternStateLiveData.value = RegisteredPatternState.FINISHED
      } catch (e: Exception) {
        onError(app.applicationContext, e)
        mutableRegisteredPatternStateLiveData.value = RegisteredPatternState.CANCELED
      }
    }
  }
}