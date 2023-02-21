package com.example.fzthtv.events

sealed class AuthEvents(){

    data class CreateUser(val email: String, val password: String) : AuthEvents()
    data class LogInUser(val email: String, val password: String) : AuthEvents()
    data class OnError(val message: Boolean) : AuthEvents()
}
