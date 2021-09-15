package com.example.rail.domain.model.faresystem._foundation.monetary

import com.example.rail.domain._policy.format.fabric.RateNotation
import com.example.rail.domain.model.faresystem._foundation.monetary.format.MonetaryFormatter
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


    private val sign: Sign = value.sign()

    val signString: String = sign.signString

    fun description(): String {

        return MonetaryFormatter.rateFormatter.format { rateNotation ->
            when (rateNotation) {
                RateNotation.None                                          -> value
                RateNotation.Percentage, RateNotation.PercentageWideLetter -> percentage.value
            }
        }
    }

    private val percentage: Percentage
        get() = Percentage(value.scaleByPowerOfTen(2))


    operator fun times(jpMoney: JpMoney) = (jpMoney * value).truncatedInUnitsOf10yen()
}
