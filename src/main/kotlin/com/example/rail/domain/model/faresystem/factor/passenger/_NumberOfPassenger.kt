package com.example.rail.domain.model.faresystem.factor.passenger

/**
 * 乗客数
 *
 */
sealed interface NumberOfPassenger {

    val value: Int

    operator fun plus(other: NumberOfPassenger) = TemporalNumberOfPax(value + other.value)

    operator fun plus(other: Int) = TemporalNumberOfPax(value + other)

    operator fun Int.plus(other: NumberOfPassenger): Int = this + other.value

    operator fun compareTo(other: Int): Int = this.value.compareTo(other)
}
