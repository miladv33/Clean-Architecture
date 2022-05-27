package com.example.takehomeassesmenttestnumber1

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.example.takehomeassesmenttestnumber1.ui.theme.TakeHomeAssesmentTestNumber1Theme
import com.example.takehomeassesmenttestnumber1.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

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
        setContent {
            dialog(mainViewModel)
            // A surface container using the 'background' color from the theme
            Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
//                    Greeting(mainViewModel)
            }
        }
    }
}

@Composable
fun Greeting(viewModel: MainViewModel) {
    val quote = viewModel.randomQuoteLiveData.observeAsState()
    Column(modifier = Modifier.padding(10.dp)) {
        Text(text = " From ${quote.value?.author ?: ""}: ")
        Text(modifier = Modifier.padding(start = 15.dp), text = quote.value?.content ?: "")
        Button(modifier = Modifier.padding(top = 15.dp), onClick = {
            viewModel.getQuotesFlow()
        }) {
            Text(text = "Another Quote")
        }
    }
}

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
                Text(modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center, text = text ?: "")
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