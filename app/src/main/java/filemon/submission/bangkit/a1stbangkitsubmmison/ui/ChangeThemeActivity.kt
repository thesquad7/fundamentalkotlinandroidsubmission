package filemon.submission.bangkit.a1stbangkitsubmmison.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.switchmaterial.SwitchMaterial
import filemon.submission.bangkit.a1stbangkitsubmmison.R
import filemon.submission.bangkit.a1stbangkitsubmmison.databinding.ActivityChangeThemeBinding
import filemon.submission.bangkit.a1stbangkitsubmmison.ui.adapter.SetPreference
import filemon.submission.bangkit.a1stbangkitsubmmison.ui.viewmodel.ThemeSetingVM
import filemon.submission.bangkit.a1stbangkitsubmmison.ui.viewmodel.VMFactory
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
class ChangeThemeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChangeThemeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChangeThemeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val switchTheme = findViewById<SwitchMaterial>(R.id.switch_theme)
        val pref = SetPreference.getInstance(dataStore)
        val themeViewModel = ViewModelProvider(this, VMFactory(pref)).get(
            ThemeSetingVM::class.java
        )
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        themeViewModel.getThemeSettings().observe(this) { isDarkModeActive: Boolean ->
            if (isDarkModeActive) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                switchTheme.isChecked = true
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                switchTheme.isChecked = false
            }
        }
        switchTheme.setOnCheckedChangeListener { _: CompoundButton?, isChecked: Boolean ->
            themeViewModel.saveThemeSetting(isChecked)
        }
    }
}