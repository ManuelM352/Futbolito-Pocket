package dev.ricknout.composesensors.demo.model

import android.view.View
import dev.ricknout.composesensors.demo.R

enum class Demo(
    val title: String,
    val iconResourceId: Int,
) {
    NONE("", View.NO_ID),
    ACCELEROMETER("", R.drawable.ic_accelerometer_24dp),
    AMBIENT_TEMPERATURE("Ambient Temperature", R.drawable.ic_ambient_temperature_24dp),
    GRAVITY("Gravity", R.drawable.ic_gravity_24dp),
    GYROSCOPE("Gyroscope", R.drawable.ic_gyroscope_24dp),
    LIGHT("Light", R.drawable.ic_light_24dp),
    LINEAR_ACCELERATION("Linear Acceleration", R.drawable.ic_linear_acceleration_24dp),
    MAGNETIC_FIELD("Magnetic Field", R.drawable.ic_magnetic_field_24dp),
    PRESSURE("Pressure", R.drawable.ic_pressure_24dp),
    PROXIMITY("Proximity", R.drawable.ic_proximity_24dp),
    RELATIVE_HUMIDITY("Relative Humidity", R.drawable.ic_relative_humidity_24dp),
}
