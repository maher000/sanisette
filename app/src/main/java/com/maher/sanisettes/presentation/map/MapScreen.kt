package com.maher.sanisettes.presentation.map

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.maher.sanisettes.presentation.SanisetteUiModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MapScreenRoute(viewModel: SanisettesMapViewModel = koinViewModel()) {

    val sanisettes = viewModel.allSanisettes.collectAsLazyPagingItems()

    MapScreen(
        sanisettes = sanisettes,
        fetchSanisettes = viewModel::onLatLngChanged
    )
}

@Composable
fun MapScreen(
    sanisettes: LazyPagingItems<SanisetteUiModel>,
    fetchSanisettes: (LatLng) -> Unit
    ) {

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(PARIS_LAT_LNG, 10f)
    }
    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        onMapLoaded = {
            val bounds = calculateVisibleBounds(cameraPositionState)
            fetchSanisettes(bounds.center)
        }
    ) {


        sanisettes.itemSnapshotList.forEach { sanisette ->
            sanisette?.let {
                Marker(
                    state = MarkerState(position = LatLng(it.latitude, it.longitude)),
                    title = it.address
                )
            }
        }

        LaunchedEffect(cameraPositionState.isMoving) {
            if (!cameraPositionState.isMoving) {
                val bounds = calculateVisibleBounds(cameraPositionState)
                fetchSanisettes(bounds.center)
            }
        }
    }

}

fun calculateVisibleBounds(cameraPositionState: CameraPositionState): LatLngBounds {
    val projection = cameraPositionState.projection ?: return LatLngBounds(
        LatLng(0.0, 0.0), LatLng(0.0, 0.0)
    )
    return projection.visibleRegion.latLngBounds
}