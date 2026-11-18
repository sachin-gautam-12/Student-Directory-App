package com.example.myapplicationint226

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

@Composable
fun ReactiveFlowScreen() {

    val context = LocalContext.current

    var counter by remember {
        mutableIntStateOf(0)
    }

    // Cold Flow
    val timerFlow = remember {
        flow {
            var seconds = 0

            while (true) {
                emit(seconds)
                delay(1000L)
                seconds++
            }
        }
    }

    val timerValue by timerFlow.collectAsState(initial = 0)

    // Toast when screen starts
    LaunchedEffect(Unit) {
        Toast.makeText(
            context,
            "Flow Started",
            Toast.LENGTH_SHORT
        ).show()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Reactive Flow",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Cold Flow Timer: $timerValue sec",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Counter: $counter",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                counter++
            }
        ) {
            Text(text = "Increment Counter")
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "Reactive Flow Preview"
)
@Composable
fun ReactiveFlowScreenPreview() {

    MaterialTheme {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Reactive Flow",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Cold Flow Timer: 0 sec",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Counter: 0",
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {}) {
                Text(text = "Increment Counter")
            }
        }
    }
}