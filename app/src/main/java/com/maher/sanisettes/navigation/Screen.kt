package com.maher.sanisettes.navigation

enum class ScreenName {
    SANISETTE_LIST,
    SANISETTE_MAP,
}

sealed class Screen(val route: String) {
    data object SanisetteList : Screen(ScreenName.SANISETTE_LIST.name)
    data object SanisetteMap : Screen(ScreenName.SANISETTE_MAP.name)
}