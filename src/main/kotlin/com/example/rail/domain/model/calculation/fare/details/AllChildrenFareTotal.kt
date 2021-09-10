package com.example.rail.domain.model.calculation.fare.details

import com.example.rail.domain.model.faresystem._foundation.monetary.JpMoney

data class AllChildrenFareTotal(

        val jpMoney: JpMoney

) {

    val displayString: String = jpMoney.displayString
}
