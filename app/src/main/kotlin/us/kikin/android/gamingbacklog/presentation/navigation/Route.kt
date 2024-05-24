package us.kikin.android.gamingbacklog.presentation.navigation

import kotlinx.serialization.Serializable

sealed class Route {
    @Serializable
    object OnboardingScreen : Route()

    @Serializable
    object Home : Route()
}
