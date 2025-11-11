package com.telegramplus.app.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "telegram_preferences")

class PreferencesManager(private val context: Context) {
    
    companion object {
        val CURRENT_USER_ID = stringPreferencesKey("current_user_id")
        val IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")
        val GHOST_MODE_ENABLED = booleanPreferencesKey("ghost_mode_enabled")
        val ANTI_DELETE_ENABLED = booleanPreferencesKey("anti_delete_enabled")
        val AUTO_DOWNLOAD_MEDIA = booleanPreferencesKey("auto_download_media")
        val THEME_MODE = intPreferencesKey("theme_mode")
        val CHAT_FONT_SIZE = intPreferencesKey("chat_font_size")
        val NOTIFICATIONS_ENABLED = booleanPreferencesKey("notifications_enabled")
        val SHOW_ONLINE_STATUS = booleanPreferencesKey("show_online_status")
        val BIOMETRIC_ENABLED = booleanPreferencesKey("biometric_enabled")
        val SAVE_TO_GALLERY = booleanPreferencesKey("save_to_gallery")
        val FORWARD_WITHOUT_QUOTE = booleanPreferencesKey("forward_without_quote")
        val HIDE_KEYBOARD_ON_SCROLL = booleanPreferencesKey("hide_keyboard_on_scroll")
    }
    
    val currentUserId: Flow<String?> = context.dataStore.data
        .map { preferences -> preferences[CURRENT_USER_ID] }
    
    val isLoggedIn: Flow<Boolean> = context.dataStore.data
        .map { preferences -> preferences[IS_LOGGED_IN] ?: false }
    
    val ghostModeEnabled: Flow<Boolean> = context.dataStore.data
        .map { preferences -> preferences[GHOST_MODE_ENABLED] ?: false }
    
    val antiDeleteEnabled: Flow<Boolean> = context.dataStore.data
        .map { preferences -> preferences[ANTI_DELETE_ENABLED] ?: false }
    
    val autoDownloadMedia: Flow<Boolean> = context.dataStore.data
        .map { preferences -> preferences[AUTO_DOWNLOAD_MEDIA] ?: true }
    
    val themeMode: Flow<Int> = context.dataStore.data
        .map { preferences -> preferences[THEME_MODE] ?: 0 } // 0=system, 1=light, 2=dark
    
    val chatFontSize: Flow<Int> = context.dataStore.data
        .map { preferences -> preferences[CHAT_FONT_SIZE] ?: 14 }
    
    val notificationsEnabled: Flow<Boolean> = context.dataStore.data
        .map { preferences -> preferences[NOTIFICATIONS_ENABLED] ?: true }
    
    val showOnlineStatus: Flow<Boolean> = context.dataStore.data
        .map { preferences -> preferences[SHOW_ONLINE_STATUS] ?: true }
    
    val biometricEnabled: Flow<Boolean> = context.dataStore.data
        .map { preferences -> preferences[BIOMETRIC_ENABLED] ?: false }
    
    val saveToGallery: Flow<Boolean> = context.dataStore.data
        .map { preferences -> preferences[SAVE_TO_GALLERY] ?: false }
    
    val forwardWithoutQuote: Flow<Boolean> = context.dataStore.data
        .map { preferences -> preferences[FORWARD_WITHOUT_QUOTE] ?: false }
    
    val hideKeyboardOnScroll: Flow<Boolean> = context.dataStore.data
        .map { preferences -> preferences[HIDE_KEYBOARD_ON_SCROLL] ?: true }
    
    suspend fun setCurrentUserId(userId: String) {
        context.dataStore.edit { preferences ->
            preferences[CURRENT_USER_ID] = userId
        }
    }
    
    suspend fun setLoggedIn(isLoggedIn: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[IS_LOGGED_IN] = isLoggedIn
        }
    }
    
    suspend fun setGhostMode(enabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[GHOST_MODE_ENABLED] = enabled
        }
    }
    
    suspend fun setAntiDelete(enabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[ANTI_DELETE_ENABLED] = enabled
        }
    }
    
    suspend fun setAutoDownloadMedia(enabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[AUTO_DOWNLOAD_MEDIA] = enabled
        }
    }
    
    suspend fun setThemeMode(mode: Int) {
        context.dataStore.edit { preferences ->
            preferences[THEME_MODE] = mode
        }
    }
    
    suspend fun setChatFontSize(size: Int) {
        context.dataStore.edit { preferences ->
            preferences[CHAT_FONT_SIZE] = size
        }
    }
    
    suspend fun setNotificationsEnabled(enabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[NOTIFICATIONS_ENABLED] = enabled
        }
    }
    
    suspend fun setShowOnlineStatus(show: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[SHOW_ONLINE_STATUS] = show
        }
    }
    
    suspend fun setBiometricEnabled(enabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[BIOMETRIC_ENABLED] = enabled
        }
    }
    
    suspend fun setSaveToGallery(enabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[SAVE_TO_GALLERY] = enabled
        }
    }
    
    suspend fun setForwardWithoutQuote(enabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[FORWARD_WITHOUT_QUOTE] = enabled
        }
    }
    
    suspend fun setHideKeyboardOnScroll(enabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[HIDE_KEYBOARD_ON_SCROLL] = enabled
        }
    }
    
    suspend fun clearAll() {
        context.dataStore.edit { preferences ->
            preferences.clear()
        }
    }
}
