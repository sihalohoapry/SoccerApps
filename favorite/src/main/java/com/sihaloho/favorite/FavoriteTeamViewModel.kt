package com.sihaloho.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.sihaloho.core.domain.usecase.TeamUseCase

class FavoriteTeamViewModel (teamUseCase: TeamUseCase) : ViewModel() {
    val favoriteTeam = teamUseCase.getFavoriteTeam().asLiveData()
}