package com.example.rail.domain.model.faresystem.factor.passenger

/**
 * 大人人数
 *
 * @property value
 */
data class NumberOfAdults(

        override val value: Int

) : NumberOfPassenger {

    init {
        if (value < 0) throw IllegalArgumentException("大人人数は正数のみ可")
    }
}
