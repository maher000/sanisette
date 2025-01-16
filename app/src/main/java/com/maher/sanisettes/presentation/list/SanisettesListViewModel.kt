package com.maher.sanisettes.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.maher.sanisettes.domain.SanisetteRepository
import com.maher.sanisettes.presentation.SanisetteUiModel
import com.maher.sanisettes.presentation.toUiModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class SanisettesListViewModel(private val sanisetteRepository: SanisetteRepository) : ViewModel() {

    val district = MutableStateFlow("All")

    val allSanisettes: StateFlow<PagingData<SanisetteUiModel>> =
        sanisetteRepository.getSanisettes()
            .map {
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

    @OptIn(ExperimentalCoroutinesApi::class)
    val sanisettesByDistrict: StateFlow<PagingData<SanisetteUiModel>> = district.flatMapLatest {
        if (it != "All") {
            sanisetteRepository.getSanisettesByDistrict(it)
        } else emptyFlow()
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

    fun onDistrictSelected(district: String) {
        this.district.value = district
    }
}