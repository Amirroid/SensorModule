package ir.amirreza.sensormodule.data.models

import android.hardware.Sensor
import androidx.compose.runtime.Immutable

@Immutable
data class UiSensor(
    val id: Int,
    val name: String
) {
    companion object {
        fun fromSensor(sensor: Sensor) = UiSensor(sensor.type, sensor.name)
    }
}
