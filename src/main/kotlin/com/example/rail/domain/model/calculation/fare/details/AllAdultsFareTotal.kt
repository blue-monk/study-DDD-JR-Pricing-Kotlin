package com.example.rail.domain.model.calculation.fare.details

import com.example.rail.domain.model.faresystem._foundation.monetary.JpMoney
import com.example.rail.domain.model.faresystem._foundation.monetary.amount.TemporalAmount

data class AllAdultsFareTotal(

        private val jpMoney: JpMoney

) {

    operator fun plus(allChildrenFareTotal: AllChildrenFareTotal): TemporalAmount {
        return TemporalAmount(jpMoney + allChildrenFareTotal.jpMoney)
    }


    val displayString: String = jpMoney.displayString
}
