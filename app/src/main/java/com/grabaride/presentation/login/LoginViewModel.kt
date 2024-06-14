package com.grabaride.presentation.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grabaride.data.model.InvalidCredentialsException
import com.grabaride.domain.usecases.LoginUseCase
import com.grabaride.presentation.states.LoginCredentialsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val useCase: LoginUseCase,
) : ViewModel() {
    
    private val _credentialsState = mutableStateOf(LoginCredentialsState())
    val credentialsState: State<LoginCredentialsState> = _credentialsState
    
    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()
    
    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.EnteredEmail -> {
                _credentialsState.value = _credentialsState.value.copy(email = event.value)
            }
            
            is LoginEvent.EnteredPassword -> {
                _credentialsState.value = _credentialsState.value.copy(password = event.value)
            }
            
            LoginEvent.OnLogin -> {
                loginUser()
            }
        }
    }
    
    private fun loginUser() {
        viewModelScope.launch {
            try {
                useCase.loginUser(
                    _credentialsState.value.email,
                    _credentialsState.value.password
                ).addOnCompleteListener { task ->
                    viewModelScope.launch {
                        if (task.isSuccessful) {
                            _eventFlow.emit(UiEvent.LoginUser)
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
        object LoginUser : UiEvent()
    }
}