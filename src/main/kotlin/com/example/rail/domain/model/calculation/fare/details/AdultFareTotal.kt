package com.example.rail.domain.model.calculation.fare.details

import com.example.rail.domain.model.faresystem._foundation.monetary.JpMoney
import com.example.rail.domain.model.faresystem.rule.pax.AppliedNumberOfAdults

data class AdultFareTotal(

        private val jpMoney: JpMoney

) {

    operator fun times(appliedNumberOfAdults: AppliedNumberOfAdults): AllAdultsFareTotal {
        return AllAdultsFareTotal(jpMoney * appliedNumberOfAdults.value)
    }


    val displayString: String = jpMoney.displayString
}
