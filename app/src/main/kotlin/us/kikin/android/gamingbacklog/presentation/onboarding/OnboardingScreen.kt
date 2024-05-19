package us.kikin.android.gamingbacklog.presentation.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import us.kikin.android.gamingbacklog.presentation.common.GhostButton
import us.kikin.android.gamingbacklog.presentation.common.PageIndicator
import us.kikin.android.gamingbacklog.presentation.common.PrimaryButton
import us.kikin.android.gamingbacklog.presentation.onboarding.components.OnboardingPage
import us.kikin.android.gamingbacklog.ui.theme.AppTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(
    modifier: Modifier = Modifier,
    event: (OnboardingEvent) -> Unit,
) {
    Column(modifier = modifier.fillMaxSize()) {
        val pagerState =
            rememberPagerState(initialPage = 0) {
                pages.size
            }

        val buttonState =
            remember {
                derivedStateOf {
                    when (pagerState.currentPage) {
                        0 -> listOf("", "Next")
                        1 -> listOf("Back", "Next")
                        2 -> listOf("Back", "Next")
                        3 -> listOf("Back", "Get Started")
                        else -> listOf("", "")
                    }
                }
            }

        HorizontalPager(state = pagerState) { index ->
            OnboardingPage(page = pages[index])
        }

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .navigationBarsPadding(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            PageIndicator(
                modifier = Modifier.width(68.dp),
                pageSize = pages.size,
                selectedPage = pagerState.currentPage,
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                val scope = rememberCoroutineScope()
                if (buttonState.value[0].isNotEmpty()) {
                    GhostButton(
                        text = buttonState.value[0],
                        onClick = {
                            scope.launch { pagerState.animateScrollToPage(pagerState.currentPage - 1) }
                        },
                    )
                }
                PrimaryButton(
                    text = buttonState.value[1],
                    onClick = {
                        if (pagerState.currentPage == pages.size - 1) {
                            event(OnboardingEvent.SaveAppEntry)
                        }
                        scope.launch { pagerState.animateScrollToPage(pagerState.currentPage + 1) }
                    },
                )
            }
        }
        Spacer(modifier = Modifier.weight(0.5f))
    }
}

@Composable
@Preview(showBackground = true)
internal fun OnboardingScreenPreview() {
    AppTheme {
        OnboardingScreen(event = {})
    }
}
