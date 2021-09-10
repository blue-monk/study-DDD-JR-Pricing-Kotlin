package com.example.rail.domain.model.faresystem.pricing.byRoute

import com.example.rail.domain.model.faresystem._foundation.monetary.amount.FareAmount
import com.example.rail.domain.model.faresystem.factor.route.Route
import com.example.rail.domain.model.faresystem.factor.route.Station
import kotlinx.collections.immutable.toImmutableMap

/**
 * のぞみプレミアムチャージ料金プライシング
 */
object NozomiPremiumChargePricing {

    private val faresByRoute = mapOf(

            Route(Station.Tokyo,    Station.SinOsaka) to FareAmount(320),
            Route(Station.Tokyo,    Station.Himeji)   to FareAmount(530),
            Route(Station.SinOsaka, Station.Himeji)   to FareAmount(210),

    ).toImmutableMap()


    fun fareFor(route: Route): FareAmount {
        return faresByRoute[route] ?: faresByRoute[route.reversed()] ?: throw IllegalStateException("当該 route の定義がありません [route=$route]")

    }
}
