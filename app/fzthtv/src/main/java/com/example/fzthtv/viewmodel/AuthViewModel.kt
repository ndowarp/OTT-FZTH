package com.example.fzthtv.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.fzthtv.events.AuthEvents
import com.example.fzthtv.states.AuthState
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(

) : ViewModel() {
    private val _state = mutableStateOf(AuthState())
    val state: State<AuthState> = _state

    fun onEvent(event: AuthEvents) {
        when (event) {
            is AuthEvents.OnError -> _state.value = state.value.copy(
                errorMessage = event.message
            )
            is AuthEvents.LogInUser -> {
                _state.value = state.value.copy(email = event.email, password = event.password)
                logIn(state.value.email, state.value.password)
                Log.d("CREATION", state.value.email + state.value.password)
            }
            is AuthEvents.CreateUser -> {
                _state.value = state.value.copy(email = event.email, password = event.password)
                   createUser(state.value.email, state.value.password)
                Log.d("CREATION", state.value.email + state.value.password)
            }
        }
    }

    private fun createUser(email: String, password: String) {
        Log.d("CREATION", state.value.email + "  2   " + state.value.password)
        if (!email.isNullOrEmpty() && !password.isNullOrEmpty()) {
            FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        _state.value = state.value.copy(
                            successMessage = true
                        )
                    } else {
                        _state.value = state.value.copy(
                            errorMessage = true
                        )
                    }
                }
        }
    }

    private fun logIn(email: String, password: String) {
        if (!email.isNullOrEmpty() && !password.isNullOrEmpty()) {
            FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        _state.value = state.value.copy(
                            successMessage = true
                        )
                    } else {
                        _state.value = state.value.copy(
                            errorMessage = true
                        )
                    }
                }
        }
    }
}