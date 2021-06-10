package com.example.tca.models.login


data class Login(
    var status: String?,
    var message: String?,
    var data: LoginData?
)

data class LoginData(
    val user: LoginFields?
)

data class LoginFields(
    val email: String?,
    val name: String?,
    val token: String?
)