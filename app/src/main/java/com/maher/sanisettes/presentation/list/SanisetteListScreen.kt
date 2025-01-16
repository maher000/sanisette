package com.maher.sanisettes.presentation.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.maher.sanisettes.R
import com.maher.sanisettes.core.designSystem.theme.SanisettesTheme
import com.maher.sanisettes.presentation.SanisetteUiModel
import kotlinx.coroutines.flow.MutableStateFlow
import org.koin.androidx.compose.koinViewModel

@Composable
fun SanisetteListScreenRoute(viewModel: SanisettesListViewModel = koinViewModel()) {
    val sanisettes = viewModel.allSanisettes.collectAsLazyPagingItems()
    val filtredSanisettes = viewModel.sanisettesByDistrict.collectAsLazyPagingItems()
    val district by viewModel.district.collectAsState()
    val allDistricts = stringArrayResource(R.array.districts).toList()

    SanisetteListScreen(
        sanisettes = if (district == allDistricts.first()) sanisettes else filtredSanisettes,
        districts = allDistricts,
        selectedDistrict = allDistricts.firstOrNull { it == district }?.toString()
            ?: allDistricts.first(),
        onDistrictSelected = viewModel::onDistrictSelected
    )
}

@Composable
fun SanisetteListScreen(
    sanisettes: LazyPagingItems<SanisetteUiModel>,
    districts: List<String>,
    selectedDistrict: String,
    onDistrictSelected: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        SanisetteTopSection(
            districts = districts,
            selectedDistrict = selectedDistrict,
            onDistrictSelected = onDistrictSelected
        )

        LazyColumn {
            if (sanisettes.loadState.refresh == LoadState.Loading) {
                item {
                    Text(
                        text = "Waiting for items to load...",
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.CenterHorizontally)
                    )
                }
            }
            items(count = sanisettes.itemCount) { index ->
                val item = sanisettes[index]
                item?.let {
                    SanisetteCard(sanisette = item)
                }
            }
            if (sanisettes.loadState.append == LoadState.Loading) {
                item {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.CenterHorizontally)
                    )
                }
            }
        }
    }
}

@Composable
fun SanisetteTopSection(
    districts: List<String>,
    selectedDistrict: String,
    onDistrictSelected: (String) -> Unit
) {
    var districtDropdownExpanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
    ) {
        OutlinedButton(
            onClick = { districtDropdownExpanded = true },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = selectedDistrict)
        }

        DropdownMenu(
            expanded = districtDropdownExpanded,
            onDismissRequest = { districtDropdownExpanded = false },
            modifier = Modifier
                .fillMaxWidth()
        ) {
            districts.forEach { district ->
                DropdownMenuItem(
                    text = { Text(district) },
                    onClick = {
                        onDistrictSelected(district)
                        districtDropdownExpanded = false
                    }
                )
            }
        }
    }
}

@Composable
fun SanisetteCard(sanisette: SanisetteUiModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = sanisette.source, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "${stringResource(R.string.address)} ${sanisette.address}")
            Spacer(modifier = Modifier.height(2.dp))
            Text(text = "${stringResource(R.string.open_hours)} ${sanisette.openHours}")
            Spacer(modifier = Modifier.height(2.dp))
            Text(text = "${stringResource(R.string.manager)} ${sanisette.manager}")
            Spacer(modifier = Modifier.height(2.dp))
            Text(text = "${stringResource(R.string.district)} ${sanisette.district}")
        }
    }
}

@Composable
@Preview
fun SanisetteCardPreview() {
    val sanisetteUiModel = SanisetteUiModel(
        district = "75001",
        address = "123 Avenue, District 1",
        openHours = "07:00 - 22:00",
        manager = "City of Paris",
        source = "Paris Open Data",
        longitude = 1.35,
        latitude = 103.87
    )
    SanisetteCard(sanisetteUiModel)
}

@Composable
@Preview
fun SanisetteListScreenPreview() {
    val sampleSanisettes = listOf(
        SanisetteUiModel(
            district = "Sanisette 1",
            address = "123 Avenue, District 1",
            openHours = "07:00 - 22:00",
            manager = "City of Paris",
            source = "Paris Open Data",
            longitude = 1.35,
            latitude = 103.87

        ),
        SanisetteUiModel(
            district = "Sanisette 2",
            address = "45 Boulevard, District 2",
            openHours = "24/7",
            manager = "City of Paris",
            source = "Paris Open Data",
            longitude = 1.35,
            latitude = 103.87
        )
    )

    val pagingData = PagingData.from(sampleSanisettes)
    val fakeDataFlow = MutableStateFlow(pagingData)
    val lazyPagingItems = fakeDataFlow.collectAsLazyPagingItems()

    val allDistricts = listOf("All", "District 1", "District 2", "District 3")
    SanisettesTheme {
        SanisetteListScreen(
            sanisettes = lazyPagingItems,
            districts = allDistricts,
            selectedDistrict = "All",
            onDistrictSelected = { }
        )
    }
}