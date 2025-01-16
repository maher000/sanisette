package com.maher.sanisettes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.rememberNavController
import com.maher.sanisettes.core.designSystem.components.AppNavigationBar
import com.maher.sanisettes.core.designSystem.theme.SanisettesTheme
import com.maher.sanisettes.navigation.SanisetteNavHost
import com.maher.sanisettes.navigation.navigateTo

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            SanisettesTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        CenterAlignedTopAppBar(title = {
                            Text(stringResource(R.string.title_main))
                        })
                    },
                    bottomBar = {
                        AppNavigationBar(
                            navigateToDestination = {
                                navigateTo(screen = it, navController = navController)
                            }
                        )
                    }
                ) { innerPadding ->
                    SanisetteNavHost(
                        modifier = Modifier.padding(innerPadding),
                        navController = navController,
                    )
                }
            }
        }
    }
}