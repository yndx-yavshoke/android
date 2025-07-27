package ru.yavshok.app.data.network

import android.content.Context
import android.util.Log
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.yavshok.app.data.api.ApiService
import ru.yavshok.app.data.api.UserApiService
import ru.yavshok.app.data.storage.TokenStorage
import java.util.concurrent.TimeUnit

object NetworkModule {
    
    // Use the working API URL from the Swagger documentation
    private const val BASE_URL = "https://api.yavshok.ru/"

    private fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }
    
    private fun provideAuthenticatedOkHttpClient(context: Context): OkHttpClient {
        val tokenStorage = TokenStorage(context)
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        
        val authInterceptor = Interceptor { chain ->
            val originalRequest = chain.request()
            val token = tokenStorage.getToken()
            
            Log.d("NetworkModule", "🔐 Auth interceptor - Token: ${if (token != null) "***EXISTS***" else "NULL"}")
            Log.d("NetworkModule", "📡 Request URL: ${originalRequest.url}")
            
            val newRequest = if (token != null) {
                Log.d("NetworkModule", "✅ Adding Bearer token to request")
                originalRequest.newBuilder()
                    .addHeader("Authorization", "Bearer $token")
                    .build()
            } else {
                Log.e("NetworkModule", "❌ No token available!")
                originalRequest
            }
            
            chain.proceed(newRequest)
        }

        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    private fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(provideOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    
    private fun provideAuthenticatedRetrofit(context: Context): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(provideAuthenticatedOkHttpClient(context))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun provideApiService(): ApiService {
        return provideRetrofit().create(ApiService::class.java)
    }
    
    fun provideUserApiService(context: Context): UserApiService {
        return provideAuthenticatedRetrofit(context).create(UserApiService::class.java)
    }
}