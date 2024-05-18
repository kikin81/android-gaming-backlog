package us.kikin.android.gamingbacklog.presentation.navigation

sealed class Route(
    val route: String,
) {
    object OnboardingScreen : Route("onboardingScreen")

    object HomeScreen : Route("homeScreen")

    object SearchScreen : Route("searchScreen")

    object BookmarkScreen : Route("bookmarkScreen")

    object DetailScreen : Route("detailScreen")

    object AppStartNavigation : Route("appStartNavigation")

    object GamesNavigation : Route("gamesNavigation")

    object GamesNavigatorScreen : Route("gamesNavigator")
}
