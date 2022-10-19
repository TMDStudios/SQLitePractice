package com.example.sqlitepractice.data.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.sqlitepractice.data.City
import com.example.sqlitepractice.data.Country

data class CountryWithCities(
    @Embedded val country: Country,
    @Relation(
        parentColumn = "id",
        entityColumn = "countryId"
    )
    val cities: List<City>
)