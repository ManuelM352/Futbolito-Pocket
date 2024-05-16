package dev.ricknout.composesensors.demo.ui.accelerometer

import android.content.res.Configuration
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.unit.dp
import dev.ricknout.composesensors.accelerometer.isAccelerometerSensorAvailable
import dev.ricknout.composesensors.accelerometer.rememberAccelerometerSensorValueAsState
import dev.ricknout.composesensors.demo.model.Demo
import dev.ricknout.composesensors.demo.ui.Demo
import dev.ricknout.composesensors.demo.ui.NotAvailableDemo



@Composable
fun SoccerField() {
    if (isAccelerometerSensorAvailable()) {
        val sensorValue by rememberAccelerometerSensorValueAsState()
        val (x, y, z) = sensorValue.value

        // Coliciones de la pantalla del de juego
        val leftLimit = 60.dp //Derecha
        val rightLimit = 1020.dp
        val topLimit = 60.dp //Arriba
        val bottomLimit = 1940.dp

        Demo(
            demo = Demo.ACCELEROMETER,
            value = "X: $x m/s^2\nY: $y m/s^2\nZ: $z m/s^2",
        ) {
            var center by remember { mutableStateOf(Offset.Zero) }
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

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Green)
            ) {
                // Porterías
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

                // Obstaculos
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .offset(100.dp, 100.dp)
                            .background(Color.Gray)
                    )
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .offset(300.dp, 200.dp)
                            .background(Color.Gray)
                    )
                }

                // Campo de juego
                Box(
                    modifier = Modifier
                        .size(rightLimit, bottomLimit)
                        .padding(20.dp)
                        .background(Color.Transparent)
                        .border(4.dp, Color.White)
                ) {
                    // Linea
                    Divider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.Center)
                            .height(4.dp),
                        color = Color.White
                    )
                }

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





