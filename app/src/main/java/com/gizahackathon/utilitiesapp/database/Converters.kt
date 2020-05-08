package com.gizahackathon.utilitiesapp.database

import androidx.room.TypeConverter
import org.joda.time.DateTimeZone
import org.joda.time.LocalDate
import java.math.BigDecimal
import java.math.RoundingMode

class Converters {
    @TypeConverter
    fun bigDecimalToString(bigDecimal: BigDecimal?): String? =
        bigDecimal?.setScale(4, RoundingMode.UP)?.toString()

    @TypeConverter
    fun stringToBigDecimal(value: String?): BigDecimal? =
        if (value == null) null else BigDecimal(value)

    @TypeConverter
    fun localDateToLong(localDate: LocalDate?): Long? = localDate?.toDateTimeAtStartOfDay(
        DateTimeZone.UTC
    )?.millis

    @TypeConverter
    fun longToLocalDate(value: Long?): LocalDate? = if (value == null) null else LocalDate(value)
}