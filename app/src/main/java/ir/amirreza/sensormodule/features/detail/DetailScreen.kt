package ir.amirreza.sensormodule.features.detail

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.lifecycle.viewmodel.compose.viewModel
import ir.amirreza.sensormodule.viewModels.DetailViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    id: Int
) {
    val viewModel = viewModel(modelClass = DetailViewModel::class.java)
    val context = LocalContext.current
    val events = viewModel.events
    val androidSensor = viewModel.androidSensor
    LaunchedEffect(key1 = Unit) {
        viewModel.loadData(context, id)
    }
    Column {
        SmallTopAppBar(title = {
            Text(text = "View Sensor")
        }, navigationIcon = {
            IconButton(onClick = {}) {
                Icon(imageVector = Icons.Rounded.ArrowBack, contentDescription = null)
            }
        })
        ChartView(events)
    }

}

@Composable
fun ChartView(events: List<FloatArray>) {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(horizontal = 12.dp)
            .padding(top = 12.dp)
    ) {
        if (events.isNotEmpty()) {
            val limitSize = 30
            val limitedEvents = events.takeLast(limitSize)
            val eachWidth = size.width / limitSize
            val maxValues = List(events.first().size) { index ->
                limitedEvents.maxBy { event -> event[index] }[index]
            }
            val minValues = List(events.first().size) { index ->
                limitedEvents.minBy { event -> event[index] }[index]
            }
            val paths = List(events.first().size) {
                Path().apply {
                    moveTo(0f, size.height / 2)
                }
            }
            limitedEvents.forEach {
                it.forEachIndexed { index, event ->
                    paths[index].apply {
                        lineTo(
                            eachWidth * index.plus(1),
                            getY(minValues[index], maxValues[index], event, size.height)
                        )
                    }
                }
            }
            paths.forEachIndexed { index, path ->
                drawPath(
                    path,
                    colors[index],
                    style = Stroke(2.dp.toPx(), cap = StrokeCap.Round)
                )
            }
        }
    }
}

fun getY(
    s: Float,
    e: Float,
    value: Float,
    height: Float
): Float {
    return height - (height / e.minus(s) * value)
}


val colors = listOf(
    Color.Blue,
    Color.Red,
    Color.Cyan,
    Color.Green,
    Color.Yellow,
)