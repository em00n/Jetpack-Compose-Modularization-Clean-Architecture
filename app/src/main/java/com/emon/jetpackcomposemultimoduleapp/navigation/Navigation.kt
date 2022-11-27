package com.emon.jetpackcomposemultimoduleapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.emon.profile.ProfileScreen
import com.emon.repositories.RepositoriesScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavRoutes.Repositories.route
    ) {

        composable(NavRoutes.Repositories.route) {
            RepositoriesScreen(onNavigateToProfile = { navController.navigate(NavRoutes.Profile.route+"/$it") })
        }
        composable(NavRoutes.Profile.route+ "/{userName}") {backStackEntry ->
            val userName = backStackEntry.arguments?.getString("userName")?:""
            ProfileScreen(userName = userName, navController = navController)
        }
    }
}

sealed class NavRoutes(val route: String) {
    object Repositories : NavRoutes("repositories")
    object Profile : NavRoutes("profile")
}