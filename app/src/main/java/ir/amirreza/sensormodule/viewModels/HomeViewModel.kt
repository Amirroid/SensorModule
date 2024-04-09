package ir.amirreza.sensormodule.viewModels

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import androidx.lifecycle.ViewModel
import ir.amirreza.sensormodule.data.models.UiSensor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HomeViewModel : ViewModel() {
    private val _sensors = MutableStateFlow(emptyList<UiSensor>())
    val sensors = _sensors.asStateFlow()

    private lateinit var sensorManager: SensorManager

    fun getAllSensors(context: Context) {
        sensorManager = context.getSystemService(SensorManager::class.java)
        sensorManager.getSensorList(Sensor.TYPE_ALL).also { sensorList ->
            _sensors.update {
                sensorList.map { sensor -> UiSensor.fromSensor(sensor) }.distinctBy {
                    it.id
                }
            }
        }
    }
}