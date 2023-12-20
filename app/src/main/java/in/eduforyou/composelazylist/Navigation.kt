package `in`.eduforyou.composelazylist

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import `in`.eduforyou.composelazylist.explorer.ExplorerScreen
import `in`.eduforyou.composelazylist.welcome.WelcomeScreen

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    val coroutineScope = rememberCoroutineScope()
    NavHost(
        navController = navController,
        startDestination = "welcome",
    ) {
        composable(route = "welcome") {
            WelcomeScreen {
                coroutineScope.launch {
                    // delay for making navigation a real life example
                    delay(1000)
                    navController.navigate("explorer") {
                        popUpTo(navController.findDestination("welcome")?.id ?: return@navigate) {
                            inclusive
                            saveState = false
                        }
                        restoreState = false
                    }
                }
            }
        }
        composable("explorer") {
            ExplorerScreen()
        }
    }
}
