package ir.amirreza.sensormodule.viewModels

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import ir.amirreza.sensormodule.data.models.AndroidSensor
import ir.amirreza.sensormodule.data.models.sensors
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class DetailViewModel : ViewModel(), SensorEventListener {
    private val _events = MutableStateFlow(emptyList<FloatArray>())
    val events = _events.asStateFlow()

    var androidSensor by mutableStateOf<AndroidSensor?>(null)

    private lateinit var sensorManager: SensorManager

    fun loadData(context: Context, type: Int) {
        androidSensor = sensors.find { it.id == type }
        sensorManager = context.getSystemService(SensorManager::class.java)
        val sensor = sensorManager.getDefaultSensor(type)
        sensor?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_UI)
        }
    }

    override fun onCleared() {
        sensorManager.unregisterListener(this)
        super.onCleared()
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event != null) {
            events.add(event.values)
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) = Unit
}