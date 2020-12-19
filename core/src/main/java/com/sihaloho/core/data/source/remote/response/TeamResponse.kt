package com.sihaloho.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class TeamResponse(
    @field:SerializedName("idTeam")
    val idTeam: String,
    @field:SerializedName("strTeam")
    val nameTeam: String,
    @field:SerializedName("intFormedYear")
    val year: String,
    @field:SerializedName("strStadium")
    val stadium: String,
    @field:SerializedName("strStadiumThumb")
    val photoStadium: String,
    @field:SerializedName("strStadiumLocation")
    val location: String,
    @field:SerializedName("strDescriptionEN")
    val description: String,
    @field:SerializedName("strTeamBadge")
    val logoTeam: String,
    )
