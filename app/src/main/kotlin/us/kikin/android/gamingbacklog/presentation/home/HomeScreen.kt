package us.kikin.android.gamingbacklog.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import proto.ReleaseDate
import us.kikin.android.gamingbacklog.R
import us.kikin.android.gamingbacklog.presentation.common.GameCard
import us.kikin.android.gamingbacklog.presentation.common.PrimaryButton

@Composable
fun HomeScreen(
    pagingItems: LazyPagingItems<ReleaseDate>,
    navigate: (String) -> Unit,
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier,
) {
    if (pagingItems.loadState.refresh is LoadState.Error) {
        LaunchedEffect(key1 = snackbarHostState) {
            snackbarHostState.showSnackbar(
                (pagingItems.loadState.refresh as LoadState.Error).error.message ?: "",
            )
        }
        Box(modifier = Modifier.fillMaxSize()) {
            PrimaryButton(
                text = "Retry",
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .align(Alignment.Center),
            ) {
            }
        }
    }

    Box(modifier = modifier.fillMaxSize()) {
        if (pagingItems.loadState.refresh is LoadState.Loading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else {
            LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 140.dp)) {
                items(
                    count = pagingItems.itemCount,
//                    key = pagingItems.itemKey { it.id },
                ) { index ->
                    val release = pagingItems[index]
                    if (release != null) {
                        val game = release.game
                        GameCard(game = game, isLoading = false, imageId = R.drawable.witcher) {
                            // todo navigate to detail
                        }
                    }
                }
                item {
                    if (pagingItems.loadState.append is LoadState.Loading) {
                        CircularProgressIndicator(modifier = Modifier.padding(16.dp))
                    }
                }
            }
        }
    }
}
