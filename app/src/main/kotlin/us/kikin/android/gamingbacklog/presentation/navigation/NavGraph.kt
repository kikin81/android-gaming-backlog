package us.kikin.android.gamingbacklog.presentation.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import us.kikin.android.gamingbacklog.presentation.home.HomeScreen
import us.kikin.android.gamingbacklog.presentation.home.HomeViewModel
import us.kikin.android.gamingbacklog.presentation.onboarding.OnboardingScreen
import us.kikin.android.gamingbacklog.presentation.onboarding.OnboardingViewModel

@SuppressLint("ComposeViewModelInjection", "ComposeModifierMissing")
@Composable
fun NavGraph(startDestination: Route) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {
        composable<Route.OnboardingScreen> {
            val viewModel = hiltViewModel<OnboardingViewModel>()
            OnboardingScreen(event = viewModel::onEvent)
        }
        composable<Route.Home> {
            // TODO: add games screen
            val viewModel = hiltViewModel<HomeViewModel>()
            val snackbarHostState = remember { SnackbarHostState() }
            Scaffold(
                snackbarHost = {
                    SnackbarHost(snackbarHostState)
                },
            ) { paddingValues ->
                HomeScreen(
                    pagingItems = viewModel.games.collectAsLazyPagingItems(),
                    navigate = { navController.navigate(it) },
                    snackbarHostState = snackbarHostState,
                    modifier = Modifier.padding(paddingValues),
                )
            }
        }
    }
}
