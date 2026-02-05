package com.example.alasli

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.alasli.ui.theme.AlasliTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AlasliTheme {
                var showSplash by remember { mutableStateOf(true) }

                Surface(modifier = Modifier.fillMaxSize()) {
                    if (showSplash) {
                        SplashScreen(onTimeout = { showSplash = false })
                    } else {
                        HomeScreenWithNav()
                    }
                }
            }
        }
    }
}