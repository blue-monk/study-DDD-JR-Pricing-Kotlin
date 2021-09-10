package com.example.rail.domain.model.faresystem._foundation.monetary.amount

import com.example.rail.domain.model.faresystem._foundation.monetary.JpMoney
import com.example.rail.domain.model.faresystem._foundation.monetary.accumulation.Accumulatable

/**
 * 一時的料金
 *
 * @property jpMoney
 */
data class TemporalAmount(

        override val jpMoney: JpMoney

) : AmountOfMoney {

    override val operation: Accumulatable.Operation
        get() = Accumulatable.Operation.Addition


    companion object {

        val ZERO: TemporalAmount = TemporalAmount(JpMoney.ZERO)
    }


    val roundedJpMoney: JpMoney
        get() = jpMoney.truncatedInUnitsOf10yen()
}
