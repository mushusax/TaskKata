package com.example.taskkata.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.example.taskkata.FirebaseUserLiveData

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