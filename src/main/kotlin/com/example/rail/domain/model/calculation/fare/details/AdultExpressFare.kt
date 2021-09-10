package com.example.rail.domain.model.calculation.fare.details

import com.example.rail.domain.model.faresystem._foundation.monetary.JpMoney
import com.example.rail.domain.model.faresystem.factor.route.TripType

data class AdultExpressFare(

        val oneWayAmount: JpMoney,
        val tripType: TripType

) {

    val actualAmountWithTripType: JpMoney by lazy {
        tripType.applyTo(oneWayAmount)
    }
}
