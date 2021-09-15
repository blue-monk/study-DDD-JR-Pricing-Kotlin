package com.example.rail.domain.model.calculation.calculator

import com.example.rail.domain.model.calculation.fare.details.AdultExpressFare
import com.example.rail.domain.model.calculation.fare.details.ChildExpressFare
import com.example.rail.domain.model.faresystem._foundation.monetary.accumulation.accumulateAmount
import com.example.rail.domain.model.faresystem.factor.date.DepartureDate
import com.example.rail.domain.model.faresystem.factor.equipment.SeatType
import com.example.rail.domain.model.faresystem.factor.equipment.TrainType
import com.example.rail.domain.model.faresystem.factor.route.Route
import com.example.rail.domain.model.faresystem.factor.route.TripType
import com.example.rail.domain.model.faresystem.rule.express.LimitedExpressNonReservedSeatDiscount
import com.example.rail.domain.model.faresystem.rule.express.LimitedExpressReservedSeatFare
import com.example.rail.domain.model.faresystem.rule.express.LimitedExpressSurcharge
import com.example.rail.domain.model.faresystem.rule.seasonality.SeasonalAdjustmentFee

/**
 * 特急料金計算機
 *
 * ファクターから特急料金計算に必要な料金を導出し、
 * それらを累算して特急料金を算出する．
 *
 * @property factor ファクター
 */
data class ExpressFareCalculator(

        private val factor: Factor

) {

    data class Factor(
            val route: Route,
            val seatType: SeatType,
            val trainType: TrainType,
            val departureDate: DepartureDate,
            val tripType: TripType
    )


    companion object {

        fun withFactor(route: Route, seatType: SeatType, trainType: TrainType, departureDate: DepartureDate, tripType: TripType): ExpressFareCalculator {
            return ExpressFareCalculator(Factor(route, seatType, trainType, departureDate, tripType))
        }
    }


    fun calculate(): Pair<AdultExpressFare, ChildExpressFare> {

        val limitedExpressReservedSeatFare = LimitedExpressReservedSeatFare.withFactor(factor.route)
        val limitedExpressSurcharge = LimitedExpressSurcharge.withFactor(factor.route, factor.trainType)
        val limitedExpressDiscount = LimitedExpressNonReservedSeatDiscount.withFactor(factor.seatType)
        val seasonalAdjustmentFee = SeasonalAdjustmentFee.withFactor(factor.departureDate, factor.seatType)

        val (accumulatedAmount, discountTrails) = accumulateAmount {
            accumulate(limitedExpressReservedSeatFare)
            accumulate(limitedExpressSurcharge)
            accumulate(limitedExpressDiscount)
            accumulate(seasonalAdjustmentFee)
        }

        val adultExpressFare = AdultExpressFare(accumulatedAmount.roundedJpMoney, factor.tripType, discountTrails)
        val childExpressFare = ChildExpressFare.createFrom(adultExpressFare)

        return adultExpressFare to childExpressFare
    }
}
