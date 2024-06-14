package com.grabaride.presentation.signup

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grabaride.data.model.InvalidCredentialsException
import com.grabaride.domain.usecases.SignUpUseCase
import com.grabaride.presentation.states.SignUpCredentialsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val useCase: SignUpUseCase) : ViewModel() {
    
    private val _credentialsState = mutableStateOf(SignUpCredentialsState())
    val credentialsState: State<SignUpCredentialsState> = _credentialsState
    
    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()
    
    fun onEvent(event: SignupEvent) {
        when (event) {
            is SignupEvent.EnteredEmail -> {
                _credentialsState.value = _credentialsState.value.copy(email = event.value)
            }
            
            is SignupEvent.EnteredPassword -> {
                _credentialsState.value = _credentialsState.value.copy(password = event.value)
            }
            
            is SignupEvent.EnteredConfirmPassword -> {
                _credentialsState.value =
                    _credentialsState.value.copy(confirmPassword = event.value)
            }
            
            is SignupEvent.OnSignUp -> {
                registerUser()
            }
        }
    }
    
    private fun registerUser() {
        viewModelScope.launch {
            try {
                useCase.onSignUp(
                    email = _credentialsState.value.email,
                    password = _credentialsState.value.password,
                    confirmPassword = _credentialsState.value.confirmPassword
                ).addOnCompleteListener { task ->
                    // Todo : Add dispatchers from DI
                    viewModelScope.launch(Dispatchers.Main) {
                        if (task.isSuccessful) {
                            _eventFlow.emit(UiEvent.RegisterUser)
                        } else {
                            task.exception?.message?.let { message ->
                                _eventFlow.emit(UiEvent.ShowToast(message))
                            }
                        }
                    }
                }
            } catch (exception: InvalidCredentialsException) {
                _eventFlow.emit(UiEvent.ShowToast(exception.exceptionMessage))
            }
        }
    }
    
    sealed class UiEvent {
        data class ShowToast(val message: String?) : UiEvent()
        object RegisterUser : UiEvent()
    }
}