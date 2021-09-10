package com.example.rail.domain.model.faresystem.pricing.discount

import com.example.rail.domain.model.faresystem._foundation.datetime.period.NotionalPeriod
import com.example.rail.domain.model.faresystem._foundation.monetary.DiscountRate
import com.example.rail.domain.model.faresystem.factor.date.DepartureDate
import com.example.rail.domain.model.faresystem.factor.date.valueFor
import kotlinx.collections.immutable.ImmutableMap
import kotlinx.collections.immutable.toImmutableMap

/**
 * 団体個人別割引プライシング
 */
object GroupIndividualDiscountPricing {

    private val discountRateByPeriod = mapOf(

            NotionalPeriod(first = "01-01", last = "01-10") to DiscountRate(0.1),
            NotionalPeriod(first = "01-11", last = "12-20") to DiscountRate(0.15),
            NotionalPeriod(first = "12-21", last = "12-31") to DiscountRate(0.1),

    ).toImmutableMap()


    fun discountRateOn(departureDate: DepartureDate): DiscountRate {
        return discountRateByPeriod.discountRateFor(departureDate) ?: DiscountRate.ZERO
    }
}



fun ImmutableMap<NotionalPeriod, DiscountRate>.discountRateFor(departureDate: DepartureDate): DiscountRate? {
    return this.valueFor(departureDate, subject = "グループ個別割引価格定義")
}
