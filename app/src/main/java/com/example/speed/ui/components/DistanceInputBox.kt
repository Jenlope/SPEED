package com.example.speed.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

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
