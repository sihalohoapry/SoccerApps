package com.sihaloho.core.utils

import com.sihaloho.core.data.source.local.entity.TeamEntity
import com.sihaloho.core.data.source.remote.response.TeamResponse
import com.sihaloho.core.domain.model.Team

object DataMapper {
    fun mapResponsesToEntities(input: List<TeamResponse>): List<TeamEntity> {
        val teamList = ArrayList<TeamEntity>()
        input.map {
            val team = TeamEntity(
                idTeam = it.idTeam,
                nameTeam = it.nameTeam,
                year = it.year,
                stadium = it.stadium,
                photoStadium = it.photoStadium,
                location = it.location,
                description = it.description,
                logoTeam = it.logoTeam,
                isFavorite = false
            )
            teamList.add(team)
        }
        return teamList
    }

    fun mapEntitiesToDomain(input: List<TeamEntity>): List<Team> =
        input.map {
            Team(
                idTeam = it.idTeam,
                nameTeam = it.nameTeam,
                year = it.year,
                stadium = it.stadium,
                photoStadium = it.photoStadium,
                location = it.location,
                description = it.description,
                logoTeam = it.logoTeam,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Team) = TeamEntity(
        idTeam = input.idTeam,
        nameTeam = input.nameTeam,
        year = input.year,
        stadium = input.stadium,
        photoStadium = input.photoStadium,
        location = input.location,
        description = input.description,
        logoTeam = input.logoTeam,
        isFavorite = input.isFavorite
    )
}