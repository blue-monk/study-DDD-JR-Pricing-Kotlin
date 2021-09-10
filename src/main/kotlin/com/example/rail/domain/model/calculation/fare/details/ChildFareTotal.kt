package com.example.rail.domain.model.calculation.fare.details

import com.example.rail.domain.model.faresystem._foundation.monetary.JpMoney
import com.example.rail.domain.model.faresystem.rule.pax.AppliedNumberOfChildren

data class ChildFareTotal(

        private val jpMoney: JpMoney

) {

    operator fun times(applicableNumberOfChildren: AppliedNumberOfChildren): AllChildrenFareTotal {
        return AllChildrenFareTotal(jpMoney * applicableNumberOfChildren.value)
    }

    val displayString: String = jpMoney.displayString
}
