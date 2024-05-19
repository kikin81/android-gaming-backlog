package us.kikin.android.gamingbacklog.presentation.common

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
    @DrawableRes imageId: Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Card(
        modifier = modifier
            .clickable { onClick() }
        .widthIn(max = 246.dp),
        shape = MaterialTheme.shapes.medium,
    ) {
        Box {
            Image(
                painter = painterResource(id = imageId),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop,
            )
            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier.align(alignment = Alignment.TopEnd)
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
            )
            Text(
                text = "May 1st 2024",
                style = MaterialTheme.typography.bodySmall,
            )
            PlatformList(platforms = game.platformsList.toImmutableList())
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
        modifier = modifier.widthIn(max = 200.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        for (platform in platforms) {
            SuggestionChip(onClick = { /*TODO*/ },
                label = {
                    Text(
                        text = platform.name,
                    )
                })
        }
    }
}

@Composable
@Preview(showBackground = true)
internal fun GameCardPreview() {
    AppTheme {
        GameCard(
            game = Game.newBuilder()
                .setName("Witcher 3")
                .setFirstReleaseDate(
                    Timestamp.newBuilder()
                        .setSeconds(1714546800L)
                        .build()
                )
                .addAllPlatforms(
                    listOf(
                        Platform.newBuilder().setName("PC").build(),
                        Platform.newBuilder().setName("PS5").build(),
                        Platform.newBuilder().setName("Switch").build(),
                        Platform.newBuilder().setName("Xbox").build(),
                    )
                )
                .build(),
            imageId = R.drawable.witcher,

            onClick = {},
        )
    }
}
