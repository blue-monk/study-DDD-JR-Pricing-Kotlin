package com.example.rail.domain.model.faresystem.pricing.byRoute

import com.example.rail.domain.model.faresystem._foundation.monetary.amount.FareAmount
import com.example.rail.domain.model.faresystem.factor.route.Route
import com.example.rail.domain.model.faresystem.pricing.byRoute.integrated.PricingTable

/**
 * 普通運賃プライシング
 */
object BasicFarePricing {

    fun fareFor(route: Route): FareAmount {
        return PricingTable.basicFareFor(route)
    }
}
