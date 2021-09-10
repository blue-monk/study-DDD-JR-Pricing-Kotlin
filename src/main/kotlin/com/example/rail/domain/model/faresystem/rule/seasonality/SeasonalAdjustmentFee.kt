package com.example.rail.domain.model.faresystem.rule.seasonality

import com.example.rail.domain.model.faresystem.pricing.seasonality.SeasonType
import com.example.rail.domain.model.faresystem._foundation.monetary.amount.FareAmount
import com.example.rail.domain.model.faresystem._foundation.monetary.accumulation.AccumulatableAmount
import com.example.rail.domain.model.faresystem.factor.date.DepartureDate
import com.example.rail.domain.model.faresystem.factor.equipment.SeatType
import com.example.rail.domain.model.faresystem.pricing.seasonality.SeasonDef

/**
 * 季節性調整料金
 *
 * @property factor
 */
data class SeasonalAdjustmentFee(

        private val factor: Factor

) : AccumulatableAmount<FareAmount> {

    data class Factor(
            val departureDate: DepartureDate,
            val seatType: SeatType
    )


    companion object {

        fun withFactor(departureDate: DepartureDate, seatType: SeatType): SeasonalAdjustmentFee {
            return SeasonalAdjustmentFee(Factor(departureDate, seatType))
        }
    }


    private val seasonType: SeasonType = SeasonDef.seasonTypeOn(factor.departureDate)

    //BIZ-RULE: 季節(season)による特急指定席料金の変動
    //          通常期の指定席特急券に対して、季節タイプに応じて金額を調整する （負値あり）
    override val amount: FareAmount by lazy {

        when (factor.seatType) {
            SeatType.NonReserved -> FareAmount.ZERO
            SeatType.Reserved    -> seasonType.seasonalityAmount
        }
    }
}
