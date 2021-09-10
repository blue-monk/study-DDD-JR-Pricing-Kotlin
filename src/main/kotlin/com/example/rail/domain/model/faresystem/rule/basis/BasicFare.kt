package com.example.rail.domain.model.faresystem.rule.basis

import com.example.rail.domain.model.faresystem._foundation.monetary.DiscountRate
import com.example.rail.domain.model.faresystem._foundation.monetary.accumulation.AccumulatableAmount
import com.example.rail.domain.model.faresystem._foundation.monetary.amount.DiscountAmount
import com.example.rail.domain.model.faresystem._foundation.monetary.amount.FareAmount
import com.example.rail.domain.model.faresystem.factor.route.Route
import com.example.rail.domain.model.faresystem.pricing.byRoute.BasicFarePricing

/**
 * 普通運賃
 *
 * @property factor
 */
data class BasicFare(

        private val factor: Factor

) : AccumulatableAmount<FareAmount> {

    data class Factor(
            val route: Route
    )


    companion object {

        fun withFactor(route: Route): BasicFare {
            return BasicFare(Factor(route))
        }
    }


    //BIZ-RULE: 普通運賃
    override val amount: FareAmount = BasicFarePricing.fareFor(factor.route)


    operator fun times(discountRate: DiscountRate): DiscountAmount = amount * discountRate
}
