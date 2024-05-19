package us.kikin.android.gamingbacklog.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import proto.Game
import us.kikin.android.gamingbacklog.R
import us.kikin.android.gamingbacklog.ui.theme.AppTheme

@Composable
fun GamesList(
    games: LazyPagingItems<Game>,
    modifier: Modifier = Modifier,
    onClick: (Game) -> Unit,
) {
    val handlePagingResult = handlePagingResult(games = games)
    if (handlePagingResult) {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(count = games.itemCount) {
                games[it]?.let { game ->
                    GameCard(
                        game = game,
                        imageId = R.drawable.witcher,
                        onClick = { onClick(game) },
                    )
                }
            }
        }
    }
}

@Composable
fun handlePagingResult(games: LazyPagingItems<Game>): Boolean {
    val loadState = games.loadState
    val error =
        when {
            loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
            loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
            loadState.append is LoadState.Error -> loadState.append as LoadState.Error
            else -> null
        }

    return when {
        loadState.refresh is LoadState.Loading -> {
            // handle shimmer
            false
        }

        error != null -> {
            false
        }

        else -> {
            true
        }
    }
}

@Composable
@Preview(showBackground = true)
internal fun GamesListPreview() {
    AppTheme {
//        GamesList()
    }
}
