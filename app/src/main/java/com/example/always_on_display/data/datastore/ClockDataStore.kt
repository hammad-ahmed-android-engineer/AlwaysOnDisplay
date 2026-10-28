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
        val KEY_SELECTED_CATEGORY = stringPreferencesKey("selected_category")
        val KEY_SELECTED_STYLE = stringPreferencesKey("selected_style")
        val KEY_SERVICE_ACTIVATED = booleanPreferencesKey("service_activated")

        // Legacy keys for backward compatibility
        val KEY_ACTIVE_CATEGORY = stringPreferencesKey("active_category")
        val KEY_DIGITAL_STYLE = stringPreferencesKey("digital_style")
    }

    val clockConfigFlow: Flow<ClockConfig> = context.dataStore.data.map { prefs ->
        val categoryStr = prefs[KEY_SELECTED_CATEGORY] ?: prefs[KEY_ACTIVE_CATEGORY] ?: ClockCategory.DIGITAL.name
        val category = try {
            ClockCategory.valueOf(categoryStr)
        } catch (_: Exception) {
            ClockCategory.DIGITAL
        }

        val style = prefs[KEY_SELECTED_STYLE] ?: prefs[KEY_DIGITAL_STYLE] ?: "digital1"

        ClockConfig(
            selectedCategory = category,
            selectedStyle = style,
            isServiceActivated = prefs[KEY_SERVICE_ACTIVATED] ?: false
        )
    }

    suspend fun updateSelectedClock(category: ClockCategory, styleKey: String) {
        context.dataStore.edit { prefs ->
            prefs[KEY_SELECTED_CATEGORY] = category.name
            prefs[KEY_SELECTED_STYLE] = styleKey
        }
    }

    suspend fun updateServiceState(isActivated: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[KEY_SERVICE_ACTIVATED] = isActivated
        }
    }
}
