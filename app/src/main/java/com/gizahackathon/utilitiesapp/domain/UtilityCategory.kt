package com.gizahackathon.utilitiesapp.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UtilityCategory(
    @ColumnInfo(name = "utility_category_id") @PrimaryKey(autoGenerate = true) val utilityCategoryId: Long = 0,

    @ColumnInfo(name = "utility_name") val utilityName: String
)