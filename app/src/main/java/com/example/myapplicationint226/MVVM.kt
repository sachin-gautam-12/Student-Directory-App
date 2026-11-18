package com.example.myapplicationint226

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

// ==================================================
// UI STATE
// ==================================================

sealed interface UserProfileUiState {

    data object Loading : UserProfileUiState

    data class Success(
        val username: String,
        val email: String,
        val isFollowed: Boolean
    ) : UserProfileUiState

    data class Error(
        val message: String
    ) : UserProfileUiState
}

// ==================================================
// VIEWMODEL
// ==================================================

class UserProfileViewModel : ViewModel() {

    private val _uiState =
        MutableStateFlow<UserProfileUiState>(
            UserProfileUiState.Loading
        )

    val uiState: StateFlow<UserProfileUiState> =
        _uiState.asStateFlow()

    init {
        fetchUserProfile()
    }

    fun fetchUserProfile() {

        viewModelScope.launch {

            _uiState.value =
                UserProfileUiState.Loading

            delay(1500L)

            _uiState.value =
                UserProfileUiState.Success(
                    username = "Sachin",
                    email = "sachin@example.com",
                    isFollowed = false
                )
        }
    }

    fun toggleFollowStatus() {

        val currentState = _uiState.value

        if (currentState is UserProfileUiState.Success) {

            _uiState.value =
                currentState.copy(
                    isFollowed = !currentState.isFollowed
                )
        }
    }
}

// ==================================================
// MVVM SCREEN
// ==================================================

@Composable
fun MVVMScreen(
    viewModel: UserProfileViewModel = viewModel()
) {

    val uiState by viewModel.uiState
        .collectAsStateWithLifecycle()

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {

        UserProfileContent(
            uiState = uiState,

            onFollowClick = {
                viewModel.toggleFollowStatus()
            },

            onRetryClick = {
                viewModel.fetchUserProfile()
            }
        )
    }
}

// ==================================================
// UI CONTENT
// ==================================================

@Composable
fun UserProfileContent(
    uiState: UserProfileUiState,
    onFollowClick: () -> Unit,
    onRetryClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),

        verticalArrangement =
            Arrangement.Center,

        horizontalAlignment =
            Alignment.CenterHorizontally
    ) {

        when (uiState) {

            // ------------------------------------------
            // LOADING STATE
            // ------------------------------------------

            is UserProfileUiState.Loading -> {

                CircularProgressIndicator()

                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                Text(
                    text = "Loading Profile...",
                    style =
                        MaterialTheme.typography.bodyMedium
                )
            }

            // ------------------------------------------
            // SUCCESS STATE
            // ------------------------------------------

            is UserProfileUiState.Success -> {

                Card(
                    modifier =
                        Modifier.fillMaxWidth(0.9f)
                ) {

                    Column(
                        modifier =
                            Modifier.padding(20.dp),

                        horizontalAlignment =
                            Alignment.CenterHorizontally
                    ) {

                        Text(
                            text = uiState.username,

                            style =
                                MaterialTheme.typography
                                    .headlineMedium
                        )

                        Spacer(
                            modifier =
                                Modifier.height(4.dp)
                        )

                        Text(
                            text = uiState.email,

                            style =
                                MaterialTheme.typography
                                    .bodyMedium,

                            color =
                                MaterialTheme.colorScheme
                                    .onSurfaceVariant
                        )

                        Spacer(
                            modifier =
                                Modifier.height(20.dp)
                        )

                        Button(
                            onClick = onFollowClick
                        ) {

                            Text(
                                text =
                                    if (uiState.isFollowed)
                                        "Unfollow"
                                    else
                                        "Follow"
                            )
                        }
                    }
                }
            }

            // ------------------------------------------
            // ERROR STATE
            // ------------------------------------------

            is UserProfileUiState.Error -> {

                Text(
                    text = uiState.message,

                    color =
                        MaterialTheme.colorScheme.error,

                    style =
                        MaterialTheme.typography.bodyMedium
                )

                Spacer(
                    modifier =
                        Modifier.height(12.dp)
                )

                OutlinedButton(
                    onClick = onRetryClick
                ) {

                    Text("Retry")
                }
            }
        }
    }
}

// ==================================================
// PREVIEW
// ==================================================

@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "MVVM Screen Preview"
)
@Composable
fun MVVMScreenPreview() {

    MaterialTheme {

        UserProfileContent(

            uiState =
                UserProfileUiState.Success(
                    username = "Sachin",
                    email = "sachin@example.com",
                    isFollowed = false
                ),

            onFollowClick = {},

            onRetryClick = {}
        )
    }
}