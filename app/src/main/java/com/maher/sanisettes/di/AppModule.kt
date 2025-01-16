package com.maher.sanisettes.di

import com.maher.sanisettes.data.SanisetteRepositoryImpl
import com.maher.sanisettes.domain.SanisetteRepository
import com.maher.sanisettes.presentation.list.SanisettesListViewModel
import com.maher.sanisettes.presentation.map.SanisettesMapViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<SanisetteRepository> {
        SanisetteRepositoryImpl(
            sanisetteApi = get()
        )
    }

    viewModel {
        SanisettesListViewModel(
            sanisetteRepository = get()
        )
    }
    viewModel {
        SanisettesMapViewModel(
            sanisetteRepository = get()
        )
    }
}