package com.grabaride.data.repository

import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.grabaride.domain.repository.AuthenticationRepository
import com.grabaride.utils.TAG

class FirebaseAuthenticationRepositoryImpl(private val auth: FirebaseAuth) :
    AuthenticationRepository {
    
    override suspend fun onLogin(email: String, password: String): Task<AuthResult> {
        return auth.signInWithEmailAndPassword(email, password)
    }
    
    override suspend fun onSignUp(email: String, password: String): Task<AuthResult> {
        return auth.createUserWithEmailAndPassword(email, password)
    }
    
    override suspend fun onForgotPassword(email: String) {
        TODO("Not yet implemented")
    }
}