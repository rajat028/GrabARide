package com.grabaride

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.grabaride.presentation.addride.AddRideScreen
import com.grabaride.presentation.home.HomeScreen
import com.grabaride.presentation.login.LoginScreen
import com.grabaride.presentation.signup.SignUpScreen
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    
    @Inject
    lateinit var auth: FirebaseAuth
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppController(auth)
        }
    }
}

@Composable
fun AppController(auth: FirebaseAuth) {
    val isUserLoggedIn = auth.currentUser != null
    var startDestination by remember { mutableStateOf(NavigationItem.Signup.route) }
    if (isUserLoggedIn) {
        startDestination = NavigationItem.Home.route
    }
    
    fun navigateToLogin(navController: NavHostController) {
        navController.navigate(NavigationItem.Login.route)
    }
    
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination) {
        composable(NavigationItem.Signup.route) {
            SignUpScreen(onLogInClick = {
                navigateToLogin(navController)
            }, onSignUpSuccessful = {
                navController.popBackStack()
                navController.navigate(NavigationItem.Login.route)
            })
        }
        composable(NavigationItem.Login.route) {
            LoginScreen(onLoginSuccessful = {
                navController.navigate(NavigationItem.Home.route) {
                    popUpTo(navController.graph.id) {
                        inclusive = true
                    }
                }
                startDestination = NavigationItem.Home.route
            })
        }
        
        composable(NavigationItem.Home.route) {
            HomeScreen(addRide = {
                navController.navigate(NavigationItem.AddRide.route)
            })
        }
        
        composable(NavigationItem.AddRide.route) {
            AddRideScreen()
        }
    }
}