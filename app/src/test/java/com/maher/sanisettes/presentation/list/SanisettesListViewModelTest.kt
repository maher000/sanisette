package com.maher.sanisettes.presentation.list

import androidx.paging.testing.asSnapshot
import com.maher.sanisettes.data.FakeSanisetteRepository
import com.maher.sanisettes.domain.SanisetteRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class SanisettesListViewModelTest {
    private lateinit var viewModel: SanisettesListViewModel
    private lateinit var fakeRepository: SanisetteRepository

    @Before
    fun setup() {
        fakeRepository = FakeSanisetteRepository()
        viewModel = SanisettesListViewModel(fakeRepository)
    }

    @Test
    fun `allSanisettes emits data when repository returns data`() = runTest {
        val items = viewModel.allSanisettes.asSnapshot()
        assertEquals(6, items.size)
    }

    @Test
    fun `sanisettesByDistrict emits data for selected district`() = runTest {
        viewModel.onDistrictSelected("District 1")
        val items = viewModel.sanisettesByDistrict.asSnapshot()
        assertEquals(6, items.size)
    }
}
