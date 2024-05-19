package com.example.taskapp.networks

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService {
    private var retrofit: Retrofit? = null

    // Method for creating a Retrofit client without a token (for login, signup, etc.)
    fun getClient(): Retrofit? {
        val mHttp = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(mHttp)
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl("https://devapp.tayopay.co.za/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        return retrofit
    }

    // Method for creating a Retrofit client with a token (for authenticated requests)
    fun getClient(token: String): Retrofit? {
        val mHttp = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(mHttp)
            .addInterceptor(AuthInterceptor(token))
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl("https://devapp.tayopay.co.za/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        return retrofit
    }
}
