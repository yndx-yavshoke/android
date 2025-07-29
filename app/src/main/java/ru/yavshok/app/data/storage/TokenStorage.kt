package ru.yavshok.app.data.storage

import android.content.Context
import android.content.SharedPreferences
import android.util.Log

class TokenStorage(context: Context) {
    
    private val prefs: SharedPreferences = context.getSharedPreferences(
        PREFS_NAME, Context.MODE_PRIVATE
    )

    fun saveToken(token: String, sync: Boolean = false) {
        val editor = prefs.edit().putString(TOKEN_KEY, token)
        if (sync) editor.commit() else editor.apply()
    }
    
    fun getToken(): String? {
        return prefs.getString(TOKEN_KEY, null)
    }
    

    
    fun isLoggedIn(): Boolean {
        val token = prefs.getString(TOKEN_KEY, null)
        Log.d("TokenStorage", "isLoggedIn check: token = $token")
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