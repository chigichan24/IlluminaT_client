package net.chigita.illuminat.apply

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import net.chigita.illuminat.repository.ColorRepository
import net.chigita.illuminat.repository.PatternRepository
import javax.inject.Inject

class ApplyPatternViewModel @Inject constructor(
    private val app: Application,
    private val patternRepository: PatternRepository,
    private val colorRepository: ColorRepository
) : AndroidViewModel(app) {

}