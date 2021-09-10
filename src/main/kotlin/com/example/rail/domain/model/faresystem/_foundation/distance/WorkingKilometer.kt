package com.example.rail.domain.model.faresystem._foundation.distance

import java.math.BigDecimal

/**
 * 営業キロ
 *
 * @property value
 */
data class WorkingKilometer(

        private val value: BigDecimal
) {

    operator fun compareTo(other: WorkingKilometer) = value.compareTo(other.value)
}


val Int.営業キロ: WorkingKilometer
    get() = WorkingKilometer(this.toBigDecimal())

val Double.営業キロ: WorkingKilometer
    get() = WorkingKilometer(this.toBigDecimal())
