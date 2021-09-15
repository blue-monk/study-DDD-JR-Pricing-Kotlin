package com.example.rail.domain.model.faresystem.rule.express

import com.example.rail.domain.model.faresystem._foundation.monetary.amount.FareAmount
import com.example.rail.domain.model.faresystem._foundation.monetary.accumulation.AccumulatableAmount
import com.example.rail.domain.model.faresystem.factor.route.Route
import com.example.rail.domain.model.faresystem.pricing.byRoute.LimitedExpressReservedSeatFarePricing

/**
 * 特急指定席料金
 *
 * 自由席の場合も適用する（自由席割引きは [LimitedExpressNonReservedSeatDiscount] で実施）．
 *
 * @property factor
 */
data class LimitedExpressReservedSeatFare(

        private val factor: Factor

) : AccumulatableAmount<FareAmount> {

    data class Factor(
            val route: Route
    )


    companion object {

        fun withFactor(route: Route): LimitedExpressReservedSeatFare {
            return LimitedExpressReservedSeatFare(Factor(route))
        }
    }


    //BIZ-RULE: 指定席特急料金
    override val amount = LimitedExpressReservedSeatFarePricing.fareFor(factor.route)
}
