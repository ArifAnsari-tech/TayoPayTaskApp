package com.example.taskapp.networks.response

class LoginResponse(
    var status: String,
    val data: Data,
    val message: String,)
{
    data class Data(
    val authToken: String,
    val refreshToken: String,
)
}