package com.example.sqlitepractice.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="cities")
data class City(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String?,
    val countryCode: String?,
    val district: String?,
    val population: Int?,
    val countryId: Int?
)

data class Query1(
    val name: String,
    val population: Int,
    val surfaceArea: Float
)