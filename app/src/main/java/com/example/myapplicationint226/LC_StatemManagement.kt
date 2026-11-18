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
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel

import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch


// ==================================================
// UI STATE
// ==================================================

sealed interface LocationTrackerUiState {

    // ----------------------------------------------
    // INACTIVE STATE
    // ----------------------------------------------

    data object Inactive : LocationTrackerUiState


    // ----------------------------------------------
    // ACTIVE STATE
    // ----------------------------------------------

    data class Active(
        val latitude: Double,
        val longitude: Double,
        val updatesCount: Int,
        val observerStatus: String
    ) : LocationTrackerUiState
}


// ==================================================
// LIFECYCLE SENSOR ENGINE
// ==================================================

class LocationSensorEngine(
    private val onStatusChanged: (String) -> Unit
) : DefaultLifecycleObserver {


    // ----------------------------------------------
    // ON START
    // ----------------------------------------------

    override fun onStart(owner: LifecycleOwner) {

        super.onStart(owner)

        onStatusChanged(
            "GPS Hardware Engine: CONNECTED & STREAMING"
        )
    }


    // ----------------------------------------------
    // ON STOP
    // ----------------------------------------------

    override fun onStop(owner: LifecycleOwner) {

        super.onStop(owner)

        onStatusChanged(
            "GPS Hardware Engine: DISCONNECTED (Resource Saved)"
        )
    }
}


// ==================================================
// VIEW MODEL
// ==================================================

class LocationTrackerViewModel : ViewModel() {


    // ----------------------------------------------
    // UI STATE
    // ----------------------------------------------

    private val _uiState =
        MutableStateFlow<LocationTrackerUiState>(
            LocationTrackerUiState.Inactive
        )

    val uiState: StateFlow<LocationTrackerUiState> =
        _uiState.asStateFlow()


    // ----------------------------------------------
    // TRACKING JOB
    // ----------------------------------------------

    private var trackingJob: Job? = null


    // ----------------------------------------------
    // UPDATE COUNTER
    // ----------------------------------------------

    private var updateCounter = 0


    // ----------------------------------------------
    // LIFECYCLE ENGINE STATUS
    // ----------------------------------------------

    private var currentEngineStatus =
        "Uninitialized"


    // ----------------------------------------------
    // LIFECYCLE-AWARE SENSOR ENGINE
    // ----------------------------------------------

    val sensorEngine = LocationSensorEngine { status ->

        currentEngineStatus = status

        updateStatusInCurrentState()
    }


    // ==================================================
    // START TRACKING
    // ==================================================

    fun startTracking() {

        // Prevent multiple tracking jobs
        if (trackingJob?.isActive == true) {
            return
        }


        // Reset counter
        updateCounter = 0


        trackingJob = viewModelScope.launch {

            // ------------------------------------------
            // INITIAL SIMULATED COORDINATES
            // ------------------------------------------

            var latitude = 32.7266
            var longitude = 74.8570


            // ------------------------------------------
            // CONTINUOUS TRACKING
            // ------------------------------------------

            while (isActive) {

                updateCounter++


                // Simulated GPS movement
                latitude += 0.0001
                longitude += 0.0001


                // Update UI state
                _uiState.value =
                    LocationTrackerUiState.Active(

                        latitude = latitude,

                        longitude = longitude,

                        updatesCount = updateCounter,

                        observerStatus =
                            currentEngineStatus
                    )


                // Update every 1 second
                delay(1000L)
            }
        }
    }


    // ==================================================
    // STOP TRACKING
    // ==================================================

    fun stopTracking() {

        trackingJob?.cancel()

        trackingJob = null


        _uiState.value =
            LocationTrackerUiState.Inactive
    }


    // ==================================================
    // UPDATE LIFECYCLE STATUS
    // ==================================================

    private fun updateStatusInCurrentState() {

        val currentState =
            _uiState.value


        if (currentState is LocationTrackerUiState.Active) {

            _uiState.value =
                currentState.copy(
                    observerStatus =
                        currentEngineStatus
                )
        }
    }


    // ==================================================
    // VIEWMODEL CLEANUP
    // ==================================================

    override fun onCleared() {

        trackingJob?.cancel()

        trackingJob = null

        super.onCleared()
    }
}


// ==================================================
// COMPOSE SCREEN
// ==================================================

@Composable
fun LifecycleStateManagementScreen(
    viewModel: LocationTrackerViewModel = viewModel()
) {

    // ----------------------------------------------
    // COLLECT STATE WITH LIFECYCLE
    // ----------------------------------------------

    val state by
    viewModel.uiState.collectAsStateWithLifecycle()


    Column(

        modifier =
            Modifier
                .fillMaxSize()
                .padding(20.dp),

        verticalArrangement =
            Arrangement.Center,

        horizontalAlignment =
            Alignment.CenterHorizontally

    ) {


        // ------------------------------------------
        // TITLE
        // ------------------------------------------

        Text(
            text =
                "Lifecycle-Aware Location Tracker",

            style =
                MaterialTheme
                    .typography
                    .headlineSmall
        )


        Spacer(
            modifier =
                Modifier.height(24.dp)
        )


        // ------------------------------------------
        // STATE DISPLAY
        // ------------------------------------------

        when (val currentState = state) {


            // ======================================
            // INACTIVE STATE
            // ======================================

            is LocationTrackerUiState.Inactive -> {

                Card(

                    modifier =
                        Modifier.fillMaxWidth(),

                    colors =
                        CardDefaults.cardColors(
                            containerColor =
                                MaterialTheme
                                    .colorScheme
                                    .surfaceVariant
                        )

                ) {

                    Column(

                        modifier =
                            Modifier.padding(24.dp),

                        horizontalAlignment =
                            Alignment.CenterHorizontally

                    ) {

                        Text(
                            text =
                                "Tracking is currently disabled."
                        )


                        Spacer(
                            modifier =
                                Modifier.height(16.dp)
                        )


                        Button(

                            onClick = {
                                viewModel.startTracking()
                            }

                        ) {

                            Text(
                                text =
                                    "Start Tracking"
                            )
                        }
                    }
                }
            }


            // ======================================
            // ACTIVE STATE
            // ======================================

            is LocationTrackerUiState.Active -> {

                Card(

                    modifier =
                        Modifier.fillMaxWidth(),

                    colors =
                        CardDefaults.cardColors(
                            containerColor =
                                MaterialTheme
                                    .colorScheme
                                    .primaryContainer
                        )

                ) {

                    Column(

                        modifier =
                            Modifier.padding(24.dp),

                        horizontalAlignment =
                            Alignment.Start

                    ) {


                        // --------------------------------
                        // STATUS TITLE
                        // --------------------------------

                        Text(
                            text =
                                "Live Telemetry Active",

                            style =
                                MaterialTheme
                                    .typography
                                    .titleMedium
                        )


                        Spacer(
                            modifier =
                                Modifier.height(12.dp)
                        )


                        // --------------------------------
                        // LATITUDE
                        // --------------------------------

                        Text(
                            text =
                                "Latitude: ${currentState.latitude}"
                        )


                        // --------------------------------
                        // LONGITUDE
                        // --------------------------------

                        Text(
                            text =
                                "Longitude: ${currentState.longitude}"
                        )


                        // --------------------------------
                        // UPDATE COUNT
                        // --------------------------------

                        Text(
                            text =
                                "Total Updates Received: " +
                                        "${currentState.updatesCount}"
                        )


                        Spacer(
                            modifier =
                                Modifier.height(12.dp)
                        )


                        // --------------------------------
                        // LIFECYCLE STATUS
                        // --------------------------------

                        Text(
                            text =
                                currentState.observerStatus,

                            style =
                                MaterialTheme
                                    .typography
                                    .labelMedium
                        )


                        Spacer(
                            modifier =
                                Modifier.height(16.dp)
                        )


                        // --------------------------------
                        // STOP BUTTON
                        // --------------------------------

                        Button(

                            onClick = {
                                viewModel.stopTracking()
                            },

                            modifier =
                                Modifier.align(
                                    Alignment.CenterHorizontally
                                )

                        ) {

                            Text(
                                text =
                                    "Stop Tracking"
                            )
                        }
                    }
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
    showSystemUi = true
)
@Composable
fun LifecycleStateManagementScreenPreview() {

    MaterialTheme {

        Column(

            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(20.dp),

            verticalArrangement =
                Arrangement.Center,

            horizontalAlignment =
                Alignment.CenterHorizontally

        ) {


            // ------------------------------------------
            // TITLE
            // ------------------------------------------

            Text(
                text =
                    "Lifecycle-Aware Location Tracker",

                style =
                    MaterialTheme
                        .typography
                        .headlineSmall
            )


            Spacer(
                modifier =
                    Modifier.height(24.dp)
            )


            // ------------------------------------------
            // PREVIEW CARD
            // ------------------------------------------

            Card(

                modifier =
                    Modifier.fillMaxWidth(),

                colors =
                    CardDefaults.cardColors(
                        containerColor =
                            MaterialTheme
                                .colorScheme
                                .surfaceVariant
                    )

            ) {

                Column(

                    modifier =
                        Modifier.padding(24.dp),

                    horizontalAlignment =
                        Alignment.CenterHorizontally

                ) {

                    Text(
                        text =
                            "Tracking is currently disabled."
                    )


                    Spacer(
                        modifier =
                            Modifier.height(16.dp)
                    )


                    Button(
                        onClick = {}
                    ) {

                        Text(
                            text =
                                "Start Tracking"
                        )
                    }
                }
            }
        }
    }
}