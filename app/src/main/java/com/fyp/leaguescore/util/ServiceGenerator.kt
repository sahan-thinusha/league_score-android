package com.fyp.leaguescore.util

import com.fyp.leaguescore.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class ServiceGenerator {
    companion object {
        private var retrofit: Retrofit? = null
        fun getClient(): Retrofit? {
            if (retrofit == null) {
                val interceptor = HttpLoggingInterceptor()
                interceptor.level = HttpLoggingInterceptor.Level.BODY
                val httpClientBuilder = OkHttpClient.Builder()
                httpClientBuilder.addInterceptor(interceptor)
                retrofit = Retrofit.Builder()
                    .baseUrl(BuildConfig.API_URL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClientBuilder.build())
                    .build()
            }
            return retrofit
        }
    }
}