package com.sihaloho.core.data.source.remote.network

import com.sihaloho.core.data.source.remote.response.ListTeamResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("lookup_all_teams.php")
    suspend fun getList(
        @Query("id") id : String
    ): ListTeamResponse
}