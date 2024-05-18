package us.kikin.android.gamingbacklog.presentation.onboarding

import androidx.annotation.DrawableRes
import us.kikin.android.gamingbacklog.R

data class Page(
    val title: String,
    val description: String,
    @DrawableRes val imageRes: Int,
)

val pages =
    listOf(
        Page(
            title = "Welcome to Gaming Backlog",
            description = "Keep track of your video game backlog",
            imageRes = R.drawable.onboarding1,
        ),
        Page(
            title = "Add Games",
            description = "Add games to your backlog",
            imageRes = R.drawable.onboarding2,
        ),
        Page(
            title = "Track Progress",
            description = "Track your progress and completion status",
            imageRes = R.drawable.onboarding3,
        ),
        Page(
            title = "Get Started",
            description = "Get started by adding your first game",
            imageRes = R.drawable.onboarding4,
        ),
    )
