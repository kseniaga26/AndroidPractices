package com.example.consecutivep.state

import androidx.compose.runtime.Stable
import ru.kseniaga.androidpractices.presentation.model.TitleUiModel

@Stable
interface ListState {
    val searchName: String
    val items: List<TitleUiModel>
    val error: String?
    var loading: Boolean
}