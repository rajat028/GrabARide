package com.grabaride.domain.usecases

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.grabaride.data.model.InvalidCredentialsException
import com.grabaride.domain.repository.AuthenticationRepository
import com.grabaride.utils.isValidEmail
import com.grabaride.utils.isValidPassword
import javax.inject.Inject

class SignUpUseCase @Inject constructor(private val authenticationRepository: AuthenticationRepository) {
    
    suspend fun onSignUp(email: String, password: String, confirmPassword: String): Task<AuthResult> {
        if (email.isValidEmail().not()) {
            throw InvalidCredentialsException("Invalid Email")
        } else if (password.isValidPassword().not()) {
            throw InvalidCredentialsException("Invalid Password")
        } else if (password != confirmPassword) {
            throw InvalidCredentialsException("Password must be same")
        } else {
           return authenticationRepository.onSignUp(email, password)
        }
    }
}