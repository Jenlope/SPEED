package com.example.speed.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mapbox.geojson.Point
import com.example.speed.ui.components.DistanceInputBox
import com.example.speed.ui.components.MapBoxView
import com.example.speed.ui.components.RouteDetailsBox
import com.example.speed.ui.components.SelectedPointsBox

@Composable
fun CreateScreen(
    modifier: Modifier = Modifier
) {
    var distanceInput by remember { mutableStateOf("") }
    var distance by remember { mutableStateOf("") }
    var estimatedTime by remember { mutableStateOf("") }
    var elevationGain by remember { mutableStateOf("") }
    var points by remember { mutableStateOf(listOf<Point>()) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Top Title Bar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(Color.Black),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = "Create Route",
                color = Color.White,
                modifier = Modifier.padding(start = 16.dp)
            )


        }

        Spacer(modifier = Modifier.height(32.dp))

        //add box to display point selection
        SelectedPointsBox(points)

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            //horizontalArrangement = Arrangement.SpaceBetween
        ) {
            MapBoxView(
                modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
                onPointsChanged = {points = it})

        }

        Spacer(modifier = Modifier.height(32.dp))

        //Route details
        Row(
            modifier = Modifier.fillMaxWidth(),
            //horizontalArrangement = Arrangement.SpaceBetween
        ) {
            RouteDetailsBox(
                distance = distance,
                estimatedTime = estimatedTime,
                elevationGain = elevationGain
            )

        }

        Spacer(modifier = Modifier.weight(1f))

        //Bottom selection bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .border(1.dp, Color.Gray),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "RELOAD",
                modifier = Modifier
                    .padding(start = 16.dp)
                    .clickable {

                    }
            )

            Text(
                text = "SAVE",
                modifier = Modifier
                    .padding(end = 16.dp)
                    .clickable {

                    }
            )

            Text(
                text = "HISTORY",
                modifier = Modifier
                    .padding(end = 16.dp)
                    .clickable {

                    }
            )

            Text(
                text = "START",
                modifier = Modifier
                    .padding(end = 16.dp)
                    .clickable {

                    }
            )
        }
    }
}
