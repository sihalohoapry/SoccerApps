package com.sihaloho.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Team(
    val idTeam: String,
    val nameTeam: String,
    val year: String,
    val stadium: String,
    val photoStadium: String,
    val location: String,
    val description: String,
    val logoTeam: String,
    val isFavorite: Boolean = false
) : Parcelable
