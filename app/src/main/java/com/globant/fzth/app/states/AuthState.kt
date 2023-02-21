package com.globant.fzth.app.states

data class AuthState(
    var errorMessage: Boolean = false,
    var successMessage: Boolean = false,
    var email: String = "",
    var password: String = ""
)