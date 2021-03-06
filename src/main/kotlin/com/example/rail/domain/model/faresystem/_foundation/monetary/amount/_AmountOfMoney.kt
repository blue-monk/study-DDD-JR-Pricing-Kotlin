package com.example.rail.domain.model.faresystem._foundation.monetary.amount

import com.example.rail.domain.model.faresystem._foundation.monetary.JpMoney
import com.example.rail.domain.model.faresystem._foundation.monetary.accumulation.Accumulatable


/**
 * 料金
 *
 */
sealed interface AmountOfMoney : Accumulatable {

    val jpMoney: JpMoney



    operator fun plus(other: AmountOfMoney) = TemporalAmount(jpMoney + other.jpMoney)

    operator fun minus(other: AmountOfMoney) = TemporalAmount(jpMoney - other.jpMoney)

    operator fun compareTo(other: AmountOfMoney) = jpMoney.compareTo(other.jpMoney)
}
