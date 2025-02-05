package com.example.pokedex

import android.os.Bundle
import android.os.SystemClock
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.pokedex.presentation.PokemonViewModel
import com.example.pokedex.ui.navigation.NavGraph
import com.example.pokedex.ui.theme.PokedexTheme
import com.google.firebase.Firebase
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.analytics
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var analytics: FirebaseAnalytics
    private lateinit var viewModel: PokemonViewModel
    private var starTime: Long = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        analytics = Firebase.analytics
        enableEdgeToEdge()
        setContent {
            PokedexTheme {
                viewModel = hiltViewModel<PokemonViewModel>()
                val navController = rememberNavController()
                NavGraph(navController, viewModel, analytics)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        starTime = SystemClock.elapsedRealtime()
    }

    @Composable
    fun Greetings(name:String){
        Text(
            text = "hello $name"
        )
    }

    @Preview(showBackground = true)
    @Composable
    fun GretingsRpreview(){
        Greetings(name = "marcelo")
    }

}
