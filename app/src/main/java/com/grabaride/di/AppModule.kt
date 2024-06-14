package com.grabaride.di

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.grabaride.data.repository.FirebaseAuthenticationRepositoryImpl
import com.grabaride.domain.repository.AuthenticationRepository
import com.grabaride.domain.usecases.LoginUseCase
import com.grabaride.domain.usecases.SignUpUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    
    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth {
        return Firebase.auth
    }
    @Provides
    @Singleton
    fun providesAuthenticationRepository(auth : FirebaseAuth): AuthenticationRepository {
        return FirebaseAuthenticationRepositoryImpl(auth)
    }
    
    @Provides
    @Singleton
    fun providesSignUpUseCase(repository: AuthenticationRepository): SignUpUseCase {
        return SignUpUseCase(repository)
    }
    
    @Provides
    @Singleton
    fun providesLogInUseCase(repository: AuthenticationRepository): LoginUseCase {
        return LoginUseCase(repository)
    }
    
}