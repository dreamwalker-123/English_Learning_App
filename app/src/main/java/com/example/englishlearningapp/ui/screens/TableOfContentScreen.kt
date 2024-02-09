package com.example.englishlearningapp.ui.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.englishlearningapp.R
import com.example.englishlearningapp.data.local.SubTable
import com.example.englishlearningapp.data.local.TableOfContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TableOfContentScreen(
    items: List<SubTable>,
    title: String,
    onSubTableClicked: (String) -> Unit,
    navigateBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(title) },
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
            LazyColumn(modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()) {
                items(items) {
                    SubTableComposable(it, onSubTableClicked)
                }
            }
        }
    }
}

@Composable
fun SubTableComposable(
    item: SubTable,
    onSubTableClicked: (String) -> Unit
) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    Row(modifier = Modifier
            .clickable(onClick = { expanded = !expanded })
            .animateContentSize()
            .padding(bottom = 8.dp)
    ) {
        Column {
            Icon(painter = painterResource(
                    id = if (expanded) R.drawable.expand_less_fill0_wght400_grad0_opsz24
                    else R.drawable.expand_more_fill0_wght400_grad0_opsz24),
                contentDescription = null,
                modifier = Modifier.padding(top = 5.dp))
        }
        Column(modifier = Modifier.padding(start = 5.dp)) {
            Text(text = item.name)
            if (expanded) {
                Card {
                    Column(modifier = Modifier.padding(8.dp)) {
                        (0..<item.list.size).forEach {
                            Row {
                                val width = (item.list[it].indent * 15).dp
                                Spacer(modifier = Modifier.width(width))
                                Text(text = item.list[it].value,
                                    modifier = Modifier.clickable(
                                        onClick = { onSubTableClicked(item.list[it].index) } ) )
                            } }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewTheoryScreen() {
    TableOfContentScreen(
        items = TableOfContent.list,
        title = stringResource(R.string.table_of_contents),
        onSubTableClicked = {},
        navigateBack = {}
        )
}