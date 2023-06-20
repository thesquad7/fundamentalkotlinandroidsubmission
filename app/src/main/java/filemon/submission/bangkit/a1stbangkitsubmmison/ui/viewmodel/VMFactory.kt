package filemon.submission.bangkit.a1stbangkitsubmmison.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import filemon.submission.bangkit.a1stbangkitsubmmison.ui.adapter.SetPreference

class VMFactory (private val pref: SetPreference) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ThemeSetingVM::class.java)) {
            return ThemeSetingVM(pref) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class : " + modelClass.name)
    }
}