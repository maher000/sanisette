package com.maher.sanisettes.core.designSystem.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Place
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.maher.sanisettes.navigation.Screen

@Composable
fun AppNavigationBar(
    startDestination: Screen = Screen.SanisetteList,
    navigateToDestination: (Screen) -> Unit
) {
    var currentDestination by rememberSaveable { mutableStateOf(startDestination.route) }

    val destinations = listOf(Screen.SanisetteList, Screen.SanisetteMap)
    val selectedIcons = listOf(Icons.Rounded.Home, Icons.Rounded.Place)
    val icons = listOf(Icons.Outlined.Home, Icons.Outlined.Place)
    SanisetteNavigationBar {
        destinations.forEachIndexed { index, destination ->
            SanisetteNavigationBarItem(
                icon = {
                    Icon(
                        imageVector = icons[index],
                        contentDescription = destination.route,
                    )
                },
                selectedIcon = {
                    Icon(
                        imageVector = selectedIcons[index],
                        contentDescription = destination.route,
                    )
                },
                alwaysShowLabel = false,
                selected = currentDestination == destination.route,
                onClick = {
                    currentDestination = destination.route
                    navigateToDestination(destination)
                }
            )
        }
    }
}

@Composable
fun SanisetteNavigationBar(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit,
) {
    NavigationBar(
        modifier = modifier,
        contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
        tonalElevation = 0.dp,
        content = content,
    )
}

@Composable
fun RowScope.SanisetteNavigationBarItem(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    alwaysShowLabel: Boolean = true,
    icon: @Composable () -> Unit,
    selectedIcon: @Composable () -> Unit = icon,
    label: @Composable (() -> Unit)? = null,
) {
    NavigationBarItem(
        selected = selected,
        onClick = onClick,
        icon = if (selected) selectedIcon else icon,
        modifier = modifier,
        enabled = enabled,
        label = label,
        alwaysShowLabel = alwaysShowLabel,
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
            unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
            selectedTextColor = MaterialTheme.colorScheme.onPrimaryContainer,
            unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
            indicatorColor = MaterialTheme.colorScheme.primaryContainer,
        ),
    )
}
