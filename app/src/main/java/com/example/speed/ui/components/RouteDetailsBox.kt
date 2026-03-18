package com.example.speed.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun RouteDetailsBox(
    distance: String,
    estimatedTime: String,
    elevationGain: String
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .border(1.dp, Color.Gray),
        horizontalAlignment = Alignment.Start

    ) {


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .border(1.dp, Color.Gray),
            contentAlignment = Alignment.CenterStart,

            ) {
            Text(
                "Route Details", modifier = Modifier
                    .padding(start = 16.dp)
            )

        }

        Spacer(modifier = Modifier.size(20.dp))

        Text("Distance: $distance", modifier = Modifier
            .padding(start = 16.dp))
        Text("Estimated Time: $estimatedTime", modifier = Modifier
            .padding(start = 16.dp))
        Text("Elevation Gain: $elevationGain", modifier = Modifier
            .padding(start = 16.dp))
    }
}
