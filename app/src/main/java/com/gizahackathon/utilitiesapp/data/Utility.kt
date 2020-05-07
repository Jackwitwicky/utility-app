package com.gizahackathon.utilitiesapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "utility_table")
data class Utility(@PrimaryKey(autoGenerate = true) @ColumnInfo val id: Int,
                   @ColumnInfo var name: String,
                   @ColumnInfo var utilityType: String)