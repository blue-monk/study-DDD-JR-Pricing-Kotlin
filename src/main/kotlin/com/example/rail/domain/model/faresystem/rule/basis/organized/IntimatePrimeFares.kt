package com.example.rail.domain.model.faresystem.rule.basis.organized

import com.example.rail.domain.model.faresystem.factor.route.Route
import com.example.rail.domain.model.faresystem.factor.route.TripType
import com.example.rail.domain.model.faresystem.rule.basis.BasicFare
import com.example.rail.domain.model.faresystem.rule.basis.RoundTripDiscount

/**
 * 関連性主要料金群
 *
 * ファクターを共にし、また、相手方に依存したりもする主要料金群．
 *
 * @property factor
 */
data class IntimatePrimeFares(

        private val factor: Factor

) {

    companion object Factory {

        fun withFactor(route: Route, tripType: TripType): IntimatePrimeFares {
            return IntimatePrimeFares(Factor(route, tripType))
        }
    }


    data class Factor(
            val route: Route,
            val tripType: TripType
    )

    val basicFare: BasicFare = BasicFare.withFactor(factor.route)

    val roundTripDiscount: RoundTripDiscount = RoundTripDiscount.withFactor(factor.route, factor.tripType, basicFare)
}
