package ru.kseniaga.androidpractices.components


import android.net.Uri
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.consecutivep.presentation.profile.model.state.ProfileState
import kotlinx.coroutines.launch
import ru.kseniaga.androidpractices.domain.IProfileRepository

class ProfileViewModel(
    private val repository: IProfileRepository
): ViewModel() {

    private val mutableState = MutableProfileState()
    val viewState = mutableState as ProfileState

    init {
        viewModelScope.launch {
            repository.observeProfile().collect {
                mutableState.name = it.name
                mutableState.photoUri = Uri.parse(it.photoUri)
                mutableState.url = it.url

            }
        }
    }

    private class MutableProfileState() : ProfileState {
        override var name by mutableStateOf("")
        override var photoUri by mutableStateOf(Uri.EMPTY)
        override var url by mutableStateOf("")
    }
}