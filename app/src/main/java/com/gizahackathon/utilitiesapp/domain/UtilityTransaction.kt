package com.gizahackathon.utilitiesapp.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import org.joda.time.LocalDate
import java.math.BigDecimal

@Entity(
    foreignKeys = [ForeignKey(
        entity = UtilityAccount::class,
        parentColumns = arrayOf("utility_account_id"),
        childColumns = arrayOf("utility_account_id")
    )]
)
data class UtilityTransaction(

    @ColumnInfo(name = "transaction_id") @PrimaryKey(autoGenerate = true) val transactionId: Long = 0,

    @ColumnInfo(name = "utility_account_id") val utilityAccountId: Long,

    @ColumnInfo(name = "transaction_status") val transactionStatus: String,

    @ColumnInfo(name = "transaction_amount") val transactionAmount: BigDecimal,

    @ColumnInfo(name = "created_date") val createdDate: LocalDate,

    @ColumnInfo(name = "completed_date") val completedDate: LocalDate
)