package com.gizahackathon.utilitiesapp.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = UtilityAccount::class,
        parentColumns = arrayOf("utility_account_id"),
        childColumns = arrayOf("utility_account_id")
    )]
)
data class UtilityAccountNumber(
    @ColumnInfo(name = "utility_account_number_id") @PrimaryKey(autoGenerate = true) val utilityAccountNumberId: Long = 0,

    @ColumnInfo(name = "utility_account_id", index = true) val utilityAccountId: Long,

    @ColumnInfo(name = "account_number") val accountNumber: Long
)