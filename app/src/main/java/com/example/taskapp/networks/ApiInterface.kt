package com.example.taskapp.networks

import com.example.taskapp.networks.response.DetailsResponse
import com.example.taskapp.networks.response.LoginResponse
import com.example.taskapp.networks.response.RegisterResponse
import com.google.gson.JsonObject
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiInterface {

    @POST("user/register")
    fun register(
       @Body jsonObject: JsonObject
        ): Call<RegisterResponse>


    @POST("auth/login")
    fun login(
        @Body jsonObject: JsonObject
    ): Call<LoginResponse>


        @GET("user/detail")
        fun getDetails():Call<DetailsResponse>
}
