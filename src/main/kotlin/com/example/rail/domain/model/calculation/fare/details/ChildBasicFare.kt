package com.example.rail.domain.model.calculation.fare.details

import com.example.rail.domain.model.faresystem._foundation.monetary.JpMoney
import com.example.rail.domain.model.faresystem._foundation.monetary.trail.DiscountTrails
import com.example.rail.domain.model.faresystem.factor.route.TripType

data class ChildBasicFare(

        private val oneWayAmount: JpMoney,
        private val tripType: TripType,

        val discountTrails: DiscountTrails

) {


    companion object {

        fun createFrom(adultBasicFare: AdultBasicFare): ChildBasicFare {

            //BIZ-RULE: 運賃、特急料金ともに、こどもは半額
            return ChildBasicFare(oneWayAmount = adultBasicFare.oneWayAmount.halfAmount(), adultBasicFare.tripType, adultBasicFare.discountTrails)
        }
    }


    val actualAmountWithTripType: JpMoney by lazy {
        tripType.applyTo(oneWayAmount)
    }

    val trailsDescription: String = discountTrails.joinedDescription()


    operator fun plus(childExpressFare: ChildExpressFare): ChildFareTotal {
        return ChildFareTotal(actualAmountWithTripType + childExpressFare.actualAmountWithTripType)
    }
}
