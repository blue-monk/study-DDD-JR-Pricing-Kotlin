package com.example.rail.domain.model.faresystem.factor.passenger

/**
 * 一時的乗客数
 *
 * @property value
 */
data class TemporalNumberOfPax(

        override val value: Int

) : NumberOfPassenger {

    operator fun div(other: Int) = TemporalNumberOfPax(value / other)
}
