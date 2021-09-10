package com.example.rail.domain.model.faresystem._foundation.monetary.amount

import com.example.rail.domain.model.faresystem._foundation.monetary.DiscountRate
import com.example.rail.domain.model.faresystem._foundation.monetary.JpMoney
import com.example.rail.domain.model.faresystem._foundation.monetary.accumulation.Accumulatable

/**
 * 運賃
 *
 * @property jpMoney
 */
data class FareAmount(

        override val jpMoney: JpMoney

) : AmountOfMoney {

    constructor(value: Int) : this(JpMoney(value))

    override val operation: Accumulatable.Operation
        get() = Accumulatable.Operation.Addition

    val isZero: Boolean = jpMoney.isZero


    companion object {

        val ZERO = FareAmount(JpMoney.ZERO)
    }


    operator fun times(discountRate: DiscountRate) = DiscountAmount(discountRate * jpMoney)
}
