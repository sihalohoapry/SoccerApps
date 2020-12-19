package com.sihaloho.soccerapps.ui.team

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.sihaloho.core.domain.usecase.TeamUseCase

class TeamViewModel(teamUseCase: TeamUseCase) : ViewModel() {
    val team = teamUseCase.getAllTeam().asLiveData()

}