package filemon.submission.bangkit.a1stbangkitsubmmison.ui.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import filemon.submission.bangkit.a1stbangkitsubmmison.data.database.User
import filemon.submission.bangkit.a1stbangkitsubmmison.data.util.UserRepository

class FavoriteViewModel(application: Application) : ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    private val _error = MutableLiveData<Boolean>()

    val loading : LiveData<Boolean> = _isLoading
    val error: LiveData<Boolean> = _error

    private val userRepo : UserRepository = UserRepository(application)
    fun getFavorite() : LiveData<List<User>> = userRepo.getAllFavorites()
}