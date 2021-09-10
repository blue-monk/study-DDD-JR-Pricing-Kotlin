package com.example.rail.domain.model.faresystem.factor.route

/**
 * ルート
 *
 * @property from 出発駅
 * @property to 到着駅
 */
data class Route(

        val from: Station,
        val to: Station

) {

    init {
        check(from != to) { throw IllegalArgumentException("出発駅 と 到着駅 が同じ [from=$from, to=$to]") }
    }


    fun reversed(): Route {
        return Route(from = to, to = from)
    }
}
