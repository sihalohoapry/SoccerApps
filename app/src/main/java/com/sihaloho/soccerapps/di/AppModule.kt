package com.sihaloho.soccerapps.di

import com.sihaloho.core.domain.usecase.TeamInteractor
import com.sihaloho.core.domain.usecase.TeamUseCase
import com.sihaloho.soccerapps.ui.detail.DetailTeamViewModel
import com.sihaloho.soccerapps.ui.team.TeamViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<TeamUseCase> { TeamInteractor(get()) }
}

val viewModelModule = module {
    viewModel { TeamViewModel(get()) }
    viewModel { DetailTeamViewModel(get()) }
}