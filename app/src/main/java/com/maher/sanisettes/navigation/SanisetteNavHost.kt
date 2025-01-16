package com.maher.sanisettes.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.maher.sanisettes.presentation.list.SanisetteListScreenRoute
import com.maher.sanisettes.presentation.map.MapScreenRoute

@Composable
fun SanisetteNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = Screen.SanisetteList.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(Screen.SanisetteList.route) {
            SanisetteListScreenRoute()
        }
        composable(Screen.SanisetteMap.route) {
            MapScreenRoute()
        }
    }
}