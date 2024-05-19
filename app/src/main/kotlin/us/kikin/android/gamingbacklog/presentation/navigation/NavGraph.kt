package us.kikin.android.gamingbacklog.presentation.navigation

import android.annotation.SuppressLint
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import us.kikin.android.gamingbacklog.presentation.onboarding.OnboardingScreen
import us.kikin.android.gamingbacklog.presentation.onboarding.OnboardingViewModel

@SuppressLint("ComposeViewModelInjection", "ComposeModifierMissing")
@Composable
fun NavGraph(startDestination: String) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {
        navigation(
            route = Route.AppStartNavigation.route,
            startDestination = Route.OnboardingScreen.route,
        ) {
            composable(route = Route.OnboardingScreen.route) {
                val viewModel = hiltViewModel<OnboardingViewModel>()
                OnboardingScreen(event = viewModel::onEvent)
            }
        }
        navigation(
            route = Route.GamesNavigation.route,
            startDestination = Route.GamesNavigatorScreen.route,
        ) {
            composable(route = Route.GamesNavigatorScreen.route) {
                // TODO: add games screen
                Text(text = "Games Navigator Screen")
            }
        }
    }
}
