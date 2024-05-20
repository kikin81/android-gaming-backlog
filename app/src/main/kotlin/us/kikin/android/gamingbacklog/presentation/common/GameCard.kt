package us.kikin.android.gamingbacklog.presentation.common

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.material.shimmer
import com.google.protobuf.Timestamp
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import proto.Game
import proto.Platform
import us.kikin.android.gamingbacklog.R
import us.kikin.android.gamingbacklog.ui.theme.AppTheme

@Composable
fun GameCard(
    game: Game,
    isLoading: Boolean,
    @DrawableRes imageId: Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Card(
        modifier =
            modifier
                .clickable { onClick() }
                .widthIn(max = 260.dp),
    ) {
        Box(
            modifier =
                Modifier.placeholder(
                    isLoading,
                    highlight = PlaceholderHighlight.shimmer(),
                ),
        ) {
            if (game.cover != null && !game.cover.imageId.isNullOrBlank()) {
                // images.igdb.com/igdb/image/upload/t_thumb/co5qi9.jpg
                val url = "https://images.igdb.com/igdb/image/upload/t_cover_big/${game.cover.imageId}.jpg"
                AsyncImage(
                    model = url,
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.Crop,
                )
            } else {
                Image(
                    painter = painterResource(id = imageId),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.Crop,
                )
            }
            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier.align(alignment = Alignment.TopEnd),
            ) {
                Icon(imageVector = Icons.Outlined.FavoriteBorder, contentDescription = "Favorite")
            }
        }
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Text(
                text = game.name,
                style = MaterialTheme.typography.headlineSmall,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .placeholder(
                            isLoading,
                            highlight = PlaceholderHighlight.shimmer(),
                        ),
            )
            if (game.releaseDatesList.isNotEmpty()) {
//                val dateFormat = DateFormat.getDateInstance(DateFormat.SHORT, Locale.getDefault())
//                val release = Date(game.firstReleaseDate.seconds * 1000)
//                val dateString = dateFormat.format(release)
                Text(
                    text = game.releaseDatesList.first().human,
                    style = MaterialTheme.typography.bodySmall,
                    modifier =
                        Modifier.placeholder(
                            isLoading,
                            highlight = PlaceholderHighlight.shimmer(),
                        ),
                )
            }
            Text(
                text = "May 1st 2024",
                style = MaterialTheme.typography.bodySmall,
                modifier =
                    Modifier.placeholder(
                        isLoading,
                        highlight = PlaceholderHighlight.shimmer(),
                    ),
            )
            PlatformList(
                platforms = game.platformsList.toImmutableList(),
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .placeholder(
                            isLoading,
                            highlight = PlaceholderHighlight.shimmer(),
                        ),
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PlatformList(
    platforms: ImmutableList<Platform>,
    modifier: Modifier = Modifier,
) {
    FlowRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        for (platform in platforms) {
            SuggestionChip(
                onClick = { /*TODO*/ },
                label = {
                    Text(
                        text = platform.name,
                        style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.SemiBold),
                    )
                },
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
internal fun PlatformListPreview() {
    AppTheme {
        PlatformList(
            platforms =
                listOf(
                    Platform.newBuilder().setName("PC").build(),
                    Platform.newBuilder().setName("PS5").build(),
                    Platform.newBuilder().setName("Switch").build(),
                    Platform.newBuilder().setName("Xbox").build(),
                ).toImmutableList(),
            modifier = Modifier.widthIn(max = 200.dp),
        )
    }
}

@Composable
@Preview(showBackground = true)
internal fun GameCardPreview() {
    val gameBuilder =
        Game.newBuilder()
            .setName("Witcher 3")
            .setFirstReleaseDate(
                Timestamp.newBuilder()
                    .setSeconds(1714546800L)
                    .build(),
            )
            .addAllPlatforms(
                listOf(
                    Platform.newBuilder().setName("PC").build(),
                    Platform.newBuilder().setName("PS5").build(),
                    Platform.newBuilder().setName("Switch").build(),
                    Platform.newBuilder().setName("Xbox").build(),
                ),
            )

    AppTheme {
        Scaffold(
            containerColor = MaterialTheme.colorScheme.surface,
        ) { paddingValues ->
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 160.dp),
                modifier =
                    Modifier
                        .background(color = MaterialTheme.colorScheme.surface)
                        .padding(paddingValues),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                item {
                    GameCard(
                        game = gameBuilder.build(),
                        isLoading = false,
                        imageId = R.drawable.witcher,
                        onClick = {},
                    )
                }
                item {
                    GameCard(
                        game = gameBuilder.build(),
                        isLoading = true,
                        imageId = R.drawable.witcher,
                        onClick = {},
                    )
                }
                item {
                    GameCard(
                        game = gameBuilder.setName("Fallout 3: New Vegas DLC Expansion").build(),
                        isLoading = false,
                        imageId = R.drawable.witcher,
                        onClick = {},
                    )
                }
                item {
                    GameCard(
                        game = gameBuilder.setName("Tetris").build(),
                        isLoading = false,
                        imageId = R.drawable.witcher,
                        onClick = {},
                    )
                }
                item {
                    GameCard(
                        game = gameBuilder.setName("Isaac").build(),
                        isLoading = false,
                        imageId = R.drawable.witcher,
                        onClick = {},
                    )
                }
                item {
                    GameCard(
                        game = gameBuilder.setName("The Legend of Zelda").build(),
                        isLoading = false,
                        imageId = R.drawable.witcher,
                        onClick = {},
                    )
                }
            }
        }
    }
}
