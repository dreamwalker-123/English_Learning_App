package com.example.englishlearningapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun MainScreen(
    onTheoryButtonClicked: () -> Unit,
    onPracticeButtonClicked: () -> Unit,
) {
    Column(
        modifier = Modifier
            .padding()
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        FilledTonalButton(
            onClick = onTheoryButtonClicked,
            modifier = Modifier.size(130.dp, 40.dp)
        ) {
            Text(text = "Теория")
        }
        Spacer(modifier = Modifier.height(8.dp))
        FilledTonalButton(
            onClick = onPracticeButtonClicked,
            modifier = Modifier.size(130.dp, 40.dp)
        ) {
            Text(text = "Практика")
        }
    }
}

@Preview
@Composable
fun PreviewMainScreen() {
    MainScreen(
        onTheoryButtonClicked = {},
        onPracticeButtonClicked = {},
    )
}