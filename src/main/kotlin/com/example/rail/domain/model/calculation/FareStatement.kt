package com.example.rail.domain.model.calculation

import com.example.rail.domain.model.calculation.fare.AdultFare
import com.example.rail.domain.model.calculation.fare.ChildFare
import com.example.rail.domain.model.calculation.payment.TotalPaymentAmount
import com.example.rail.domain.model.faresystem.rule.pax.NumberOfPaxOnFareCalculation

/**
 * 運賃明細
 *
 * @property fareRequest
 * @property numberOfPaxOnFareCalculation
 * @property adultFare
 * @property childFare
 * @property totalPaymentAmount
 */
data class FareStatement(

        val fareRequest: FareRequest,

        val numberOfPaxOnFareCalculation: NumberOfPaxOnFareCalculation,

        val adultFare: AdultFare,
        val childFare: ChildFare,

        val totalPaymentAmount: TotalPaymentAmount
)
