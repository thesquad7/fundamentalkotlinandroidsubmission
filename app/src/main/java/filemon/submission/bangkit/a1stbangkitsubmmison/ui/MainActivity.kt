package filemon.submission.bangkit.a1stbangkitsubmmison.ui

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SearchView
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import filemon.submission.bangkit.a1stbangkitsubmmison.R
import filemon.submission.bangkit.a1stbangkitsubmmison.data.model.UserResponse
import filemon.submission.bangkit.a1stbangkitsubmmison.databinding.ActivityMainBinding
import filemon.submission.bangkit.a1stbangkitsubmmison.ui.adapter.MainAdapter
import filemon.submission.bangkit.a1stbangkitsubmmison.ui.viewmodel.MainViewModel

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var rvList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[MainViewModel::class.java]
        viewModel.listUser.observe(this){gitUserList ->
            showRecyclerList(gitUserList)
        }

        viewModel.loading.observe(this) {
            showLoading(it)
        }
        viewModel.error.observe(this) {
            Toast.makeText(this, "Data Not Found", Toast.LENGTH_SHORT).show()
            viewModel.doneToastError()
        }
        rvList = binding.rvProfileName
        rvList.setHasFixedSize(true)

    }

    private fun showRecyclerList(gitUserList: List<UserResponse> ) {
        rvList.layoutManager = LinearLayoutManager(this)
        val searchUserAdapter = MainAdapter(gitUserList)
        rvList.adapter = searchUserAdapter

        searchUserAdapter.setOnItemClickCallback(object : MainAdapter.OnItemClickCallback{
            override fun onItemClicked(data: UserResponse) {
                val intentDetail = Intent(this@MainActivity, DetailActivity::class.java)
                intentDetail.putExtra(EXTRA_DATA, data.login)
                startActivity(intentDetail)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchItem: MenuItem? = menu.findItem(R.id.search)
        val searchView = searchItem?.actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = resources.getString(R.string.search_hint)

        searchItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(item: MenuItem): Boolean {
                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem): Boolean {
                viewModel.detailUser()
                return true
            }
        })

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.searchUser(query.toString())
                searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.themeSetting -> {
                Intent(this@MainActivity, ChangeThemeActivity::class.java).apply {
                    startActivity(this)
                }
            }
            R.id.favorite -> {
                val intent = Intent(this, FavoriteActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }



    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.rvLoading.visibility = View.VISIBLE
        } else {
            binding.rvLoading.visibility = View.GONE
        }
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}