package com.sihaloho.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "team")
data class TeamEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "idTeam")
    var idTeam: String,

    @ColumnInfo(name = "nameTeam")
    var nameTeam: String,

    @ColumnInfo(name = "year")
    var year: String,

    @ColumnInfo(name = "stadium")
    var stadium: String,

    @ColumnInfo(name = "photoStadium")
    var photoStadium: String,

    @ColumnInfo(name = "location")
    var location: String,

    @ColumnInfo(name = "description")
    var description: String,

    @ColumnInfo(name = "logoTeam")
    var logoTeam: String,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false

)
