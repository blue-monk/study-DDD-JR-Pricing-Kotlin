package com.example.rail.domain.model.faresystem._foundation.monetary

import java.math.BigDecimal

/**
 * 割引率
 *
 * @property value
 */
data class DiscountRate private constructor (

        private val value: BigDecimal

) {

    constructor(int: Int) : this(BigDecimal("$int"))
    constructor(long: Long) : this(BigDecimal("$long"))
    constructor(float: Float) : this(BigDecimal("$float"))
    constructor(double: Double) : this(BigDecimal("$double"))


    companion object {

        val ZERO = DiscountRate(0)
    }


    operator fun times(jpMoney: JpMoney) = (jpMoney * value).truncatedInUnitsOf10yen()
}
