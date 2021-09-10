package com.example.rail.domain.model.faresystem.rule.basis

import com.example.rail.domain.model.faresystem._foundation.distance.営業キロ
import com.example.rail.domain.model.faresystem._foundation.monetary.DiscountRate
import com.example.rail.domain.model.faresystem._foundation.monetary.accumulation.AccumulatableAmount
import com.example.rail.domain.model.faresystem._foundation.monetary.amount.DiscountAmount
import com.example.rail.domain.model.faresystem.factor.route.Route
import com.example.rail.domain.model.faresystem.factor.route.RouteDistanceDef
import com.example.rail.domain.model.faresystem.factor.route.TripType

/**
 * 往復割引
 *
 * @property factor
 */
data class RoundTripDiscount(

        private val factor: Factor

) : AccumulatableAmount<DiscountAmount> {

    data class Factor(
            val route: Route,
            val tripType: TripType,
            val basicFare: BasicFare
    )


    companion object {

        fun withFactor(route: Route, tripType: TripType, basicFare: BasicFare): RoundTripDiscount {
            return RoundTripDiscount(Factor(route, tripType, basicFare))
        }

        private val DISCOUNTABLE_DISTANCE_BOUNDARY = 601.営業キロ
        private val DISCOUNT_RATE = DiscountRate(0.1)
    }


    override val amount: DiscountAmount by lazy {
        compute()
    }

    //BIZ-RULE: 片道の営業キロが601km以上あれば、「ゆき」と「かえり」の運賃がそれぞれ1割引になります
    private fun compute(): DiscountAmount {

        if (factor.tripType.isOneWay) {
            return DiscountAmount.ZERO
        }

        val workingKilometer = RouteDistanceDef.kilometerFor(factor.route)
        if (workingKilometer < DISCOUNTABLE_DISTANCE_BOUNDARY) {
            return DiscountAmount.ZERO
        }

        return factor.basicFare * DISCOUNT_RATE
    }

}
