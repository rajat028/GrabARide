package com.grabaride.domain.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult

interface AuthenticationRepository {
    suspend fun onLogin(email: String, password: String): Task<AuthResult>
    suspend fun onSignUp(email: String, password: String): Task<AuthResult>
    suspend fun onForgotPassword(email: String)
}