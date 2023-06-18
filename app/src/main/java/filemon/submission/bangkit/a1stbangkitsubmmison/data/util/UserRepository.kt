package filemon.submission.bangkit.a1stbangkitsubmmison.data.util

import android.app.Application
import androidx.lifecycle.LiveData
import filemon.submission.bangkit.a1stbangkitsubmmison.data.database.User
import filemon.submission.bangkit.a1stbangkitsubmmison.data.database.UserDAO
import filemon.submission.bangkit.a1stbangkitsubmmison.data.database.UserRoomDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class UserRepository(application: Application) {
    private val mUserDao: UserDAO
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = UserRoomDatabase.getDatabase(application)
        mUserDao = db.userDao()
    }
    fun insert(user: User) {
        executorService.execute { mUserDao.insertFavorite(user) }
    }

    fun delete(id: Int) {
        executorService.execute { mUserDao.removeFavorite(id) }
    }
}