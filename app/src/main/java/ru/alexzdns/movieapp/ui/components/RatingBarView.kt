package ru.alexzdns.movieapp.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import ru.alexzdns.movieapp.R

@Composable
internal fun RatingView(
    rating: Int,
    modifier: Modifier = Modifier,
    maxRating: Int = 5,
) {
    Row(modifier) {
        repeat(maxRating) { starIndex ->
            val iconRes = if (starIndex < rating) R.drawable.ic_star_fill else R.drawable.ic_star
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = null
            )
        }
    }
}

@Preview
@Composable
private fun RatingViewPreview() {
    RatingView(rating = 3)
}
