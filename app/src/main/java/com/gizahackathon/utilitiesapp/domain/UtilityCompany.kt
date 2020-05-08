package com.gizahackathon.utilitiesapp.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UtilityCompany(
    @ColumnInfo(name = "utility_company_id") @PrimaryKey(autoGenerate = true) val utilityCompanyId: Long = 0,

    @ColumnInfo(name = "company_name") val companyName: String,

    @ColumnInfo(name = "companyNumber") val companyNumber: Long
)