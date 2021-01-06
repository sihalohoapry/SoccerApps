package com.sihaloho.soccerapps.maps

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.sihaloho.core.domain.usecase.TeamUseCase

class MapsViewModel(teamUseCase: TeamUseCase) : ViewModel() {
    val team = teamUseCase.getAllTeam().asLiveData()
}