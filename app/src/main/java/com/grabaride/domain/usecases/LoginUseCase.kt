package com.grabaride.domain.usecases

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.grabaride.domain.repository.AuthenticationRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val authenticationRepository: AuthenticationRepository){
    
    suspend fun loginUser(email: String, password: String): Task<AuthResult> {
        return authenticationRepository.onLogin(email, password)
    }
}