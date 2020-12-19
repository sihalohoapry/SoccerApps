package com.sihaloho.core.data

import com.sihaloho.core.data.source.local.LocalDataSource
import com.sihaloho.core.data.source.remote.RemoteDataSource
import com.sihaloho.core.data.source.remote.network.ApiResponse
import com.sihaloho.core.data.source.remote.response.TeamResponse
import com.sihaloho.core.domain.model.Team
import com.sihaloho.core.domain.repository.ITeamRepository
import com.sihaloho.core.utils.AppExecutors
import com.sihaloho.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TeamRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : ITeamRepository {
    override fun getAllTeam(): Flow<Resource<List<Team>>> =
        object : NetworkBoundResource<List<Team>, List<TeamResponse>>() {
            override fun loadFromDB(): Flow<List<Team>> {
                return localDataSource.getAllTeam().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Team>?): Boolean = data == null || data.isEmpty() // ganti dengan true jika ingin selalu mengambil data dari internet

            override suspend fun createCall(): Flow<ApiResponse<List<TeamResponse>>> =
                remoteDataSource.getAllTeam()

            override suspend fun saveCallResult(data: List<TeamResponse>) {
                val teamList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertTeam(teamList)
            }
        }.asFlow()

    override fun getFavoriteTeam(): Flow<List<Team>> {
        return localDataSource.getFavoriteTeam().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteTeam(team: Team, state: Boolean) {
        val teamEntity = DataMapper.mapDomainToEntity(team)
        appExecutors.diskIO().execute { localDataSource.setFavoriteTeam(teamEntity, state) }
    }
}