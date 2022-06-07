package com.example.a7_minuteworkout

import androidx.room.Entity
import androidx.room.PrimaryKey

//in order to use room database, add dependencies to build gradle first
@Entity(tableName = "History-table")
data class HistoryEntity (
    @PrimaryKey
    val date: String
)