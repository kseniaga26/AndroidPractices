package ru.kseniaga.androidpractices.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.kseniaga.androidpractices.datastore.DataStoreManager
import ru.kseniaga.androidpractices.datastore.SettingsData
import ru.kseniaga.androidpractices.utils.LocalUtils.contentStatus
import ru.kseniaga.androidpractices.utils.LocalUtils.types
import kotlinx.coroutines.launch
import ru.kseniaga.androidpractices.components.TitleViewModel
import ru.kseniaga.androidpractices.utils.LocalUtils.genres

@Composable
fun SettingsScreen(viewModel : TitleViewModel) {
    val context = LocalContext.current
    val dataStoreManager = DataStoreManager(context)

    var selectedType by remember { mutableStateOf("Select Content Type") }
    var selectedStatus by remember { mutableStateOf("Select Status") }
    var selectedGenres by remember { mutableStateOf("Select Genres") }

    val coroutine = rememberCoroutineScope()

    LaunchedEffect(key1 = true) {
        dataStoreManager.getSettings().collect { settings ->
            selectedType = settings.type
            selectedStatus = settings.status
            selectedGenres = settings.genres
        }
    }

    Text(text = "Settings Screen", modifier = Modifier.padding(16.dp))
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        shape = RoundedCornerShape(5.dp),
        elevation = 8.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("Фильтр для списка", fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {


                Button(
                    onClick = {
                        coroutine.launch {
                            dataStoreManager.saveSettings(
                                SettingsData(selectedType, selectedStatus, selectedGenres)
                            )
                            viewModel.updateFilters(selectedType, selectedStatus, selectedGenres)
                        }
                    }

                ) {
                    Text("Применить")
                }

                Button(
                    onClick = {
                        coroutine.launch {
                            dataStoreManager.resetSettings()
                        }
                    },
                    modifier = Modifier.padding(start = 16.dp)
                ) {
                    Text("Сброс")
                }
            }


            Text(
                "Тип: ",
                style = MaterialTheme.typography.titleMedium,
                fontSize = 20.sp,
                color = Color.Gray
            )
            StatusCards(types, selectedType) { status ->
                selectedType = status
            }

            Text(
                "Статус:",
                style = MaterialTheme.typography.titleMedium,
                fontSize = 20.sp,
                color = Color.Gray
            )
            StatusCards(contentStatus, selectedStatus) { rating ->
                selectedStatus = rating
            }

            Text(
                "Жанры: ",
                style = MaterialTheme.typography.titleMedium,
                fontSize = 20.sp,
                color = Color.Gray
            )
            StatusCards(genres, selectedGenres) { rating ->
                selectedGenres = rating
            }
        }
    }
}


@Composable
fun StatusCards(items: List<String>, selected: String, onSelect: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxWidth().wrapContentSize()) {
        Card(
            modifier = Modifier
                .clickable { expanded = true },
            shape = RoundedCornerShape(8.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Box(
                modifier = Modifier.padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = selected,
                    style = MaterialTheme.typography.bodyLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            items.forEach { status ->
                DropdownMenuItem(onClick = {
                    onSelect(status)
                    expanded = false
                }, text = {Text(
                    text = status,
                    modifier = Modifier.fillMaxWidth(),
                    fontWeight = if (status == selected) FontWeight.Bold else FontWeight.Normal) })
            }
        }
    }
}