package com.example.rail.domain.model.calculation

import com.example.rail.domain.model.calculation.calculator.BasicFareCalculator
import com.example.rail.domain.model.calculation.calculator.ExpressFareCalculator
import com.example.rail.domain.model.faresystem.factor.date.DepartureDate
import com.example.rail.domain.model.faresystem.factor.equipment.SeatType
import com.example.rail.domain.model.faresystem.factor.equipment.TrainType
import com.example.rail.domain.model.faresystem.factor.passenger.NumberOfPax
import com.example.rail.domain.model.faresystem.factor.route.Route
import com.example.rail.domain.model.faresystem.factor.route.TripType

/**
 * 料金リクエスト
 *
 * @property route ルート
 * @property seatType 座席タイプ
 * @property trainType 列車タイプ
 * @property departureDate 出発日
 * @property numberOfPax 乗客数
 * @property tripType 行程タイプ
 */
data class FareRequest(

        val route: Route,
        val seatType: SeatType,
        val trainType: TrainType,
        val departureDate: DepartureDate,
        val numberOfPax: NumberOfPax,
        val tripType: TripType

) {

    fun makeFareSeed(): FareSeed {

        val basicFareCalculator = BasicFareCalculator.withFactor(route, departureDate, numberOfPax, tripType)
        val expressFareCalculator = ExpressFareCalculator.withFactor(route, seatType, trainType, departureDate, tripType)

        return FareSeed(this, basicFareCalculator, expressFareCalculator)
    }
}
