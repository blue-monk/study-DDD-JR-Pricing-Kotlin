package com.example.rail.domain.model.faresystem._foundation.monetary.amount

import com.example.rail.domain.model.faresystem._foundation.monetary.JpMoney
import com.example.rail.domain.model.faresystem._foundation.monetary.accumulation.Accumulatable

/**
 * 割引料金
 *
 * @property jpMoney
 */
data class DiscountAmount(

        override val jpMoney: JpMoney

) : AmountOfMoney {

    constructor(value: Int) : this(JpMoney(value))

    override val operation: Accumulatable.Operation
        get() = Accumulatable.Operation.Subtraction


    companion object {

        val ZERO = DiscountAmount(JpMoney.ZERO)
    }
}
