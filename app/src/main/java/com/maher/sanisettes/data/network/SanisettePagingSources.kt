package com.maher.sanisettes.data.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.google.android.gms.maps.model.LatLng
import com.maher.sanisettes.data.network.model.SanisetteNetwork

class AllSanisettePagingSource(
    private val sanisetteApi: SanisetteApi
) : PagingSource<Int, SanisetteNetwork>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SanisetteNetwork> {
        val offset = params.key ?: 0
        return try {
            val response = sanisetteApi.getSanisettes(limit = DATA_LIMIT, offset = offset)
            LoadResult.Page(
                data = response.data,
                prevKey = null,
                nextKey = if (response.totalCount > offset + DATA_LIMIT) offset + DATA_LIMIT else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, SanisetteNetwork>): Int? {
        return null
    }
}

class SanisettesByDistrictPagingSource(
    private val sanisetteApi: SanisetteApi,
    private val district: String
) : PagingSource<Int, SanisetteNetwork>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SanisetteNetwork> {
        val offset = params.key ?: 0
        return try {
            val response = sanisetteApi.getSanisettesBy(
                limit = DATA_LIMIT,
                offset = offset,
                where = "arrondissement=$district"
            )
            LoadResult.Page(
                data = response.data,
                prevKey = null,
                nextKey = if (response.totalCount > offset + DATA_LIMIT) offset + DATA_LIMIT else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, SanisetteNetwork>): Int? {
        return null
    }

}

class SanisettesByLatLingPagingSource(
    private val sanisetteApi: SanisetteApi,
    private val latLng: LatLng
) : PagingSource<Int, SanisetteNetwork>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SanisetteNetwork> {
        val offset = params.key ?: 0
        return try {
            val response = sanisetteApi.getSanisettesBy(
                limit = DATA_LIMIT,
                offset = offset,
                where = "within_distance(geo_point_2d, geom'POINT(${latLng.longitude} ${latLng.latitude})', 1km)"
            )
            LoadResult.Page(
                data = response.data,
                prevKey = null,
                nextKey = if (response.totalCount > offset + DATA_LIMIT) offset + DATA_LIMIT else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, SanisetteNetwork>): Int? {
        return null
    }

}
