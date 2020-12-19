package com.sihaloho.core.domain.usecase

import com.sihaloho.core.domain.model.Team
import com.sihaloho.core.domain.repository.ITeamRepository

class TeamInteractor(private val teamRepository : ITeamRepository): TeamUseCase {
    override fun getAllTeam() = teamRepository.getAllTeam()

    override fun getFavoriteTeam()= teamRepository.getFavoriteTeam()

    override fun setFavoriteTeam(team: Team, state: Boolean) =teamRepository.setFavoriteTeam(team,state)
}