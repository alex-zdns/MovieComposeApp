package ru.alexzdns.movieapp.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun PagingErrorComponent(
    refreshAction: () -> Unit = {},
) {
    Surface(Modifier.fillMaxWidth().height(200.dp)) {
        Box(contentAlignment = Alignment.Center) {
            Text(text = "Упс, ошибка")

            Button(
                onClick = { refreshAction.invoke() },
                Modifier
                    .align(Alignment.BottomCenter)
                    .padding(24.dp)
            ) {
                Text(text = "Обновить")
                Icon(
                    imageVector = Icons.Filled.Refresh,
                    contentDescription = "",
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PagingErrorComponentPreview() {
    PagingErrorComponent()
}