package ru.alexzdns.movieapp.ui.movie.list

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import ru.alexzdns.movieapp.domain.models.Movie
import ru.alexzdns.movieapp.ui.components.RatingView

@Composable
fun MovieListItem(
    movie: Movie,
    onMovieClick: (Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier
            .background(Color.White.copy(alpha = 0.3f))
            .clickable { onMovieClick.invoke(movie.id) },
    ) {
        AsyncImage(
            model = movie.poster,
            contentDescription = null,
            modifier = Modifier
                .aspectRatio(150.0f / 216.0f)
                .fillMaxWidth()
                .clip(RoundedCornerShape(15.dp)),
        )
        Text(
            text = movie.title,
            Modifier.padding(top = 8.dp, start = 4.dp, end = 4.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        Text(
            text = movie.overview,
            Modifier.padding(top = 10.dp, start = 4.dp, end = 4.dp),
            fontSize = 12.sp,
            maxLines = 5,
            overflow = TextOverflow.Ellipsis,
            softWrap = true,
        )
        Box(
            Modifier
                .padding(top = 10.dp)
                .fillMaxWidth()
        ) {
            RatingView(
                rating = movie.ratings.toInt(),
                Modifier
                    .align(Alignment.CenterStart)
            )
            Box(
                Modifier
                    .align(Alignment.CenterEnd)
                    .size(24.dp)
                    .border(BorderStroke(1.dp, Color.Black), shape = CircleShape),
            ) {
                Text(
                    text = if (movie.isAdult) "18+" else "13+",
                    Modifier.align(Alignment.Center),
                    fontSize = 10.sp,
                )
            }
        }
    }
}