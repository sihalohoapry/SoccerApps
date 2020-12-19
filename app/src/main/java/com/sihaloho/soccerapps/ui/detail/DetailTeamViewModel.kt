package com.sihaloho.soccerapps.ui.detail

import androidx.lifecycle.ViewModel
import com.sihaloho.core.domain.model.Team
import com.sihaloho.core.domain.usecase.TeamUseCase

class DetailTeamViewModel(private val teamUseCase: TeamUseCase): ViewModel() {
    fun setFavoriteTeam(team : Team, newStatus : Boolean) =teamUseCase.setFavoriteTeam(team,newStatus)
}