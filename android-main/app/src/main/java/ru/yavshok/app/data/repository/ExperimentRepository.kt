package ru.yavshok.app.data.repository

import android.content.Context
import android.content.SharedPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import ru.yavshok.app.data.api.ApiService
import ru.yavshok.app.data.model.AgeExperiment
import ru.yavshok.app.data.model.ExperimentResponse
import com.google.gson.Gson

class ExperimentRepository(
    private val apiService: ApiService,
    private val context: Context
) {
    private val prefs: SharedPreferences = context.getSharedPreferences("experiments", Context.MODE_PRIVATE)
    private val gson = Gson()
    
    companion object {
        private const val KEY_AGE_EXPERIMENT = "age_experiment"
        private const val KEY_LAST_FETCH_TIME = "last_fetch_time"
        private const val CACHE_DURATION_MS = 60 * 60 * 1000L // 1 hour
    }
    
    suspend fun getAgeExperiment(): AgeExperiment? = withContext(Dispatchers.IO) {
        // Try to get from cache first
        val cachedExperiment = getCachedAgeExperiment()
        val lastFetchTime = prefs.getLong(KEY_LAST_FETCH_TIME, 0)
        val currentTime = System.currentTimeMillis()
        
        // Return cached if still valid
        if (cachedExperiment != null && (currentTime - lastFetchTime) < CACHE_DURATION_MS) {
            return@withContext cachedExperiment
        }
        
        // Fetch from API
        try {
            val response = apiService.getExperiments()
            if (response.isSuccessful) {
                val experimentResponse = response.body()
                val ageExperiment = experimentResponse?.flags?.age
                
                // Cache the result
                if (ageExperiment != null) {
                    cacheAgeExperiment(ageExperiment)
                }
                
                return@withContext ageExperiment
            }
        } catch (e: Exception) {
            // If API fails, return cached version even if expired
            return@withContext cachedExperiment
        }
        
        return@withContext null
    }
    
    private fun getCachedAgeExperiment(): AgeExperiment? {
        val json = prefs.getString(KEY_AGE_EXPERIMENT, null)
        return if (json != null) {
            try {
                gson.fromJson(json, AgeExperiment::class.java)
            } catch (e: Exception) {
                null
            }
        } else {
            null
        }
    }
    
    private fun cacheAgeExperiment(experiment: AgeExperiment) {
        val json = gson.toJson(experiment)
        prefs.edit()
            .putString(KEY_AGE_EXPERIMENT, json)
            .putLong(KEY_LAST_FETCH_TIME, System.currentTimeMillis())
            .apply()
    }
    
    fun clearCache() {
        prefs.edit()
            .remove(KEY_AGE_EXPERIMENT)
            .remove(KEY_LAST_FETCH_TIME)
            .apply()
    }
} 