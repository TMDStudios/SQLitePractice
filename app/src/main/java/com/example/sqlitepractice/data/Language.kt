package com.example.sqlitepractice.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="languages")
data class Language(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val countryCode: String,
    val language: String,
    val isOfficial: Boolean,
    val percentage: Float?,
    val countryId: Int?
)