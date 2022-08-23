package com.example.quotes.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.quotes.presentation.viewmodel.MainViewModel
import com.example.quotes.ui.firstpage.firstPage
import com.example.quotes.ui.secondpage.SecondPage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/**
 * Main activity
 *
 * @constructor Create empty Main activity
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            mainViewModel.getQuotesFlow()
        }
        mainViewModel.randomQuoteLiveData.observe(this) {
            Log.i("MiladTest", "onCreate: $it")
        }
//        mainViewModel.getDashboardDataCombined()
        setContent {
            val navController = rememberNavController()

            dialog(mainViewModel)
            NavHost(navController = navController, startDestination = "firstpage") {
                composable("firstpage") {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colors.background
                    ) {
                        firstPage(mainViewModel) { navController.navigate("secondpage") }
                    }
                }
                composable("secondpage") {
                    SecondPage()
                }
                /*...*/
            }
            // A surface container using the 'background' color from the theme

        }
    }
}

/**
 * Dialog
 *
 * @param mainViewModel
 */
@SuppressLint("UnrememberedMutableState")
@Composable
fun dialog(mainViewModel: MainViewModel) {
    val state = mainViewModel.randomQuoteErrorLiveData.observeAsState()
    val openDialog = mainViewModel.showErrorDialogLiveData.observeAsState()
    val text = state.value?.message
    if (openDialog.value == true) {
        AlertDialog(
            onDismissRequest = {
                mainViewModel.hideDialog()
            },
            title = {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = text ?: ""
                )
            },
            buttons = {
                Row(
                    modifier = Modifier.padding(all = 8.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { false.also { mainViewModel.hideDialog() } }
                    ) {
                        Text("Dismiss")
                    }
                }
            }
        )
    }
}