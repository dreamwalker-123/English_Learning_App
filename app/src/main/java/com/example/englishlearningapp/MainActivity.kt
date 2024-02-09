package com.example.englishlearningapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.englishlearningapp.data.dagger.Computer
import com.example.englishlearningapp.ui.MainApp
import com.example.englishlearningapp.ui.theme.EnglishLearningAppTheme
import java.security.AccessController.getContext
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    // тренировка
    @Inject
    lateinit var computer: Computer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // тренировка
        appComponent.inject(this)

        setContent {
            EnglishLearningAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainApp()
                }
            }
        }
    }
}
