package com.sihaloho.core.domain.usecase

import com.sihaloho.core.data.Resource
import com.sihaloho.core.domain.model.Team
import kotlinx.coroutines.flow.Flow

interface TeamUseCase {
    fun getAllTeam(): Flow<Resource<List<Team>>>
    fun getFavoriteTeam(): Flow<List<Team>>
    fun setFavoriteTeam(team: Team, state: Boolean)
}