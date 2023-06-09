package ru.alexzdns.movieapp.ui.movie.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import ru.alexzdns.movieapp.domain.models.Movie

@Composable
fun MovieDetailsView(movie: Movie, modifier: Modifier = Modifier) {
    Column(modifier) {
        AsyncImage(
            model = movie.backdrop,
            contentDescription = null,
            modifier = Modifier
                .aspectRatio(360.0f / 290.0f)
                .fillMaxWidth()
                .clip(RoundedCornerShape(15.dp)),
        )
        Text(
            text = movie.title,
            Modifier.padding(top = 20.dp, start = 20.dp, end = 20.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
        )
        Text(
            text = movie.overview,
            Modifier.padding(top = 20.dp, start = 20.dp, end = 20.dp),
            fontSize = 14.sp,
            softWrap = true,
        )
    }
}