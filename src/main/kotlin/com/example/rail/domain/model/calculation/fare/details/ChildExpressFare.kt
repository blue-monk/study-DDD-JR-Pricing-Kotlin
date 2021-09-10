package com.example.rail.domain.model.calculation.fare.details

import com.example.rail.domain.model.faresystem._foundation.monetary.JpMoney
import com.example.rail.domain.model.faresystem.factor.route.TripType

data class ChildExpressFare(

        private val oneWayAmount: JpMoney,
        private val tripType: TripType

) {


    companion object {

        fun createFrom(adultExpressFare: AdultExpressFare): ChildExpressFare {

            //BIZ-RULE: 運賃、特急料金ともに、こどもは半額
            return ChildExpressFare(oneWayAmount = adultExpressFare.oneWayAmount.halfAmount(), adultExpressFare.tripType)
        }
    }


    val actualAmountWithTripType: JpMoney by lazy {
        tripType.applyTo(oneWayAmount)
    }
}
