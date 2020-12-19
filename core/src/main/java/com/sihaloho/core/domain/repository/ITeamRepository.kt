package com.sihaloho.core.domain.repository

import com.sihaloho.core.data.Resource
import com.sihaloho.core.domain.model.Team
import kotlinx.coroutines.flow.Flow

interface ITeamRepository {

    fun getAllTeam(): Flow<Resource<List<Team>>>

    fun getFavoriteTeam(): Flow<List<Team>>

    fun setFavoriteTeam(team: Team, state: Boolean)

}