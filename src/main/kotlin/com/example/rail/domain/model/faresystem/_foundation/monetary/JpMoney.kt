package com.example.rail.domain.model.faresystem._foundation.monetary

import com.example.rail.domain.model.faresystem._foundation.monetary.format.MonetaryFormatter
import java.math.BigDecimal
import java.math.RoundingMode

/**
 * 日本円
 *
 * @property value
 */
data class JpMoney private constructor(

        private val value: BigDecimal

) {

    constructor(int: Int) : this(BigDecimal("$int"))
    constructor(long: Long) : this(BigDecimal("$long"))
    constructor(float: Float) : this(BigDecimal("$float"))
    constructor(double: Double) : this(BigDecimal("$double"))


    companion object {

        val ZERO: JpMoney = JpMoney(BigDecimal.ZERO)

        //BIZ-RULE: 10円単位に切り捨てる
        private const val SCALE_for_10YEN_UNIT = -1
        private val ROUNDING_MODE = RoundingMode.DOWN

        private fun truncatedInUnitsOf10yen(value: BigDecimal): JpMoney {
            return JpMoney(value.setScale(SCALE_for_10YEN_UNIT, ROUNDING_MODE).toInt())
        }
    }


    val isZero: Boolean = value == BigDecimal.ZERO

    val sign: Sign = Sign.of(value.signum())


    fun truncatedInUnitsOf10yen(): JpMoney {
        return truncatedInUnitsOf10yen(value)
    }

    fun halfAmount(): JpMoney {
        //BIZ-RULE: 5円の端数は運賃、特急料金それぞれで切り捨てます
        return (this * JpMoney(0.5).value).truncatedInUnitsOf10yen()
    }



    operator fun times(otherValue: BigDecimal) = truncatedInUnitsOf10yen(value * otherValue)

    operator fun times(otherValue: Int) = truncatedInUnitsOf10yen(value * otherValue.toBigDecimal())

    operator fun plus(other: JpMoney) = JpMoney(value + other.value)

    operator fun minus(other: JpMoney) = JpMoney(value - other.value)

    operator fun compareTo(other: JpMoney) = value.compareTo(other.value)



    val displayString: String = MonetaryFormatter.monetaryFormatter.format(this.value)

    val displayStringAsAbsValue: String = MonetaryFormatter.monetaryFormatter.format(value.abs())
}
