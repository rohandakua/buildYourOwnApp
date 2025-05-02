package com.rohandakua.rapidopartnerhelperapp.presentation.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.rohandakua.rapidopartnerhelperapp.di.koinModule
import com.rohandakua.rapidopartnerhelperapp.navigation.NavControllerClass
import com.rohandakua.rapidopartnerhelperapp.ui.theme.RapidoPartnerHelperAppTheme
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /**
         * to init. the koinModule for injection to take p;ace
         */
        startKoin{
            androidContext(this@MainActivity)
            modules(koinModule)
        }

        enableEdgeToEdge()

        setContent {
            RapidoPartnerHelperAppTheme {
                val navController = rememberNavController()
                NavControllerClass(
                    navController = navController,
                    modifier = Modifier
                )
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RapidoPartnerHelperAppTheme {
        Greeting("Android")
    }
}