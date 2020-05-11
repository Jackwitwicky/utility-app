package com.gizahackathon.utilitiesapp.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.math.BigDecimal

@Entity(
    foreignKeys = [ForeignKey(
        entity = UtilityCategory::class,
        parentColumns = arrayOf("utility_category_id"),
        childColumns = arrayOf("utility_category_id")
    ),
        ForeignKey(
            entity = UtilityCompany::class,
            parentColumns = arrayOf("utility_company_id"),
            childColumns = arrayOf("utility_company_id")
        )]
)
data class UtilityAccount(
    @ColumnInfo(name = "utility_account_id") @PrimaryKey(autoGenerate = true) val utilityAccountId: Long = 0,

    @ColumnInfo(name = "utility_category_id", index = true) val utilityCategoryId: Long,

    @ColumnInfo(name = "utility_company_id", index = true) val utilityCompanyId: Long,

    @ColumnInfo(name = "account_name") val accountName: String,

    @ColumnInfo(name = "amount") val amount: BigDecimal
)