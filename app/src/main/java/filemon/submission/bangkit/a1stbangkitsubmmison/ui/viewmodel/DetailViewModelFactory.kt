package filemon.submission.bangkit.a1stbangkitsubmmison.ui.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class DetailViewModelFactory private constructor(private val application: Application): ViewModelProvider.NewInstanceFactory() {
    companion object{
        @Volatile
        private var instance : DetailViewModelFactory? = null

        @JvmStatic
        fun getInstance(application: Application) : DetailViewModelFactory {
            if (instance == null){
                synchronized(DetailViewModelFactory::class.java){
                    instance = DetailViewModelFactory(application)
                }
            }
            return instance as DetailViewModelFactory
        }
    }
}