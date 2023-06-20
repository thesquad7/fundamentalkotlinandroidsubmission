package filemon.submission.bangkit.a1stbangkitsubmmison.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import filemon.submission.bangkit.a1stbangkitsubmmison.ui.adapter.SetPreference
import kotlinx.coroutines.launch

class ThemeSetingVM (private val pref: SetPreference) : ViewModel() {
    fun getThemeSettings(): LiveData<Boolean> {
        return pref.getThemeSetting().asLiveData()
    }

    fun saveThemeSetting(isDarkModeActive: Boolean) {
        viewModelScope.launch {
            pref.saveThemeSetting(isDarkModeActive)
        }
    }
}