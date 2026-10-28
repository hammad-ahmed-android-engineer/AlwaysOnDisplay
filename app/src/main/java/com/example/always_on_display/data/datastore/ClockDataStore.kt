package com.example.always_on_display.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.always_on_display.domain.model.ClockCategory
import com.example.always_on_display.domain.model.ClockConfig
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "always_on_display_settings")

/**
 * DataStore wrapper for clock settings and preferences.
 */
class ClockDataStore(private val context: Context) {

    companion object {
        val KEY_ACTIVE_CATEGORY = stringPreferencesKey("active_category")
        val KEY_DIGITAL_STYLE = stringPreferencesKey("digital_style")
        val KEY_ABSTRACT_STYLE = stringPreferencesKey("abstract_style")
        val KEY_NEON_STYLE = stringPreferencesKey("neon_style")
        val KEY_ATOMIC_STYLE = stringPreferencesKey("atomic_style")
        val KEY_SERVICE_ACTIVATED = booleanPreferencesKey("service_activated")
    }

    val clockConfigFlow: Flow<ClockConfig> = context.dataStore.data.map { prefs ->
        val categoryStr = prefs[KEY_ACTIVE_CATEGORY] ?: ClockCategory.DIGITAL.name
        val category = try {
            ClockCategory.valueOf(categoryStr)
        } catch (_: Exception) {
            ClockCategory.DIGITAL
        }

        ClockConfig(
            activeCategory = category,
            selectedDigitalStyle = prefs[KEY_DIGITAL_STYLE] ?: "digital1",
            selectedAbstractStyle = prefs[KEY_ABSTRACT_STYLE] ?: "abstract1",
            selectedNeonStyle = prefs[KEY_NEON_STYLE] ?: "neon1",
            selectedAtomicStyle = prefs[KEY_ATOMIC_STYLE] ?: "atomic1",
            isServiceActivated = prefs[KEY_SERVICE_ACTIVATED] ?: false
        )
    }

    suspend fun updateActiveCategory(category: ClockCategory) {
        context.dataStore.edit { prefs ->
            prefs[KEY_ACTIVE_CATEGORY] = category.name
        }
    }

    suspend fun updateDigitalStyle(styleKey: String) {
        context.dataStore.edit { prefs ->
            prefs[KEY_DIGITAL_STYLE] = styleKey
        }
    }

    suspend fun updateAbstractStyle(styleKey: String) {
        context.dataStore.edit { prefs ->
            prefs[KEY_ABSTRACT_STYLE] = styleKey
        }
    }

    suspend fun updateNeonStyle(styleKey: String) {
        context.dataStore.edit { prefs ->
            prefs[KEY_NEON_STYLE] = styleKey
        }
    }

    suspend fun updateAtomicStyle(styleKey: String) {
        context.dataStore.edit { prefs ->
            prefs[KEY_ATOMIC_STYLE] = styleKey
        }
    }

    suspend fun updateServiceState(isActivated: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[KEY_SERVICE_ACTIVATED] = isActivated
        }
    }
}
