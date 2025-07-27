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
    

    
    fun isLoggedIn(): Boolean {
        return getToken() != null
    }
    
    fun logout() {
        prefs.edit().clear().apply()
    }
    
    companion object {
        private const val PREFS_NAME = "yavshok_prefs"
        private const val TOKEN_KEY = "auth_token"
    }
} 