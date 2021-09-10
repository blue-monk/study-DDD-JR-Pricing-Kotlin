package com.example.rail.domain.model.faresystem.factor.route

import com.example.rail.domain.model.faresystem._foundation.monetary.JpMoney

/**
 * 行程タイプ
 *
 * @property displayName 表示名称
 */
enum class TripType(

        val displayName: String

) {

    OneWay("片道") {

        override fun applyTo(amount: JpMoney): JpMoney {
            return amount
        }
    },
    RoundTrip("往復") {

        override fun applyTo(amount: JpMoney): JpMoney {
            return amount * 2
        }
    },
    ;

    val isOneWay: Boolean
        get() = this == OneWay

    val isRoundTrip: Boolean
        get() = this == RoundTrip


    abstract fun applyTo(amount: JpMoney): JpMoney
}
