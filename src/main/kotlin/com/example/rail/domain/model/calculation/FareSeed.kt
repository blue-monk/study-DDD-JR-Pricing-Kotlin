package com.example.rail.domain.model.calculation

import com.example.rail.domain.model.calculation.calculator.BasicFareCalculator
import com.example.rail.domain.model.calculation.calculator.ExpressFareCalculator
import com.example.rail.domain.model.calculation.fare.AdultFare
import com.example.rail.domain.model.calculation.fare.ChildFare
import com.example.rail.domain.model.calculation.payment.TotalPaymentAmount
import com.example.rail.domain.model.faresystem.rule.pax.NumberOfPaxOnFareCalculation

data class FareSeed(

        private val fareRequest: FareRequest,
        private val basicFareCalculator: BasicFareCalculator,
        private val expressFareCalculator: ExpressFareCalculator

) {

    fun sprout(): FareStatement {

        val numberOfPaxOnFareCalculation: NumberOfPaxOnFareCalculation = basicFareCalculator.numberOfPaxOnFareCalculation

        val (adultBasicFare, childBasicFare) = basicFareCalculator.calculate()
        val (adultExpressFare, childExpressFare) = expressFareCalculator.calculate()

        val adultFare = AdultFare(numberOfPaxOnFareCalculation, adultBasicFare, adultExpressFare)
        val childFare = ChildFare(numberOfPaxOnFareCalculation, childBasicFare, childExpressFare)

        return FareStatement(
                fareRequest,
                numberOfPaxOnFareCalculation,
                adultFare,
                childFare,
                TotalPaymentAmount.of(adultFare, childFare)
        )
    }

}
