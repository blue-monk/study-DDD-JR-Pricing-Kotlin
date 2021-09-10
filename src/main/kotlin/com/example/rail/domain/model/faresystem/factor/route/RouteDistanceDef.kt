package com.example.rail.domain.model.faresystem.factor.route

import com.example.rail.domain.model.faresystem._foundation.distance.WorkingKilometer
import com.example.rail.domain.model.faresystem._foundation.distance.営業キロ
import kotlinx.collections.immutable.toImmutableMap

/**
 * ルート距離定義
 */
object RouteDistanceDef {

    private val valuesByRoute = mapOf(

            Route(Station.Tokyo, Station.SinOsaka)  to 553.0.営業キロ,
            Route(Station.Tokyo, Station.Himeji)    to 644.0.営業キロ,
            Route(Station.SinOsaka, Station.Himeji) to  87.9.営業キロ,

    ).toImmutableMap()


    fun kilometerFor(route: Route): WorkingKilometer {
        return valuesByRoute[route] ?: valuesByRoute[route.reversed()] ?: throw IllegalStateException("当該 route の定義がありません [route=$route]")
    }
}
