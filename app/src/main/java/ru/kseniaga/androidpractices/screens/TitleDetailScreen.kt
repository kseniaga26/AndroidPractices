package ru.kseniaga.androidpractices.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import coil.compose.rememberImagePainter
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp

import ru.kseniaga.androidpractices.presentation.model.TitleUiModel
import ru.kseniaga.androidpractices.ui.theme.Pink40

@Composable
fun TitleDetailScreen(title: TitleUiModel)
{
    Column(
        modifier = Modifier.padding(40.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(56.dp))
        Image(
            painter = rememberImagePainter(title.posterUrl),
            contentDescription = null,
            modifier = Modifier.size(300.dp).align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = title.name,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontFamily = FontFamily.Serif,
            fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,

            )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color.Gray,fontWeight = FontWeight.Bold)) {
                    append("Рейтинг: ")
                }
                withStyle(style = SpanStyle(color = Pink40)) {
                    append("${title.rating}")
                }
            },
            style = MaterialTheme.typography.bodyLarge,
            fontFamily = FontFamily.Serif
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color.Gray,fontWeight = FontWeight.Bold)) {
                    append("Год производства: ")
                }
                withStyle(style = SpanStyle()) {
                    append(title.year)
                }
            },
            style = MaterialTheme.typography.bodyLarge,
            fontFamily = FontFamily.Serif
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color.Gray,fontWeight = FontWeight.Bold)) {
                    append("Жанры: ")
                }
                withStyle(style = SpanStyle()) {
                    append(title.genre)
                }
            },
            style = MaterialTheme.typography.bodyLarge,
            fontFamily = FontFamily.Serif
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color.Gray,fontWeight = FontWeight.Bold)) {
                    append("Страна: ")
                }
                withStyle(style = SpanStyle()) {
                    append(title.countries)
                }
            },
            style = MaterialTheme.typography.bodyLarge,
            fontFamily = FontFamily.Serif
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color.Gray,fontWeight = FontWeight.Bold)) {
                    append("Время: ")
                }
                withStyle(style = SpanStyle()) {
                    append(title.movieLength)
                }
            },
            style = MaterialTheme.typography.bodyLarge,
            fontFamily = FontFamily.Serif
        )
        Spacer(modifier = Modifier.height(8.dp))

        if (!title.director.isNullOrEmpty()){
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = Color.Gray, fontWeight = FontWeight.Bold)) {
                        append("Режиссер: ")
                    }
                    withStyle(style = SpanStyle()) {
                        append(title.director)
                    }
                },
                style = MaterialTheme.typography.bodyLarge,
                fontFamily = FontFamily.Serif
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Spacer(modifier = Modifier.height(8.dp))
        if (title.starring.isNotEmpty()) {
            Text(text = "В ролях: ", color = Color.Gray, style = MaterialTheme.typography.bodyLarge, fontFamily = FontFamily.Serif,fontWeight = FontWeight.Bold)
            if (title.starring.isNotEmpty()) {
                LazyRow {
                    items(title.starring) { actors ->
                        Text(
                            actors + ",", style = MaterialTheme.typography.bodyLarge,
                            fontFamily = FontFamily.Serif,
                            modifier = Modifier.padding(3.dp)
                                .padding(vertical = 3.dp, horizontal = 0.dp)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = title.description, style = MaterialTheme.typography.bodyLarge, fontFamily = FontFamily.Serif,)
    }
}




