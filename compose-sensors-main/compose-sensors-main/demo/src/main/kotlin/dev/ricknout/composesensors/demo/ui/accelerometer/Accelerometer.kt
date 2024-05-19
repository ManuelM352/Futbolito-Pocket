package dev.ricknout.composesensors.demo.ui.accelerometer

import android.content.res.Configuration
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import dev.ricknout.composesensors.accelerometer.isAccelerometerSensorAvailable
import dev.ricknout.composesensors.accelerometer.rememberAccelerometerSensorValueAsState
import dev.ricknout.composesensors.demo.model.Demo
import dev.ricknout.composesensors.demo.ui.Demo
import dev.ricknout.composesensors.demo.ui.NotAvailableDemo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SoccerField() {
    if (isAccelerometerSensorAvailable()) {
        val sensorValue by rememberAccelerometerSensorValueAsState()
        val (x, y, z) = sensorValue.value

        val leftLimit = 60.dp // Derecha
        val rightLimit = 1020.dp
        val topLimit = 60.dp // Arriba
        val bottomLimit = 1940.dp

        // Izquierda + / Derecha -
        // Abajo + / Arriba -
        // Ancho + / Delgado -
        // Largo + / Corto -

        val obstacles = listOf(
            // PORTERIA ARRIBA
            RectObstacle(145.dp, 0.dp, 5.dp, 60.dp),
            RectObstacle(245.dp, 0.dp, 5.dp, 60.dp),
            // VERTICALES PARTE ARRIBA
            RectObstacle(40.dp, 100.dp, 10.dp, 50.dp),
            RectObstacle(340.dp, 100.dp, 10.dp, 50.dp),
            // 2 PRIMEROS
            RectObstacle(40.dp, 170.dp, 10.dp, 50.dp),
            RectObstacle(70.dp, 150.dp, 10.dp, 50.dp),
            // 2 ULTIMOS
            RectObstacle(340.dp, 170.dp, 10.dp, 50.dp),
            RectObstacle(280.dp, 150.dp, 10.dp, 50.dp),
            // 1 VERTICAL 5 FILA
            RectObstacle(40.dp, 300.dp, 10.dp, 50.dp),
            // 2 VERTICAL 5 FILA
            RectObstacle(340.dp, 300.dp, 10.dp, 50.dp),
            // FILA 0
            RectObstacle(50.dp, 50.dp, 10.dp, 10.dp),
            RectObstacle(85.dp, 50.dp, 10.dp, 10.dp),
            RectObstacle(120.dp, 50.dp, 10.dp, 10.dp),
            RectObstacle(120.dp, 75.dp, 10.dp, 10.dp),
            RectObstacle(260.dp, 50.dp, 10.dp, 10.dp),
            RectObstacle(295.dp, 50.dp, 10.dp, 10.dp),
            RectObstacle(330.dp, 50.dp, 10.dp, 10.dp),
            RectObstacle(260.dp, 75.dp, 10.dp, 10.dp),
            // HORIZONTALES PARTE ARRIBA 1 FILA
            RectObstacle(50.dp, 100.dp, 10.dp, 10.dp),
            RectObstacle(85.dp, 100.dp, 10.dp, 10.dp),
            RectObstacle(120.dp, 100.dp, 10.dp, 10.dp),
            RectObstacle(155.dp, 100.dp, 10.dp, 10.dp),
            RectObstacle(190.dp, 100.dp, 10.dp, 10.dp),
            RectObstacle(225.dp, 100.dp, 10.dp, 10.dp),
            RectObstacle(260.dp, 100.dp, 10.dp, 10.dp),
            RectObstacle(295.dp, 100.dp, 10.dp, 10.dp),
            RectObstacle(330.dp, 100.dp, 10.dp, 10.dp),
            // HORIZONTALES PARTE ARRIBA 2 FILA
            RectObstacle(70.dp, 150.dp, 40.dp, 10.dp),
            RectObstacle(140.dp, 150.dp, 40.dp, 10.dp),
            RectObstacle(210.dp, 150.dp, 40.dp, 10.dp),
            RectObstacle(280.dp, 150.dp, 40.dp, 10.dp),
            // HORIZONTALES 3 FILA
            RectObstacle(180.dp, 200.dp, 40.dp, 10.dp),
            RectObstacle(250.dp, 200.dp, 40.dp, 10.dp),
            RectObstacle(100.dp, 200.dp, 40.dp, 10.dp),
            // HORIZONTALES 4 FILA
            RectObstacle(50.dp, 250.dp, 20.dp, 10.dp),
            RectObstacle(85.dp, 250.dp, 10.dp, 10.dp),
            RectObstacle(120.dp, 250.dp, 15.dp, 10.dp),
            RectObstacle(155.dp, 250.dp, 10.dp, 10.dp),
            RectObstacle(190.dp, 250.dp, 20.dp, 10.dp),
            RectObstacle(225.dp, 250.dp, 10.dp, 10.dp),
            RectObstacle(260.dp, 250.dp, 15.dp, 10.dp),
            RectObstacle(295.dp, 250.dp, 20.dp, 10.dp),
            RectObstacle(330.dp, 250.dp, 15.dp, 10.dp),
            // HORIZONTALES 5 FILA
            RectObstacle(90.dp, 300.dp, 50.dp, 10.dp),
            RectObstacle(180.dp, 300.dp, 25.dp, 10.dp),
            RectObstacle(250.dp, 300.dp, 50.dp, 10.dp),
            RectObstacle(320.dp, 300.dp, 10.dp, 10.dp),
            RectObstacle(60.dp, 300.dp, 10.dp, 10.dp),
            // HORIZONTALES 6 FILA
            RectObstacle(300.dp, 330.dp, 10.dp, 10.dp),
            RectObstacle(70.dp, 330.dp, 10.dp, 10.dp),
            RectObstacle(270.dp, 330.dp, 10.dp, 10.dp),
            RectObstacle(120.dp, 330.dp, 10.dp, 10.dp),


            // PORTERIA ARRIBA
            RectObstacle(145.dp, 665.dp, 5.dp, 60.dp),
            RectObstacle(245.dp, 665.dp, 5.dp, 60.dp),
            // VERTICALES PARTE ARRIBA
            RectObstacle(40.dp, 570.dp, 10.dp, 50.dp),
            RectObstacle(340.dp, 570.dp, 10.dp, 50.dp),
            // 2 PRIMEROS
            RectObstacle(40.dp, 495.dp, 10.dp, 50.dp),
            RectObstacle(70.dp, 515.dp, 10.dp, 50.dp),
            // 2 ULTIMOS
            RectObstacle(340.dp, 495.dp, 10.dp, 50.dp),
            RectObstacle(280.dp, 515.dp, 10.dp, 50.dp),
            // 1 VERTICAL 5 FILA
            RectObstacle(40.dp, 365.dp, 10.dp, 50.dp),
            // 2 VERTICAL 5 FILA
            RectObstacle(340.dp, 365.dp, 10.dp, 50.dp),
            // FILA 0
            RectObstacle(50.dp, 665.dp, 10.dp, 10.dp),
            RectObstacle(85.dp, 665.dp, 10.dp, 10.dp),
            RectObstacle(120.dp, 665.dp, 10.dp, 10.dp),
            RectObstacle(120.dp, 635.dp, 10.dp, 10.dp),
            RectObstacle(260.dp, 665.dp, 10.dp, 10.dp),
            RectObstacle(295.dp, 665.dp, 10.dp, 10.dp),
            RectObstacle(330.dp, 665.dp, 10.dp, 10.dp),
            RectObstacle(260.dp, 635.dp, 10.dp, 10.dp),
            // HORIZONTALES PARTE ARRIBA 1 FILA
            RectObstacle(50.dp, 610.dp, 10.dp, 10.dp),
            RectObstacle(85.dp, 610.dp, 10.dp, 10.dp),
            RectObstacle(120.dp, 610.dp, 10.dp, 10.dp),
            RectObstacle(155.dp, 610.dp, 10.dp, 10.dp),
            RectObstacle(190.dp, 610.dp, 10.dp, 10.dp),
            RectObstacle(225.dp, 610.dp, 10.dp, 10.dp),
            RectObstacle(260.dp, 610.dp, 10.dp, 10.dp),
            RectObstacle(295.dp, 610.dp, 10.dp, 10.dp),
            RectObstacle(330.dp, 610.dp, 10.dp, 10.dp),
            // HORIZONTALES PARTE ARRIBA 2 FILA
            RectObstacle(70.dp, 560.dp, 40.dp, 10.dp),
            RectObstacle(140.dp, 560.dp, 40.dp, 10.dp),
            RectObstacle(210.dp, 560.dp, 40.dp, 10.dp),
            RectObstacle(280.dp, 560.dp, 40.dp, 10.dp),
            // HORIZONTALES 3 FILA
            RectObstacle(180.dp, 510.dp, 40.dp, 10.dp),
            RectObstacle(250.dp, 510.dp, 40.dp, 10.dp),
            RectObstacle(100.dp, 510.dp, 40.dp, 10.dp),
            // HORIZONTALES 4 FILA
            RectObstacle(50.dp, 460.dp, 20.dp, 10.dp),
            RectObstacle(85.dp, 460.dp, 10.dp, 10.dp),
            RectObstacle(120.dp, 460.dp, 15.dp, 10.dp),
            RectObstacle(155.dp, 460.dp, 10.dp, 10.dp),
            RectObstacle(190.dp, 460.dp, 20.dp, 10.dp),
            RectObstacle(225.dp, 460.dp, 10.dp, 10.dp),
            RectObstacle(260.dp, 460.dp, 15.dp, 10.dp),
            RectObstacle(295.dp, 460.dp, 20.dp, 10.dp),
            RectObstacle(330.dp, 460.dp, 15.dp, 10.dp),
            // HORIZONTALES 5 FILA
            RectObstacle(90.dp, 410.dp, 50.dp, 10.dp),
            RectObstacle(180.dp, 410.dp, 25.dp, 10.dp),
            RectObstacle(250.dp, 410.dp, 50.dp, 10.dp),
            RectObstacle(320.dp, 410.dp, 10.dp, 10.dp),
            RectObstacle(60.dp, 410.dp, 10.dp, 10.dp),
            // HORIZONTALES 6 FILA
            RectObstacle(300.dp, 380.dp, 10.dp, 10.dp),
            RectObstacle(70.dp, 380.dp, 10.dp, 10.dp),
            RectObstacle(270.dp, 380.dp, 10.dp, 10.dp),
            RectObstacle(120.dp, 380.dp, 10.dp, 10.dp)
        )

        val trampolines = listOf(
            Trampoline(20.dp, 20.dp),
            Trampoline(352.dp, 20.dp),
            Trampoline(20.dp, 695.dp),
            Trampoline(352.dp, 695.dp)
        )

        var topGoals by remember { mutableStateOf(0) }
        var bottomGoals by remember { mutableStateOf(0) }
        var center by remember { mutableStateOf(Offset(rightLimit.value / 2, bottomLimit.value / 2)) }

        val topGoalArea = with(LocalDensity.current) {
            androidx.compose.ui.geometry.Rect(
                left = 145.dp.toPx(),
                top = 0.dp.toPx(),
                right = 245.dp.toPx(),
                bottom = 60.dp.toPx()
            )
        }

        val bottomGoalArea = with(LocalDensity.current) {
            androidx.compose.ui.geometry.Rect(
                left = 145.dp.toPx(),
                top = 665.dp.toPx(),
                right = 245.dp.toPx(),
                bottom = 725.dp.toPx()
            )
        }

        val orientation = LocalConfiguration.current.orientation
        val contentColor = LocalContentColor.current
        val radius = with(LocalDensity.current) { 10.dp.toPx() }

        center = if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            Offset(
                x = (center.x - x).coerceIn(leftLimit.value + radius, rightLimit.value - radius),
                y = (center.y + y).coerceIn(topLimit.value + radius, bottomLimit.value - radius),
            )
        } else {
            Offset(
                x = (center.x + y).coerceIn(leftLimit.value + radius, rightLimit.value - radius),
                y = (center.y + x).coerceIn(topLimit.value + radius, bottomLimit.value - radius),
            )
        }

        // Check for collisions with obstacles and trampolines
        obstacles.forEach { obstacle ->
            center = checkCollision(center, obstacle, radius)
        }

        trampolines.forEach { trampoline ->
            center = checkTrampolineCollision(center, trampoline, radius)
        }

        // Check for goals
        if (center.x in topGoalArea.left..topGoalArea.right && center.y in topGoalArea.top..topGoalArea.bottom) {
            topGoals++
            center = Offset(rightLimit.value / 2, bottomLimit.value / 2)
        }

        if (center.x in bottomGoalArea.left..bottomGoalArea.right && center.y in bottomGoalArea.top..bottomGoalArea.bottom) {
            bottomGoals++
            center = Offset(rightLimit.value / 2, bottomLimit.value / 2)
        }

        Scaffold(
            topBar = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    // Left Goal and Top Goals Counter
                    Box(
                        modifier = Modifier
                            .size(100.dp, 60.dp)
                            .background(Color.White)
                    ) {
                        Text(
                            text = "Top Goals: $topGoals",
                            color = Color.Black,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                    // Right Goal and Bottom Goals Counter
                    Box(
                        modifier = Modifier
                            .size(100.dp, 60.dp)
                            .background(Color.White)
                    ) {
                        Text(
                            text = "Bottom Goals: $bottomGoals",
                            color = Color.Black,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }
        ) { paddingValues ->
            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Green)
                ) {
                    // Field Boundary
                    Box(
                        modifier = Modifier
                            .size(rightLimit, bottomLimit)
                            .padding(20.dp)
                            .background(Color.Transparent)
                            .border(4.dp, Color.White)
                            .align(Alignment.Center)
                    ) {
                        // Midline
                        Divider(
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.Center)
                                .height(4.dp),
                            color = Color.White
                        )
                    }

                    // Draw obstacles
                    obstacles.forEach { obstacle ->
                        Box(
                            modifier = Modifier
                                .offset(obstacle.left, obstacle.top)
                                .size(obstacle.width, obstacle.height)
                                .background(Color.Gray)
                        )
                    }

                    // Draw trampolines
                    trampolines.forEach { trampoline ->
                        Box(
                            modifier = Modifier
                                .offset(trampoline.left, trampoline.top)
                                .size(20.dp, 10.dp)
                                .background(Color.Yellow)
                        )
                    }

                    // Draw the ball
                    Canvas(modifier = Modifier.fillMaxSize()) {
                        drawCircle(
                            color = contentColor,
                            radius = radius,
                            center = center,
                        )
                    }
                }
            }
        }
    }
}

data class RectObstacle(val left: Dp, val top: Dp, val width: Dp, val height: Dp)
data class Trampoline(val left: Dp, val top: Dp)

@Composable
fun checkCollision(center: Offset, obstacle: RectObstacle, radius: Float): Offset {
    val obstacleRect = with(LocalDensity.current) {
        androidx.compose.ui.geometry.Rect(
            left = obstacle.left.toPx(),
            top = obstacle.top.toPx(),
            right = obstacle.left.toPx() + obstacle.width.toPx(),
            bottom = obstacle.top.toPx() + obstacle.height.toPx()
        )
    }

    // Check for collision
    val closestX = center.x.coerceIn(obstacleRect.left, obstacleRect.right)
    val closestY = center.y.coerceIn(obstacleRect.top, obstacleRect.bottom)
    val distanceX = center.x - closestX
    val distanceY = center.y - closestY

    if ((distanceX * distanceX + distanceY * distanceY) < (radius * radius)) {
        // Collision detected, calculate new position after bounce
        val overlapX = radius - kotlin.math.abs(distanceX)
        val overlapY = radius - kotlin.math.abs(distanceY)
        return if (overlapX < overlapY) {
            // Horizontal bounce
            Offset(
                x = if (distanceX < 0) center.x - overlapX else center.x + overlapX,
                y = center.y
            )
        } else {
            // Vertical bounce
            Offset(
                x = center.x,
                y = if (distanceY < 0) center.y - overlapY else center.y + overlapY
            )
        }
    }
    return center
}

@Composable
fun checkTrampolineCollision(center: Offset, trampoline: Trampoline, radius: Float): Offset {
    val trampolineRect = with(LocalDensity.current) {
        androidx.compose.ui.geometry.Rect(
            left = trampoline.left.toPx(),
            top = trampoline.top.toPx(),
            right = trampoline.left.toPx() + 40.dp.toPx(),
            bottom = trampoline.top.toPx() + 40.dp.toPx()
        )
    }

    // Check for collision
    val closestX = center.x.coerceIn(trampolineRect.left, trampolineRect.right)
    val closestY = center.y.coerceIn(trampolineRect.top, trampolineRect.bottom)
    val distanceX = center.x - closestX
    val distanceY = center.y - closestY

    if ((distanceX * distanceX + distanceY * distanceY) < (radius * radius)) {
        // Collision detected with trampoline, add bounce effect
        return Offset(
            x = center.x + (radius * 4) * kotlin.math.sign(distanceX),
            y = center.y + (radius * 30) * kotlin.math.sign(distanceY)
        )
    }
    return center
}


@Composable
fun SoccerField2() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Green)
    ) {
        // Dibuja las porterías
        Box(
            modifier = Modifier
                .size(200.dp, 60.dp)
                .align(Alignment.TopCenter)
                .background(Color.White)
        )
        Box(
            modifier = Modifier
                .size(200.dp, 60.dp)
                .align(Alignment.BottomCenter)
                .background(Color.White)
        )

        // Dibuja los obstáculos manualmente
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            // Obstáculo 1
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .offset(100.dp, 100.dp)
                    .background(Color.Gray)
            )

            // Obstáculo 2
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .offset(300.dp, 200.dp)
                    .background(Color.Gray)
            )

            // Y así sucesivamente...
        }

        // Dibuja el campo de juego
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
                .background(Color.Transparent)
                .border(4.dp, Color.White)
        ) {
            // Dibuja la línea central
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center)
                    .height(4.dp),
                color = Color.White
            )
        }
    }
}


@Composable
fun AccelerometerDemo() {
    if (isAccelerometerSensorAvailable()) {
        val sensorValue by rememberAccelerometerSensorValueAsState()
        val (x, y, z) = sensorValue.value
        Demo(
            demo = Demo.ACCELEROMETER,
            value = "X: $x m/s^2\nY: $y m/s^2\nZ: $z m/s^2",
        ) {
            val width = constraints.maxWidth.toFloat()
            val height = constraints.maxHeight.toFloat()
            var center by remember { mutableStateOf(Offset(width / 2, height / 2)) }
            val orientation = LocalConfiguration.current.orientation
            val contentColor = LocalContentColor.current
            val radius = with(LocalDensity.current) { 10.dp.toPx() }
            center = if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                Offset(
                    x = (center.x - x).coerceIn(radius, width - radius),
                    y = (center.y + y).coerceIn(radius, height - radius),
                )
            } else {
                Offset(
                    x = (center.x + y).coerceIn(radius, width - radius),
                    y = (center.y + x).coerceIn(radius, height - radius),
                )
            }
            Canvas(modifier = Modifier.fillMaxSize()) {
                drawCircle(
                    color = contentColor,
                    radius = radius,
                    center = center,
                )
            }
            // Dibujar el campo de fútbol
            SoccerField()
        }
    } else {
        NotAvailableDemo(demo = Demo.ACCELEROMETER)
    }
}






