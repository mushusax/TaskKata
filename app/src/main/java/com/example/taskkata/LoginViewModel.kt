package com.example.taskkata

import androidx.lifecycle.Transformations.map
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map

class LoginViewModel : ViewModel() {

    enum class AuthenticationState {
        AUTHENTICATED, UNAUTHENTICATED
    }


    private val _authenticationState = FirebaseUserLiveData().map { firebaseUser ->
        if(firebaseUser == null) AuthenticationState.UNAUTHENTICATED
        else AuthenticationState.AUTHENTICATED
    }
    val authenticationState
        get() = _authenticationState

}