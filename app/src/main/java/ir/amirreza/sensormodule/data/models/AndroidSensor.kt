package ir.amirreza.sensormodule.data.models

import android.hardware.Sensor
import androidx.compose.runtime.Immutable


@Immutable
data class AndroidSensor(
    val id:Int,
    val name: String,
    val description: String,
    val state: String,
    val maxValue: Int? = null,
    val minValue: Int? = null,
    val valueType: String? = null,
    val help: String? = null
)

val sensors = listOf(
    AndroidSensor(
        id = Sensor.TYPE_ACCELEROMETER,
        name = "Accelerometer",
        description = "Measures acceleration along the x, y, and z axes.",
        state = "Depends on the specific application (e.g., stationary, moving, tilting)",
        maxValue = 20,
        minValue = 0,
        valueType = "m/s²"
    ),
    AndroidSensor(
        id = Sensor.TYPE_GYROSCOPE,
        name = "Gyroscope",
        description = "Measures angular velocity around the x, y, and z axes.",
        state = "Depends on the specific application (e.g., stationary, rotating, angular movement)",
        maxValue = 2000,
        minValue = 1,
        valueType = "degrees/second"
    ),
    AndroidSensor(
        id = Sensor.TYPE_MAGNETIC_FIELD,
        name = "Magnetometer",
        description = "Measures magnetic field strength along the x, y, and z axes.",
        state = "Depends on the specific application (e.g., detecting orientation, detecting magnetic fields)",
        maxValue = 100,
        minValue = 0,
        valueType = "microteslas"
    ),
    AndroidSensor(
        id = Sensor.TYPE_PROXIMITY,
        name = "Proximity Sensor",
        description = "Measures the distance between the device and an object nearby.",
        state = "Depends on the specific application (e.g., object detected or not)"
    ),
    AndroidSensor(
        id = Sensor.TYPE_LIGHT,
        name = "Light Sensor",
        description = "Measures ambient light intensity.",
        state = "Depends on the current lighting conditions and environment. Example: Office or classroom lighting",
        maxValue = 100000,
        minValue = 1,
        valueType = "lux",
        help = """
            Dimly lit room: 50-100 lux
            Office or classroom lighting: 300-500 lux
            Well-lit indoor spaces: 500-1000 lux
            Direct sunlight: 10,000-25,000 lux
        """.trimIndent()
    ),
//    AndroidSensor(
//        id = Sensor.,
//        name = "Barometer",
//        description = "Measures atmospheric pressure.",
//        state = "Depends on the specific application (e.g., measuring atmospheric pressure)",
//        maxValue = 1100,
//        minValue = 300,
//        valueType = "hPa"
//    ),
    AndroidSensor(
        id = Sensor.TYPE_AMBIENT_TEMPERATURE,
        name = "Temperature Sensor",
        description = "Measures ambient temperature of the device.",
        state = "Depends on the specific application (e.g., ambient temperature)",
        maxValue = 100,
        minValue = -40,
        valueType = "°C"
    ),
    AndroidSensor(
        id = Sensor.TYPE_GRAVITY,
        name = "Gravity Sensor",
        description = "Measures the force of gravity along the x, y, and z axes.",
        state = "Depends on the specific application (e.g., detecting orientation relative to gravity)",
        maxValue = 10,
        minValue = -10,
        valueType = "m/s²"
    )
)
