package com.example.englishlearningapp.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.englishlearningapp.R
import com.example.englishlearningapp.data.local.Content
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleScreen(
    item: Content,
    navigateBack: () -> Unit,
    toPrevious: () -> Unit,
    toNext: () -> Unit,
    toPractice: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(item.title, fontSize = 18.sp) },
                navigationIcon = {
                    IconButton(onClick =  navigateBack ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }
                },
            )
        },
        bottomBar = {
            BottomAppBar(actions = {
                IconButton(onClick = { toPrevious() }) {
                    Icon(Icons.Filled.KeyboardArrowLeft, contentDescription = "Localized description")
                }
                Button(onClick = toPractice) {
                    Text(text = "К практике")
                }
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Прочитано")
                }
                Spacer(modifier = Modifier.weight(0.1f))
                IconButton(onClick = { toNext() }) {
                    Icon(Icons.Filled.KeyboardArrowRight, contentDescription = "Localized description")
                }
            })
        }
    ) { innerPadding ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)) {
            val scrollState = rememberScrollState()
            val scope = rememberCoroutineScope()
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
                    .verticalScroll(scrollState),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top
            ) {
                Text(text = item.value)
            }
            Box(
                Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = 50.dp, bottom = 50.dp)
                    .clickable { scope.launch { scrollState.animateScrollTo(scrollState.value + 100000) } }
            ) {
                Icon(painterResource(id = R.drawable.arrow_circle_down_fill0_wght700_grad0_opsz48),
                    contentDescription = "ArrowDown")

            }
        }
    }
}

@Preview
@Composable
fun PreviewArticleScreen() {
    ArticleScreen(
        item = Content(1, "\tТема 1.0\n" +
                "\t1. Именем существительным называется часть речи, которая обозначает предмет. Предметом в грамматике называют все то, о чем можно спросить: who is this? кто это? или what is this? что это? Например: who is this? кто это? — a man человек, a girl девочка, an engineer инженер; what is this? что это? — a house дом, wheat пшеница, darkness темнота, work работа.\n" +
                "\t2. Имена существительные обычно сопровождаются артиклями или другими определителями и часто сочетаются с предлогами. Артикли и другие определители, а также предлоги являются признаками существительного: а table, the table стол; this book эта книга; my pencil мой карандаш; in the room в комнате; with sugar с сахаром.\n" +
                "\t3. Имена существительные имеют два числа: единственное и множественное : a table.(ед. ч.) стол, tables (мн. ч.) столы; a book (ед. ч.) книга, books (мн. ч.) книги.\n" +
                "\t4. Имена существительные имеют два падежа: общий и притяжательный: worker (общий падеж), worker's (притяжательный падеж); father (общий падеж), father's (притяжательный падеж).\n" +
                "\t5. Род имен существительных в английском языке определяется не формой слова, а его значением. Имена существительные, обозначающие одушевленные предметы, бывают мужского или женского рода, в зависимости от обозначаемого ими пола: a man (мужской род) мужчина, а woman (женский род) женщина. Имена существительные, обозначающие неодушевленные предметы, относятся к среднему роду: a chair стул, water вода, a window окно.\n" +
                "\t6. Имена существительные бывают простые, производные и сложные. \n" +
                "\tК простым именам существительным относятся существительные, не имеющие в своем составе ни префиксов, ни суффиксов: ship корабль, town город, book книга, wheat пшеница. \n" +
                "\tК производным именам существительным относятся существительные, имеющие в своем составе суффиксы или префиксы, или одновременно и те и другие: darkness темнота, misprint опечатка, unemployment безработица.\n" +
                "\tК наиболее характерным суффиксам производных существительных относятся:\n" +
                "-age:\n" +
                "-апсе,\n" +
                "-dom:\n" +
                "-ег:\n" +
                "-hood:\n" +
                "-ion:\n" +
                "-ment:\n" +
                "-ness:\n" +
                "-ship:\n" +
                "-ty:\n" +
                "-ure:\n" +
                "passage проход,\n" +
                "ence: resistance сопротивление,\n" +
                "freedom свобода,\n" +
                "worker рабочий,\n" +
                "childhood детство,\n" +
                "restriction ограничение,\n" +
                "development развитие,\n" +
                "happiness счастье,\n" +
                "leadership руководство,\n" +
                "safety безопасность,\n" +
                "departure отъезд,\n" +
                "marriage брак\n" +
                "difference разница\n" +
                "wisdom мудрость\n" +
                "writer писатель\n" +
                "neighbourhood соседство\n" +
                "connection связь\n" +
                "government правительство\n" +
                "kindness любезность\n" +
                "friendship дружба\n" +
                "certainty уверенность\n" +
                "pleasure удовольствие\n" +
                "\tСуществительные почти не имеют характерных префиксов. Префиксы существительных в основном совпадают с префиксами глаголов и прилагательных, так как они встречаются главным образом в существительных, образованных от этих частей речи: reconstruction реконструкция, disarmament разоружение, uneasiness беспокойство, inequality неравенство. \n" +
                "\tК сложным именам существительным относятся существительные, образованные из соединения двух слов в одно. Они пишутся слитно или через дефис (черточку): bedroom спальня, newspaper газета, dining-room столовая. Некоторые сложные существительные состоят из двух слов с предлогом между ними: commander-in-chief главнокомандующий, mother-inlaw свекровь, теща,\n" +
                "\t\t\t\t\t\t\t\t\t(См. приложение 1 — т. 2, стр. 86)\n" +
                "\t7. Имена существительные могут употребляться в предложении в функции:\n" +
                "\tа) подлежащего:\n" +
                "The train leaves at six o'clock. Поезд отходит в шесть часов.\n" +
                "\tб) именной части составного сказуемого:\n" +
                "Не is a teacher. Он преподаватель.\n" +
                "\tв) дополнения (прямого, беспредложного косвенного и предложного\n" +
                "косвенного):\n" +
                "I've received a telegram. Я получил телеграмму.\n" +
                "We've sent the buyers a letter. Мы послали покупателям письмо.\n" +
                "I'll speak to the manager. Я поговорю с заведующим.\n" +
                "\tг) определения :\n" +
                "This is the manager's room. Это комната заведующего.\n" +
                "\tд) обстоятельства :\n" +
                "There is a hospital in the village. И деревне имеется больница.", "1 0", "Имя существительное (The Noun)", false),
        navigateBack = {},
        toPrevious = {},
        toNext = {},
        toPractice = {},
    )
}