package com.example.myapplicationint226

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


// Responsive UI
@Composable
fun ResponsiveScreen() {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {

        BoxWithConstraints(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(16.dp)
        ) {

            // Small screen
            if (maxWidth < 480.dp) {

                ColumnLayout()

            } else {

                // Large screen
                RowLayout()
            }
        }
    }
}


// Compact / Mobile Layout
@Composable
fun ColumnLayout() {

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        ProfileAvatar()

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        ProfileDetails()
    }
}


// Expanded / Tablet Layout
@Composable
fun RowLayout() {

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        ProfileAvatar()

        Spacer(
            modifier = Modifier.width(16.dp)
        )

        ProfileDetails()
    }
}


// Avatar
@Composable
fun ProfileAvatar() {

    Box(
        modifier = Modifier
            .size(120.dp)
            .background(
                Color(0xFF6200EE),
                RoundedCornerShape(12.dp)
            ),
        contentAlignment = Alignment.Center
    ) {

        Text(
            text = "Avatar",
            color = Color.White
        )
    }
}


// Profile Information
@Composable
fun ProfileDetails() {

    Column {

        Text(
            text = "Arsalan",
            style = MaterialTheme.typography.headlineSmall
        )

        Text(
            text = "CSE Student",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        Text(
            text = "Responsive Jetpack Compose UI",
            style = MaterialTheme.typography.bodySmall
        )
    }
}


// Preview
@Preview(
    showBackground = true,
    widthDp = 360,
    heightDp = 640
)
@Composable
fun ResponsivePreview() {

    MaterialTheme {
        ResponsiveScreen()
    }
}