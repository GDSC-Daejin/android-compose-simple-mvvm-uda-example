package app.harry.udaexample.ui.screen

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import app.harry.udaexample.ui.screen.home.HomeScreen
import app.harry.udaexample.ui.screen.notification.NotificationScreen
import app.harry.udaexample.ui.screen.profile.ProfileScreen
import kotlinx.coroutines.flow.map

@Composable
fun App() {

    val navController = rememberNavController()

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text("UDA Example")
            })
        },
        bottomBar = {
            AppBottomNavigationBar(navController = navController)
        }
    ) {

        NavHost(navController, AppRoutes.Home.route) {

            composable(AppRoutes.Home.route) { HomeScreen() }

            composable(AppRoutes.Notification.route) { NotificationScreen() }

            composable(AppRoutes.Profile.route) { ProfileScreen() }

        }

    }

}

@Composable
fun NavController.currentRoute() = currentBackStackEntryFlow
    .collectAsState(initial = currentBackStackEntry)
    .value?.destination?.route

@Composable
fun AppBottomNavigationBar(navController: NavController) {

    val currentRoute = navController.currentRoute()

    BottomNavigation {
        BottomNavigationItem(
            icon = {
                Icon(Icons.Rounded.Home, null)
            },
            label = {
                Text("Home")
            },
            selected = currentRoute == AppRoutes.Home.route,
            onClick = {
                navController.navigate(AppRoutes.Home.route) {
                    launchSingleTop = true
                }
            }
        )
        BottomNavigationItem(
            icon = {
                Icon(Icons.Rounded.Notifications, null)
            },
            label = {
                Text("Notification")
            },
            selected = currentRoute == AppRoutes.Notification.route,
            onClick = {
                navController.navigate(AppRoutes.Notification.route) {
                    launchSingleTop = true
                }
            }
        )
        BottomNavigationItem(
            icon = {
                Icon(Icons.Rounded.AccountCircle, null)
            },
            label = {
                Text("Profile")
            },
            selected = currentRoute == AppRoutes.Profile.route,
            onClick = {
                navController.navigate(AppRoutes.Profile.route) {
                    launchSingleTop = true
                }
            }
        )
    }
}

sealed class AppRoutes(val route: String) {

    object Home : AppRoutes("home")

    object Notification : AppRoutes("notification")

    object Profile : AppRoutes("profile")

}