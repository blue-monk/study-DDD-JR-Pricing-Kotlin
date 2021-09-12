package com.example.rail.domain.model.faresystem.pricing.byRoute

import com.example.rail.domain.model.faresystem._foundation.monetary.amount.FareAmount
import com.example.rail.domain.model.faresystem.factor.route.Route
import com.example.rail.domain.model.faresystem.factor.route.Station
import kotlinx.collections.immutable.toImmutableMap

private typealias B = FaresForRoute.BasicFare
private typealias L = FaresForRoute.LimitedExpressReservedSeatFare
private typealias N = FaresForRoute.NozomiPremiumChargeFare

/**
 * プライシングテーブル
 *
 * ルート別の価格設定
 *
 * - 普通運賃
 * - 特急指定席料金
 * - のぞみプレミアムチャージ料金
 */
private object PricingTable {

    private val faresByRoute = mapOf(

            Route(Station.Tokyo,    Station.SinOsaka) to FaresForRoute(B( 8_910), L(5_490), N(320)),
            Route(Station.Tokyo,    Station.Himeji)   to FaresForRoute(B(10_010), L(5_920), N(530)),
            Route(Station.SinOsaka, Station.Himeji)   to FaresForRoute(B( 1_520), L(2_290), N(210)),

    ).toImmutableMap()


    private fun faresFor(route: Route): FaresForRoute {
        return faresByRoute[route] ?: faresByRoute[route.reversed()] ?: throw IllegalStateException("当該 route の定義がありません [route=$route]")
    }


    fun basicFareFor(route: Route): FareAmount {
        return faresFor(route).basicFare.fareAmount
    }

    fun limitedExpressReservedSeatFareFor(route: Route): FareAmount {
        return faresFor(route).limitedExpressReservedSeatFare.fareAmount
    }

    fun nozomiPremiumChargeFareFor(route: Route): FareAmount {
        return faresFor(route).nozomiPremiumChargeFare.fareAmount
    }
}

private data class FaresForRoute(

        val basicFare: BasicFare,
        val limitedExpressReservedSeatFare: LimitedExpressReservedSeatFare,
        val nozomiPremiumChargeFare: NozomiPremiumChargeFare
) {

    data class BasicFare(val fareAmount: FareAmount) {
        constructor(amount: Int) : this(FareAmount(amount))
    }

    data class LimitedExpressReservedSeatFare(val fareAmount: FareAmount) {
        constructor(amount: Int) : this(FareAmount(amount))
    }

    data class NozomiPremiumChargeFare(val fareAmount: FareAmount) {
        constructor(amount: Int) : this(FareAmount(amount))
    }
}



/**
 * 普通運賃プライシング
 */
object BasicFarePricing {

    fun fareFor(route: Route): FareAmount {
        return PricingTable.basicFareFor(route)
    }
}

/**
 * 特急指定席料金プライシング
 */
object LimitedExpressReservedSeatFarePricing {

    fun fareFor(route: Route): FareAmount {
        return PricingTable.limitedExpressReservedSeatFareFor(route)
    }
}

/**
 * のぞみプレミアムチャージ料金プライシング
 */
object NozomiPremiumChargePricing {

    fun fareFor(route: Route): FareAmount {
        return PricingTable.nozomiPremiumChargeFareFor(route)
    }
}
