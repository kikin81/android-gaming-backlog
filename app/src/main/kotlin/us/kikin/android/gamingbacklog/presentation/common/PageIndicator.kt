package us.kikin.android.gamingbacklog.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import us.kikin.android.gamingbacklog.ui.theme.AppTheme

@Composable
fun PageIndicator(
    pageSize: Int,
    selectedPage: Int,
    modifier: Modifier = Modifier,
    selectedColor: Color = MaterialTheme.colorScheme.primary,
    unselectedColor: Color = MaterialTheme.colorScheme.secondary,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        repeat(pageSize) { page ->
            Box(
                modifier =
                    Modifier
                        .size(14.dp)
                        .clip(CircleShape)
                        .background(
                            if (page == selectedPage) selectedColor else unselectedColor,
                        ),
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
internal fun PageIndicatorPreview() {
    AppTheme {
        PageIndicator(pageSize = 4, selectedPage = 2)
    }
}
