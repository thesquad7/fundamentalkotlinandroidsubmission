package filemon.submission.bangkit.a1stbangkitsubmmison.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import filemon.submission.bangkit.a1stbangkitsubmmison.R
import filemon.submission.bangkit.a1stbangkitsubmmison.databinding.ActivityFavoriteBinding
import filemon.submission.bangkit.a1stbangkitsubmmison.ui.adapter.FavoriteAdapter
import filemon.submission.bangkit.a1stbangkitsubmmison.ui.viewmodel.DetailViewModelFactory
import filemon.submission.bangkit.a1stbangkitsubmmison.ui.viewmodel.FavoriteViewModel

class FavoriteActivity : AppCompatActivity() {
    private var _binding : ActivityFavoriteBinding? = null
    private val binding get() = _binding
    private lateinit var adapter : FavoriteAdapter

    private lateinit var favoriteViewModel: FavoriteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        favoriteViewModel = obtainViewModel(this@FavoriteActivity)
        favoriteViewModel.getFavorite().observe(this) { githubUserList ->
            if (githubUserList != null) {
                adapter.setFavorite(githubUserList)

            }
        }
        adapter = FavoriteAdapter()
        binding?.rvFavorite?.layoutManager = LinearLayoutManager(this)
        binding?.rvFavorite?.setHasFixedSize(false)
        binding?.rvFavorite?.adapter = adapter
    }
    private fun obtainViewModel(activity: AppCompatActivity): FavoriteViewModel {
        val factory = DetailViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[FavoriteViewModel::class.java]
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}