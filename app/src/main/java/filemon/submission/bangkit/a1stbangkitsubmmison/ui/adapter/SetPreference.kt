package filemon.submission.bangkit.a1stbangkitsubmmison.ui.adapter

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SetPreference private constructor(private val dataStore: DataStore<Preferences>) {
    companion object {
        private val THEME_KEY = booleanPreferencesKey("theme_setting")

        @Volatile
        private var INSTANCE: SetPreference? = null
        fun getInstance(dataStore: DataStore<Preferences>): SetPreference {
            return INSTANCE ?: synchronized(this) {
                val instance = SetPreference(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
    fun getThemeSetting(): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[THEME_KEY] ?: false
        }
    }
    suspend fun saveThemeSetting(isDarkModeActive: Boolean) {
        dataStore.edit { preferences ->
            preferences[THEME_KEY] = isDarkModeActive
        }
    }
}