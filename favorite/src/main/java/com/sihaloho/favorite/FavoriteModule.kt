package com.sihaloho.favorite

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteTeamModule = module {
    viewModel { FavoriteTeamViewModel(get()) }
}