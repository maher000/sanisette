package com.maher.sanisettes.navigation

import androidx.navigation.NavHostController

fun navigateTo(screen: Screen, navController: NavHostController) {
    when(screen) {
        Screen.SanisetteList -> navController.navigate(Screen.SanisetteList.route)
        Screen.SanisetteMap -> navController.navigate(Screen.SanisetteMap.route)
    }
}