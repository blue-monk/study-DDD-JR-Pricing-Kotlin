package com.example.rail.domain.model.calculation.calculator

import com.example.rail.domain.model.calculation.fare.details.AdultBasicFare
import com.example.rail.domain.model.calculation.fare.details.ChildBasicFare
import com.example.rail.domain.model.faresystem._foundation.monetary.accumulation.accumulateAmount
import com.example.rail.domain.model.faresystem.factor.date.DepartureDate
import com.example.rail.domain.model.faresystem.factor.passenger.NumberOfPax
import com.example.rail.domain.model.faresystem.factor.route.Route
import com.example.rail.domain.model.faresystem.factor.route.TripType
import com.example.rail.domain.model.faresystem.rule.basis.BasicFare
import com.example.rail.domain.model.faresystem.rule.basis.RoundTripDiscount
import com.example.rail.domain.model.faresystem.rule.basis.organized.IntimatePrimeFares
import com.example.rail.domain.model.faresystem.rule.group.GroupDiscountFixer
import com.example.rail.domain.model.faresystem.rule.group.individually.GroupIndividualDiscount
import com.example.rail.domain.model.faresystem.rule.group.totally.GroupTotalDiscount
import com.example.rail.domain.model.faresystem.rule.pax.NumberOfPaxOnFareCalculation

/**
 * 普通運賃計算機
 *
 * ファクターから普通運賃計算に必要な料金を導出し、
 * それらを累算して普通運賃を算出する．
 *
 * @property factor ファクター
 */
data class BasicFareCalculator(

        private val factor: Factor

) {

    data class Factor(
            val route: Route,
            val departureDate: DepartureDate,
            val numberOfPax: NumberOfPax,
            val tripType: TripType
    )


    companion object {

        fun withFactor(route: Route, departureDate: DepartureDate, numberOfPax: NumberOfPax, tripType: TripType): BasicFareCalculator {
            return BasicFareCalculator(Factor(route, departureDate, numberOfPax, tripType))
        }
    }


    data class Derived(
            val basicFare: BasicFare,
            val roundTripDiscount: RoundTripDiscount,
            val groupIndividualDiscount: GroupIndividualDiscount,
            val groupTotalDiscount: GroupTotalDiscount
    )

    private val derived: Derived by lazy {
        derive()
    }

    private fun derive(): Derived {

        //THINK: 往復割引も団体個人別割引もそれぞれ basicFare をベースに計算している。団体個人別割引は往復割引後の価格を元にするということも考えられる（要求次第）

        val intimatePrimeFares = IntimatePrimeFares.withFactor(factor.route, factor.tripType)
        val basicFare = intimatePrimeFares.basicFare
        val roundTripDiscount = intimatePrimeFares.roundTripDiscount

        val (groupIndividualDiscount, groupTotalDiscount) = GroupDiscountFixer.fixFor(
                GroupDiscountFixer.Factor(
                        factor.numberOfPax,
                        factor.departureDate,
                        basicFare
                )
        )

        return Derived(basicFare, roundTripDiscount, groupIndividualDiscount, groupTotalDiscount)
    }


    val numberOfPaxOnFareCalculation: NumberOfPaxOnFareCalculation by lazy {
        derived.groupTotalDiscount.numberOfPaxOnFareCalculation
    }

    fun calculate(): Pair<AdultBasicFare, ChildBasicFare> {

        val accumulatedAmount = accumulateAmount {
            accumulate(derived.basicFare)
            accumulate(derived.roundTripDiscount)
            accumulate(derived.groupIndividualDiscount)
        }

        val adultBasicFare = AdultBasicFare(accumulatedAmount.roundedJpMoney, factor.tripType)
        val childBasicFare = ChildBasicFare.createFrom(adultBasicFare)

        return adultBasicFare to childBasicFare
    }
}
