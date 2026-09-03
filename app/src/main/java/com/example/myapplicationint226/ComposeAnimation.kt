package com.example.myapplicationint226

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


// --------------------------------------------------
// ACTIVITY
// --------------------------------------------------

class ComposeAnimation : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            AnimationScreen()
        }
    }
}


// --------------------------------------------------
// ACTION STATE
// --------------------------------------------------

enum class ActionState {
    IDLE,
    LOADING,
    SUCCESS
}


// --------------------------------------------------
// MAIN ANIMATION SCREEN
// --------------------------------------------------

@Composable
fun AnimationScreen() {

    var isExpanded by remember {
        mutableStateOf(false)
    }

    var currentState by remember {
        mutableStateOf(ActionState.IDLE)
    }


    val scale by animateFloatAsState(
        targetValue = if (isExpanded) 1.5f else 1.0f,
        animationSpec = tween(
            durationMillis = 4000
        ),
        label = "scaleAnimation"
    )


    val opacity by animateFloatAsState(
        targetValue = if (isExpanded) 1.0f else 0.5f,
        animationSpec = tween(
            durationMillis = 4000
        ),
        label = "alphaAnimation"
    )


    val transition = updateTransition(
        targetState = currentState,
        label = "buttonTransition"
    )


    // Button Width Animation
    val buttonWidth by transition.animateDp(
        transitionSpec = {
            tween(durationMillis = 1000)
        },
        label = "buttonWidthAnimation"
    ) { state ->

        when (state) {

            ActionState.IDLE -> 220.dp

            ActionState.LOADING -> 260.dp

            ActionState.SUCCESS -> 220.dp
        }
    }


    // Button Corner Radius Animation
    val cornerRadius by transition.animateDp(
        transitionSpec = {
            tween(durationMillis = 1000)
        },
        label = "cornerRadiusAnimation"
    ) { state ->

        when (state) {

            ActionState.IDLE -> 12.dp
            ActionState.LOADING -> 30.dp
            ActionState.SUCCESS -> 50.dp
        }
    }


    // Button Background Color Animation
    val backgroundColor by transition.animateColor(
        transitionSpec = {
            tween(durationMillis = 1000)
        },
        label = "colorAnimation"
    ) { state ->

        when (state) {

            ActionState.IDLE -> Color(0xFF6200EE)

            ActionState.LOADING -> Color(0xFFFF9800)

            ActionState.SUCCESS -> Color(0xFF4CAF50)
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Box(
            modifier = Modifier
                .size((100 * scale).dp)
                .alpha(opacity)
                .background(
                    color = Color(0xFF6200EE),
                    shape = RoundedCornerShape(12.dp)
                )
                .clickable {
                    isExpanded = !isExpanded
                },
            contentAlignment = Alignment.Center
        ) {

            Text(
                text = if (isExpanded) {
                    "Shrink"
                } else {
                    "Expand"
                },
                color = Color.White
            )
        }


        // Space between animations
        Box(
            modifier = Modifier.height(40.dp)
        )

        Box(
            modifier = Modifier
                .width(buttonWidth)
                .height(60.dp)
                .clip(
                    RoundedCornerShape(cornerRadius)
                )
                .background(backgroundColor)
                .clickable {

                    currentState = when (currentState) {

                        ActionState.IDLE ->
                            ActionState.LOADING

                        ActionState.LOADING ->
                            ActionState.SUCCESS

                        ActionState.SUCCESS ->
                            ActionState.IDLE
                    }
                },
            contentAlignment = Alignment.Center
        ) {

            when (currentState) {

                ActionState.IDLE -> {

                    Text(
                        text = "Download",
                        color = Color.White
                    )
                }

                ActionState.LOADING -> {

                    Text(
                        text = "Downloading...",
                        color = Color.White
                    )
                }

                ActionState.SUCCESS -> {

                    Text(
                        text = "Downloaded ✓",
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "Animation Preview"
)
@Composable
fun AnimationPreview() {

    AnimationScreen()
}