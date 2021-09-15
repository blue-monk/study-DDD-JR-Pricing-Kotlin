package com.example.rail.domain.model.calculation.fare.details

import com.example.rail.domain.model.faresystem._foundation.monetary.JpMoney
import com.example.rail.domain.model.faresystem._foundation.monetary.trail.DiscountTrails
import com.example.rail.domain.model.faresystem.factor.route.TripType

data class AdultBasicFare(

        val oneWayAmount: JpMoney,
        val tripType: TripType,

        val discountTrails: DiscountTrails

) {

    val actualAmountWithTripType: JpMoney by lazy {
        tripType.applyTo(oneWayAmount)
    }

    val trailsDescription: String = discountTrails.joinedDescription()


    operator fun plus(adultExpressFare: AdultExpressFare): AdultFareTotal {
        return AdultFareTotal(actualAmountWithTripType + adultExpressFare.actualAmountWithTripType)
    }
}
