package com.example.speed

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import com.example.speed.ui.theme.SPEEDTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import java.sql.Time
import android.widget.CalendarView
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Alignment
import java.util.Calendar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SPEEDTheme {
                SPEEDApp()
            }
        }
    }
}

@PreviewScreenSizes
@Composable
fun SPEEDApp() {
    var currentDestination by rememberSaveable { mutableStateOf(AppDestinations.WELCOME) }

    NavigationSuiteScaffold(
        navigationSuiteItems = {
            AppDestinations.entries.forEach {
                item(
                    icon = {
                        Icon(
                            it.icon,
                            contentDescription = it.label
                        )
                    },
                    label = { Text(it.label) },
                    selected = it == currentDestination,
                    onClick = { currentDestination = it }
                )
            }
        }
    ) {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

            when (currentDestination) {
                AppDestinations.WELCOME ->
                    WelcomeScreen(Modifier.padding(innerPadding))

                AppDestinations.CREATE ->
                    CreateScreen(Modifier.padding(innerPadding))

                AppDestinations.RUN ->
                    RunScreen(Modifier.padding(innerPadding))

                AppDestinations.HISTORY ->
                    HistoryScreen(Modifier.padding(innerPadding))
            }
        }
    }
}

enum class AppDestinations(
    val label: String,
    val icon: ImageVector,
) {
    WELCOME("Welcome", Icons.Default.Home),
    CREATE("Create", Icons.Default.Favorite),
    RUN("Run", Icons.Default.AccountBox),
    HISTORY("History", Icons.Default.AccountBox),
}

@Composable
fun WelcomeScreen(modifier: Modifier = Modifier) {
    Text(
        text = "Welcome to SPEED",
        modifier = modifier
    )
}

@Composable
fun CreateScreen(
    modifier: Modifier = Modifier
) {
    var distanceInput by remember { mutableStateOf("") }
    var distance by remember { mutableStateOf("") }
    var estimatedTime by remember { mutableStateOf("") }
    var elevationGain by remember { mutableStateOf("") }

    /*
    Text(
        text = "Create Route Screen",
        modifier = modifier
    )
    */

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

        //Distance input
        Row(
            modifier = Modifier.fillMaxWidth(),
            //horizontalArrangement = Arrangement.SpaceBetween

        ) {
            //CreateRouteBox("Distance Input")
            DistanceInputBox(
                label = "Distance input",
                value = distanceInput,
                onValueChange = {distanceInput = it}
            )

        }

        Spacer(modifier = Modifier.height(32.dp))

        //Map
        Row(
            modifier = Modifier.fillMaxWidth(),
            //horizontalArrangement = Arrangement.SpaceBetween
        ) {
            MapBox("Map goes here")

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

@Composable
fun RunScreen(
    modifier: Modifier = Modifier
) {

    var time by remember { mutableStateOf(0) }
    var distance by remember { mutableStateOf(0f) }
    var isRunning by remember { mutableStateOf(false) }

    // Timer logic
    LaunchedEffect(isRunning) {
        while (isRunning) {
            kotlinx.coroutines.delay(1000)
            time += 1
            distance += 0.05f
        }
    }

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
                text = "Run Route",
                color = Color.White,
                modifier = Modifier.padding(start = 16.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "Map",
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            StatBox("TIME: ${time}s")
            StatBox("DISTANCE: %.2f km".format(distance))
            StatBox("PACE")
        }

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .border(1.dp, Color.Gray),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = if (isRunning) "PAUSE" else "START",
                modifier = Modifier
                    .padding(start = 16.dp)
                    .clickable {
                        isRunning = !isRunning
                    }
            )

            Text(
                text = "END",
                modifier = Modifier
                    .padding(end = 16.dp)
                    .clickable {
                        isRunning = false
                        time = 0
                        distance = 0f
                    }
            )
        }
    }
}

@Composable
fun StatBox(label: String) {
    Column(
        modifier = Modifier
            .width(100.dp)
            .height(150.dp)
            .border(1.dp, Color.Gray),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .border(1.dp, Color.Gray),
            contentAlignment = Alignment.Center
        ) {
            Text(label)
        }
    }
}

@Composable
fun TimeBox(
    label: String
    //time:
) {
    Column(
        modifier = Modifier
            .width(100.dp)
            .height(150.dp)
            .border(1.dp, Color.Gray),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .border(1.dp, Color.Gray),
            contentAlignment = Alignment.Center
        ) {
            Text(
                "Time: ", modifier = Modifier
                    .padding(start = 16.dp)
            )
        }
        Spacer(modifier = Modifier.size(20.dp))

        Text("Time: $", modifier = Modifier
            .padding(start = 16.dp))
    }
}

@Composable
fun DistanceInputBox(
    label: String,
    value: String,
    onValueChange: (String) -> Unit
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .border(1.dp, Color.Gray),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .border(1.dp, Color.Gray),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                label, modifier = Modifier
                    .padding(start = 16.dp)
            )

        }
        TextField(
            value = value,
            onValueChange = onValueChange,
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
    }
}


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

@Composable
fun MapBox(label: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .border(1.dp, Color.Gray),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            label, modifier = Modifier
                .padding(start = 16.dp)
        )

        /*
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .border(1.dp, Color.Gray),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(label, modifier = Modifier
                .padding(start = 16.dp)
            )
        }

         */
    }
}


@Composable
fun HistoryScreen(modifier: Modifier = Modifier) {
    var chosen by remember { mutableStateOf(("No date delected)}")) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally

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
                text = "History",
                color = Color.White,
                modifier = Modifier.padding(start = 16.dp)
            )
        }

        //Text(chosen)
        Spacer(modifier = Modifier.height(50.dp))
        CalendarSection { y, m, d ->
            chosen = "Selected: $m/$d/$y"
        }
    }
}

@Composable
fun CalendarSection(
    onDateSelected: (year: Int, month: Int, day: Int) -> Unit
) {

    AndroidView(
        factory = { context ->
            CalendarView(context).apply {
                // Optional: start on today
                date = Calendar.getInstance().timeInMillis

                setOnDateChangeListener { _, year, month, dayOfMonth ->
                    // month is 0-based (Jan = 0)
                    onDateSelected(year, month + 1, dayOfMonth)
                }
            }
        }
    )
}

