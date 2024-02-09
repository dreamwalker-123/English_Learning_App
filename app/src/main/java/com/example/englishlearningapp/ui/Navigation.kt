package com.example.englishlearningapp.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.englishlearningapp.R
import com.example.englishlearningapp.data.local.Content2
import com.example.englishlearningapp.data.local.TableOfContent
import com.example.englishlearningapp.ui.screens.ArticleScreen
import com.example.englishlearningapp.ui.screens.MainScreen
import com.example.englishlearningapp.ui.screens.PracticeScreen
import com.example.englishlearningapp.ui.screens.TableOfContentScreen


@Composable
fun MainApp(
    viewModel: EngLearningViewModel = viewModel(factory = EngLearningViewModel.factory)
) {
    val fullContent by viewModel.getFull().collectAsState(emptyList())
    val fullContentFromSecondTable by viewModel.getFullSecondTable().collectAsState(emptyList())

    val onlyPassed by viewModel.getOnlyPassed().collectAsState(emptyList())

//    логика группировки и фильтрации данных из sqlite таблиц, затем передаваемых в PracticeScreen
    val list = mutableListOf<String>()
    onlyPassed.forEach { list.add(it.index) }
    val gropedList = list.groupBy { it.first() }
    val resultList = mutableListOf<String>() // "1 9", 2 4"
    for ((c, strings) in gropedList) {
        val list2 = mutableListOf<Int>()
        for (i in strings) {
            list2.add(i.split(" ")[1].toInt())
        }
        resultList.add("$c ${list2.maxOrNull()}")
    }
    val resultForSure = mutableListOf<Content2>()
    for (i in fullContentFromSecondTable) {
        for (j in resultList) {
            if (i.theme == j.split(" ").first().toInt() &&
                i.subTheme <= j.split(" ")[1].toInt()) {
                resultForSure.add(i)
            }
        }
    }

    val navController = rememberNavController()
    var index = 0
    NavHost(navController = navController, startDestination = Route.MAIN.path) {
        composable(Route.MAIN.path) {
            MainScreen(
                onTheoryButtonClicked = { navController.navigate(Route.TABLE_OF_CONTENT.path) },
                onPracticeButtonClicked = { navController.navigate(Route.PRACTICE.path) }
            )
        }
        composable(Route.TABLE_OF_CONTENT.path) {
            TableOfContentScreen(
                items = TableOfContent.list,
                title = stringResource(R.string.table_of_contents),
                onSubTableClicked = {
                    val chapterIndex = it
                    fullContent.forEach { content ->
                        if (content.index == chapterIndex) {
                            index = content.id - 1
                        }
                    }
                    navController.navigate(Route.ARTICLE.path)
                },
                navigateBack = { navController.navigateUp() }
            )
        }
        composable(Route.ARTICLE.path) {
            ArticleScreen(
                item = fullContent[index],
                navigateBack = { navController.navigateUp() },
                toPrevious = {
                    if (index > 0) index -= 1
                    navController.navigate(Route.ARTICLE.path)
                },
                toNext= {
                    if (index >= 0) index += 1
                    navController.navigate(Route.ARTICLE.path)
                },
                toPractice = {
                    navController.navigate(Route.PRACTICE.path)
                }
            )
        }
        composable(Route.PRACTICE.path) {
            PracticeScreen(
                items = resultForSure,
                navigateBack = { navController.navigateUp() }
            )
        }
    }
}

enum class Route(val path: String) {
    MAIN("Main"),
    TABLE_OF_CONTENT("TableOfContent"),
    ARTICLE("Article"),
    PRACTICE("Practice")
}
