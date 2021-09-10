package com.example.rail.domain.model.faresystem.pricing.seasonality

import com.example.rail.domain.model.faresystem._foundation.datetime.period.NotionalPeriod
import com.example.rail.domain.model.faresystem.factor.date.DepartureDate
import com.example.rail.domain.model.faresystem.factor.date.valueFor
import kotlinx.collections.immutable.ImmutableMap
import kotlinx.collections.immutable.toImmutableMap

/**
 * 季節タイプ定義
 */
object SeasonDef {

    private val seasonTypesByPeriod = mapOf(

            NotionalPeriod(first = "01-01", last = "01-10") to SeasonType.High,
            NotionalPeriod(first = "01-16", last = "01-30") to SeasonType.Low,
            NotionalPeriod(first = "12-25", last = "12-31") to SeasonType.High,

    ).toImmutableMap()


    fun seasonTypeOn(departureDate: DepartureDate): SeasonType {
        return seasonTypesByPeriod.seasonTypeFor(departureDate) ?: SeasonType.Regular
    }
}



fun ImmutableMap<NotionalPeriod, SeasonType>.seasonTypeFor(departureDate: DepartureDate): SeasonType? {
    return this.valueFor(departureDate, subject = "シーズン定義")
}
