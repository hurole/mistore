package tech.cvfe.mistore

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import tech.cvfe.mistore.ui.theme.MistoreTheme


val TAG = "MainActivity"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MistoreTheme {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(WindowInsets.statusBars.asPaddingValues()).fillMaxSize()
                ) {
                    Greeting(
                        name = "Android"
                    )
                }
            }

        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(
        text = "Hello $name!",
    )
    Button(onClick = { Log.d(TAG, "Greeting: 123") }) {
        Text("按钮")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MistoreTheme {
        Greeting("Android")
    }
}