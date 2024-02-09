package com.example.englishlearningapp.ui.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.englishlearningapp.R
import com.example.englishlearningapp.data.local.Content2

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PracticeScreen(
    items: List<Content2>,
    navigateBack: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Список упражнений") },
                navigationIcon = {
                    IconButton(onClick = navigateBack) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }
                },
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            Text(text = "Список доступных упражнений в соответствии с пройденными темами",
                modifier = Modifier.padding(start = 8.dp))
            LazyColumn(modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()) {
                items(items) {
                    ContentBox(it)
                }
            }
        }
    }
}

@Composable
fun ContentBox(item:Content2) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    Column {
        Text(text = "К теме ${item.theme} ${item.subTheme}",
            modifier = Modifier
                .clickable(onClick = { expanded = !expanded })
                .animateContentSize())
        if (expanded) {
            var expanded2 by rememberSaveable { mutableStateOf(true) }
            Card(modifier = Modifier.fillMaxWidth()) {
                var text by remember { mutableStateOf("") }
                Text(text = item.task,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(8.dp))
                Text(text = item.material, modifier = Modifier.padding(8.dp))
                OutlinedTextField(value = text,
                    onValueChange = { text = it },
                    modifier = Modifier.padding(start = 8.dp, end = 8.dp).fillMaxWidth())
                Text(text = if (expanded2) "раскрыть..." else "скрыть...", modifier = Modifier
                    .padding(start = 8.dp)
                    .clickable(onClick = { expanded2 = !expanded2 })
                    .animateContentSize())
                if (!expanded2) {
                    Text(text = item.answer, modifier = Modifier.padding(8.dp))
                }
            }
        }
    }
    if (!expanded) {
        Divider()
    }
}

@Preview
@Composable
fun PreviewPracticeScreen() {
    PracticeScreen(
        items = listOf(
            Content2(1,
                stringResource(id = R.string.task1),
                stringResource(id = R.string.material1),
                stringResource(id = R.string.answer1),
                1, 0),
            Content2(1,"qwerty","qwerty","qwerty",1, 1),
        ),
        navigateBack = {  },
    )
}