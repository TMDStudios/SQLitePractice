package com.example.sqlitepractice.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="countries")
data class Country(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val code: String,
    val name: String,
    val continent: String,
    val region: String,
    val surfaceArea: Float,
    val indepYear: Int,
    val population: Int,
    val lifeExpectancy: Float,
    val gnp: Float,
    val gnpOld: Float,
    val localName: String,
    val governmentForm: String,
    val headOfState: String,
    val capital: Int,
    val code2: String
)