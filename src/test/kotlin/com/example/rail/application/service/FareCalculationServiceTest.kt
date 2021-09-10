package com.example.rail.application.service

import com.example.rail._init.AppInitializer
import com.example.rail.domain.model.calculation.FareRequest
import com.example.rail.domain.model.calculation.payment.TotalPaymentAmount
import com.example.rail.domain.model.faresystem.factor.date.DepartureDate
import com.example.rail.domain.model.faresystem.factor.equipment.SeatType
import com.example.rail.domain.model.faresystem.factor.equipment.TrainType
import com.example.rail.domain.model.faresystem.factor.passenger.NumberOfAdults
import com.example.rail.domain.model.faresystem.factor.passenger.NumberOfChildren
import com.example.rail.domain.model.faresystem.factor.passenger.NumberOfPax
import com.example.rail.domain.model.faresystem.factor.route.Route
import com.example.rail.domain.model.faresystem.factor.route.Station
import com.example.rail.domain.model.faresystem.factor.route.TripType
import com.example.rail.domain.model.faresystem._foundation.monetary.JpMoney
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.assertThrows


data class Case(
        val caseId: String,
        val route: Route,
        val seatType: SeatType,
        val trainType: TrainType,
        val departureDate: DepartureDate,
        val numberOfAdults: NumberOfAdults,
        val numberOfChildren: NumberOfChildren,
        val tripType: TripType
) {

    fun makeFareRequest(): FareRequest {
        return FareRequest(
                route,
                seatType,
                trainType,
                departureDate,
                NumberOfPax(numberOfAdults, numberOfChildren),
                tripType
        )
    }
}

data class Expected(val fareAmount: Int) {

    fun totalPaymentAmount() = TotalPaymentAmount(JpMoney(fareAmount))
}



typealias R = Route
typealias S = Station
typealias Seat = SeatType
typealias T = TrainType
typealias Trip = TripType
typealias DDate = DepartureDate
typealias A = NumberOfAdults
typealias C = NumberOfChildren



class FareCalculationServiceTest : FreeSpec({

    beforeSpec {
        AppInitializer.initialize()
    }

    beforeTest {
    }

    afterTest { (testCase, result) ->
    }


    "FareCalculationService のテスト" - {

        val sut = FareCalculationService

        "正常系" - {

            listOf(
                    Case("CASE-01", R(S.Tokyo,    S.Himeji),   Seat.Reserved,    T.Hikari, DDate("2022-01-11"), A(  0), C(  8), Trip.RoundTrip) to Expected(  107_360),
                    Case("CASE-02", R(S.Tokyo,    S.Himeji),   Seat.Reserved,    T.Hikari, DDate("2022-01-15"), A(  0), C( 31), Trip.OneWay)    to Expected(  238_800),
                    Case("CASE-03", R(S.Tokyo,    S.Himeji),   Seat.Reserved,    T.Hikari, DDate("2022-01-13"), A(  1), C( 50), Trip.RoundTrip) to Expected(  731_080),
                    Case("CASE-04", R(S.Tokyo,    S.Himeji),   Seat.Reserved,    T.Nozomi, DDate("2022-01-16"), A(  1), C(  1), Trip.RoundTrip) to Expected(   45_760),
                    Case("CASE-05", R(S.Tokyo,    S.Himeji),   Seat.NonReserved, T.Hikari, DDate("2022-12-25"), A(  2), C(  6), Trip.RoundTrip) to Expected(  133_880),
                    Case("CASE-06", R(S.Tokyo,    S.Himeji),   Seat.NonReserved, T.Hikari, DDate("2023-01-10"), A(151), C(  0), Trip.OneWay)    to Expected(2_263_800),
                    Case("CASE-07", R(S.Tokyo,    S.SinOsaka), Seat.NonReserved, T.Hikari, DDate("2022-12-21"), A(  0), C( 51), Trip.OneWay)    to Expected(  339_570),
                    Case("CASE-08", R(S.Tokyo,    S.SinOsaka), Seat.Reserved,    T.Nozomi, DDate("2022-12-24"), A(101), C(  0), Trip.RoundTrip) to Expected(2_885_120),
                    Case("CASE-10", R(S.Tokyo,    S.SinOsaka), Seat.Reserved,    T.Hikari, DDate("2022-12-20"), A(  3), C(  4), Trip.RoundTrip) to Expected(  143_920),
                    Case("CASE-11", R(S.Tokyo,    S.SinOsaka), Seat.NonReserved, T.Nozomi, DDate("2022-01-31"), A( 50), C(  0), Trip.RoundTrip) to Expected(1_390_620),
                    Case("CASE-12", R(S.Himeji,   S.Tokyo),    Seat.NonReserved, T.Nozomi, DDate("2023-01-11"), A(  1), C(100), Trip.OneWay)    to Expected(  780_080),
                    Case("CASE-13", R(S.Himeji,   S.Tokyo),    Seat.Reserved,    T.Hikari, DDate("2022-01-30"), A( 10), C( 40), Trip.RoundTrip) to Expected(  853_940),
                    Case("CASE-14", R(S.Himeji,   S.Tokyo),    Seat.NonReserved, T.Nozomi, DDate("2022-01-22"), A(100), C(  0), Trip.RoundTrip) to Expected(2_926_280),
                    Case("CASE-15", R(S.Himeji,   S.Tokyo),    Seat.NonReserved, T.Nozomi, DDate("2022-12-22"), A(  0), C(  7), Trip.RoundTrip) to Expected(  104_440),
                    Case("CASE-16", R(S.Himeji,   S.Tokyo),    Seat.Reserved,    T.Nozomi, DDate("2022-12-23"), A(  1), C( 30), Trip.OneWay)    to Expected(  246_600),
                    Case("CASE-17", R(S.Himeji,   S.Tokyo),    Seat.NonReserved, T.Hikari, DDate("2023-01-01"), A(  1), C(  6), Trip.RoundTrip) to Expected(  115_080),
                    Case("CASE-18", R(S.Himeji,   S.Tokyo),    Seat.NonReserved, T.Nozomi, DDate("2023-12-20"), A(  2), C(149), Trip.OneWay)    to Expected(1_170_120),
                    Case("CASE-20", R(S.Himeji,   S.SinOsaka), Seat.NonReserved, T.Nozomi, DDate("2023-01-15"), A(  0), C(200), Trip.OneWay)    to Expected(  341_040),
                    Case("CASE-22", R(S.Himeji,   S.SinOsaka), Seat.NonReserved, T.Nozomi, DDate("2023-01-16"), A(150), C(  0), Trip.RoundTrip) to Expected(1_026_060),
                    Case("CASE-23", R(S.Himeji,   S.SinOsaka), Seat.Reserved,    T.Hikari, DDate("2023-01-30"), A(  4), C(196), Trip.RoundTrip) to Expected(  705_600),
                    Case("CASE-24", R(S.Himeji,   S.SinOsaka), Seat.Reserved,    T.Nozomi, DDate("2022-08-31"), A( 15), C( 15), Trip.RoundTrip) to Expected(  171_000),
                    Case("CASE-25", R(S.Himeji,   S.SinOsaka), Seat.Reserved,    T.Hikari, DDate("2022-05-15"), A(  0), C(150), Trip.OneWay)    to Expected(  279_300),
                    Case("CASE-26", R(S.SinOsaka, S.Tokyo),    Seat.NonReserved, T.Hikari, DDate("2023-01-27"), A( 30), C(  0), Trip.OneWay)    to Expected(  376_200),
                    Case("CASE-27", R(S.SinOsaka, S.Tokyo),    Seat.Reserved,    T.Nozomi, DDate("2023-12-21"), A(  7), C(  1), Trip.RoundTrip) to Expected(  207_440),
                    Case("CASE-28", R(S.SinOsaka, S.Tokyo),    Seat.Reserved,    T.Nozomi, DDate("2023-12-24"), A(  1), C(150), Trip.OneWay)    to Expected(1_080_450),
                    Case("CASE-29", R(S.SinOsaka, S.Tokyo),    Seat.Reserved,    T.Nozomi, DDate("2021-12-25"), A(  0), C( 31), Trip.RoundTrip) to Expected(  447_000),
                    Case("CASE-30", R(S.SinOsaka, S.Tokyo),    Seat.NonReserved, T.Hikari, DDate("2022-01-10"), A(  2), C( 98), Trip.OneWay)    to Expected(  679_140),
                    Case("CASE-31", R(S.SinOsaka, S.Himeji),   Seat.NonReserved, T.Nozomi, DDate("2021-01-11"), A(  7), C(  0), Trip.OneWay)    to Expected(   24_430),
                    Case("CASE-34", R(S.SinOsaka, S.Himeji),   Seat.Reserved,    T.Hikari, DDate("2022-01-01"), A(  0), C(101), Trip.OneWay)    to Expected(  196_000),
                    Case("CASE-35", R(S.SinOsaka, S.Himeji),   Seat.NonReserved, T.Hikari, DDate("2022-10-08"), A(  0), C(100), Trip.OneWay)    to Expected(  160_720),
            ).forEach {

                val (case, expected) = it

                "条件が $case のとき" - {
                    val actual = sut.fareFor(case.makeFareRequest())
                    println("actual=$actual")

                    "総支払額は ${expected.totalPaymentAmount()} になる" - {
                        actual.totalPaymentAmount shouldBe expected.totalPaymentAmount()
                    }
                }
            }
        }


        "異常系" - {
            listOf(
                    Case("CASE- 9", R(S.Tokyo,    S.SinOsaka), Seat.NonReserved, T.Hikari, DDate("2022-12-31"), A(  1), C(200), Trip.OneWay),
                    Case("CASE-19", R(S.Himeji,   S.Tokyo),    Seat.NonReserved, T.Hikari, DDate("2023-01-31"), A(201), C(  0), Trip.RoundTrip),
                    Case("CASE-21", R(S.Himeji,   S.SinOsaka), Seat.NonReserved, T.Hikari, DDate("2023-01-13"), A(201), C(  0), Trip.RoundTrip),
                    Case("CASE-32", R(S.SinOsaka, S.Himeji),   Seat.Reserved,    T.Nozomi, DDate("2021-01-16"), A(  0), C(201), Trip.OneWay),
                    Case("CASE-33", R(S.SinOsaka, S.Himeji),   Seat.Reserved,    T.Nozomi, DDate("2021-12-21"), A(200), C(  1), Trip.RoundTrip),
            ).forEach {

                val case = it

                "条件が $case のとき IllegalArgumentExceptionが発生する" - {
                    assertThrows<IllegalArgumentException> {
                        sut.fareFor(case.makeFareRequest())
                    }
                }
            }
        }

    }

})
