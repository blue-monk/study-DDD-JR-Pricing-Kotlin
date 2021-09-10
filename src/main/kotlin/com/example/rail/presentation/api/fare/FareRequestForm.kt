package com.example.rail.presentation.api.fare

import com.example.rail.domain.model.calculation.FareRequest
import com.example.rail.domain.model.faresystem.factor.date.DepartureDate
import com.example.rail.domain.model.faresystem.factor.equipment.SeatType
import com.example.rail.domain.model.faresystem.factor.equipment.TrainType
import com.example.rail.domain.model.faresystem.factor.passenger.NumberOfAdults
import com.example.rail.domain.model.faresystem.factor.passenger.NumberOfChildren
import com.example.rail.domain.model.faresystem.factor.passenger.NumberOfPax
import com.example.rail.domain.model.faresystem.factor.route.Route
import com.example.rail.domain.model.faresystem.factor.route.Station
import com.example.rail.domain.model.faresystem.factor.route.TripType
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate


@ApiModel(description = "料金計算リクエスト")
//@Schema(description = "料金計算リクエスト")
data class FareRequestForm(

        @ApiModelProperty(value = "大人人数", example = "1", position = 1, required = true)
        val numberOfAdult: Int,

        @ApiModelProperty(value = "小人人数", example = "1", position = 2, required = true)
        val numberOfChildren: Int,

        @ApiModelProperty(value = "出発地", example = "Tokyo", position = 3, required = true)
        val departureStation: Station,

        @ApiModelProperty(value = "目的地", example = "SinOsaka", position = 4, required = true)
        val arrivalStation: Station,

        @ApiModelProperty(value = "出発日", example = "2021-02-15", position = 5, required = true)
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        val departureDate: LocalDate,

        @ApiModelProperty(value = "座席区分", example = "Reserved", position = 6, required = true)
        val seatType: SeatType,

        @ApiModelProperty(value = "列車種類", example = "Hikari", position = 7, required = true)
        val trainType: TrainType,

        @ApiModelProperty(value = "OneWay/RoundTrip", example = "OneWay", position = 8, required = true)
        val ticketType: TripType

) {

    fun createFareRequest(): FareRequest {

        return FareRequest(
                Route(departureStation, arrivalStation),
                seatType,
                trainType,
                DepartureDate(departureDate),
                NumberOfPax(NumberOfAdults(numberOfAdult), NumberOfChildren(numberOfChildren)),
                ticketType
        )
    }
}
