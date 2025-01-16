package com.maher.sanisettes.presentation.map

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.google.android.gms.maps.model.LatLng
import com.maher.sanisettes.domain.SanisetteRepository
import com.maher.sanisettes.presentation.SanisetteUiModel
import com.maher.sanisettes.presentation.toUiModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class SanisettesMapViewModel(private val sanisetteRepository: SanisetteRepository) : ViewModel() {

    val latLng = MutableStateFlow(PARIS_LAT_LNG)

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    val allSanisettes: StateFlow<PagingData<SanisetteUiModel>> =
        latLng
            .debounce(1000L)
            .flatMapLatest {
                sanisetteRepository.getSanisettesByLatLng(latLng.value)
            }.map {
                it.map { sanisette ->
                    sanisette.toUiModel()
                }
            }
            .cachedIn(viewModelScope)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = PagingData.empty(),
            )

    fun onLatLngChanged(latLng: LatLng) {
        this.latLng.value = latLng
    }
}

val PARIS_LAT_LNG = LatLng(48.866667, 2.333333)