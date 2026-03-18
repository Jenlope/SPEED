package com.example.speed.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mapbox.geojson.Point

@Composable
fun SelectedPointsBox(
    points: List<Point>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .border(1.dp, Color.Gray)
            .padding(12.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text("Tap the map to add points")
        Text("Selected points: ${points.size}")

        /*
        if (points.isEmpty()) {
            //Text("Tap the map to add points")
        } else {
            points.forEachIndexed { index, point ->
                Text(
                    "Point ${index + 1}: " +
                            "${point.latitude()}, ${point.longitude()}"
                )
            }
        }

        */
    }
}