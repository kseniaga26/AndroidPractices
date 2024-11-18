package ru.kseniaga.androidpractices.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen() {

    Spacer(modifier = Modifier.height(56.dp))
    Text(text = "Home Screen", modifier = Modifier.padding(16.dp))
}

