package com.example.taskapp.networks.response


class RegisterResponse(
    var status: String,
    val data: Data,
    val message: String,
){

data class Data(
    val id: Long,
)

}