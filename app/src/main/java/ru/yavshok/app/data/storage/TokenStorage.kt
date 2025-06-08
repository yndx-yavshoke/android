package ru.yavshok.app.data.storage

import android.content.Context
import android.content.SharedPreferences

class TokenStorage(context: Context) {
    
    private val prefs: SharedPreferences = context.getSharedPreferences(
        PREFS_NAME, Context.MODE_PRIVATE
    )
    
    fun saveToken(token: String) {
        prefs.edit().putString(TOKEN_KEY, token).apply()
    }
    
    fun getToken(): String? {
        return prefs.getString(TOKEN_KEY, null)
    }
    
    fun saveUser(userId: Int, email: String, name: String) {
        prefs.edit()
            .putInt(USER_ID_KEY, userId)
            .putString(USER_EMAIL_KEY, email)
            .putString(USER_NAME_KEY, name)
            .apply()
    }
    
    fun getUserId(): Int {
        return prefs.getInt(USER_ID_KEY, -1)
    }
    
    fun getUserEmail(): String? {
        return prefs.getString(USER_EMAIL_KEY, null)
    }
    
    fun getUserName(): String? {
        return prefs.getString(USER_NAME_KEY, null)
    }
    
    fun isLoggedIn(): Boolean {
        return getToken() != null
    }
    
    fun logout() {
        prefs.edit().clear().apply()
    }
    
    companion object {
        private const val PREFS_NAME = "yavshok_prefs"
        private const val TOKEN_KEY = "auth_token"
        private const val USER_ID_KEY = "user_id"
        private const val USER_EMAIL_KEY = "user_email"
        private const val USER_NAME_KEY = "user_name"
    }
} 