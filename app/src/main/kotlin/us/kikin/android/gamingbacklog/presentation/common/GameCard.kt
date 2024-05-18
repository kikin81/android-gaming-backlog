package us.kikin.android.gamingbacklog.presentation.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import proto.Game
import us.kikin.android.gamingbacklog.ui.theme.AppTheme

@Composable
fun GameCard(
    game: Game,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Row(modifier = modifier.clickable { onClick() }) {
        Text(
            text = game.name,
            style = MaterialTheme.typography.bodyMedium,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Composable
@Preview(showBackground = true)
internal fun GameCardPreview() {
    AppTheme {
        GameCard(
            game = Game.newBuilder().setName("Game Name").build(),
            onClick = {},
        )
    }
}
