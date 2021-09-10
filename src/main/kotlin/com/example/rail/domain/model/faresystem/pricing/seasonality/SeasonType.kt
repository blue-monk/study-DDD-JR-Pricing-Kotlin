package com.example.rail.domain.model.faresystem.pricing.seasonality

import com.example.rail.domain.model.faresystem._foundation.monetary.amount.FareAmount

/**
 * 季節タイプ
 *
 * @property seasonalityAmount
 * @property displayName
 */
enum class SeasonType(

        val seasonalityAmount: FareAmount,
        val displayName: String

) {

    Low(FareAmount(-200), "閑散期"),
    Regular(FareAmount.ZERO, "通常期"),
    High(FareAmount(200), "繁忙期"),
    ;
}
