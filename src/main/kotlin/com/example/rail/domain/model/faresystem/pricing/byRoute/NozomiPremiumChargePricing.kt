package com.example.rail.domain.model.faresystem.pricing.byRoute

import com.example.rail.domain.model.faresystem._foundation.monetary.amount.FareAmount
import com.example.rail.domain.model.faresystem.factor.route.Route
import com.example.rail.domain.model.faresystem.pricing.byRoute.integrated.PricingTable

/**
 * のぞみプレミアムチャージ料金プライシング
 */
object NozomiPremiumChargePricing {

    fun fareFor(route: Route): FareAmount {
        return PricingTable.nozomiPremiumChargeFareFor(route)
    }
}
