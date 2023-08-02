package com.pisiitech.snackbarkullanimi

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.pisiitech.snackbarkullanimi.ui.theme.SnackBarKullanimiTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SnackBarKullanimiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Sayfa()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Sayfa() {
    val snackbarHostState = remember { SnackbarHostState() } //SnackBar'i gostermek icin kullanilan degisken
    val scope = rememberCoroutineScope() //performansli olmasini saglamak icin kullanilan bir degisken

    Scaffold(
        snackbarHost = {
                       SnackbarHost(hostState = snackbarHostState) {
                           Snackbar(
                               snackbarData = it,
                               containerColor = Color.White,
                               contentColor = Color.Blue,
                               actionColor = Color.Red
                           )
                       }
        },
        content = {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(onClick = {
                    scope.launch {
                        snackbarHostState.showSnackbar("Merhaba")
                    }
                }) {
                    Text(text = "Varsayilan")
                }

                Button(onClick = {
                    scope.launch {
                        val sb = snackbarHostState.showSnackbar("Silmek ister misin?", actionLabel = "Evet")

                        if(sb == SnackbarResult.ActionPerformed) {
                            snackbarHostState.showSnackbar("Silindi")
                        }
                    }
                }) {
                    Text(text = "SnackBar Action")
                }

                Button(onClick = {
                    scope.launch {
                        val sb = snackbarHostState.showSnackbar("Internet Baglantisi Yok!", actionLabel = "Tekrar Dene")

                        if(sb == SnackbarResult.ActionPerformed) {
                            snackbarHostState.showSnackbar("Tekrar denendi")
                        }
                    }
                }) {
                    Text(text = "SnackBar Ozel")
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SnackBarKullanimiTheme {
        Sayfa()
    }
}