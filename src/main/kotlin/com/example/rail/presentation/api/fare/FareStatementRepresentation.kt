package com.example.rail.presentation.api.fare

import com.example.rail.domain.model.calculation.FareRequest
import com.example.rail.domain.model.calculation.FareStatement
import com.example.rail.domain.model.calculation.fare.AdultFare
import com.example.rail.domain.model.calculation.fare.ChildFare
import com.example.rail.domain.model.calculation.payment.TotalPaymentAmount
import com.example.rail.domain.model.faresystem.rule.pax.NumberOfPaxOnFareCalculation

data class FareStatementRepresentation(

        val fareStatement: FareStatement

) {

    private val fareRequest: FareRequest = fareStatement.fareRequest
    private val numberOfPaxOnFareCalculation: NumberOfPaxOnFareCalculation = fareStatement.numberOfPaxOnFareCalculation
    private val adultFare: AdultFare = fareStatement.adultFare
    private val childFare: ChildFare = fareStatement.childFare
    private val totalPaymentAmount: TotalPaymentAmount = fareStatement.totalPaymentAmount

    //NOTE: SwaggerUI での確認用
    fun plainText(): String {

        return """
            経路　　　　　　　　${fareRequest.route.from} − ${fareRequest.route.to}
            座席タイプ　　　　　${fareRequest.seatType}
            列車タイプ　　　　　${fareRequest.trainType}
            出発日　　　　　　　${fareRequest.departureDate}
            行程タイプ　　　　　${fareRequest.tripType}
            人数　　　　　　　　${fareRequest.numberOfPax}
            ---------------------------------------------------------------------
            大人${fareRequest.tripType.displayName}普通運賃　　${padded(adultFare.adultBasicFare.actualAmountWithTripType.displayString)}　　　　　${adultFare.adultBasicFare.trailsDescription}
            大人${fareRequest.tripType.displayName}特急料金　　${padded(adultFare.adultExpressFare.actualAmountWithTripType.displayString)}　　　　　${adultFare.adultExpressFare.trailsDescription}
            大人運賃合計　　　　${padded(adultFare.adultFareTotal.displayString)}
            大人適用人数　　　　${padded(numberOfPaxOnFareCalculation.appliedNumberOfPax.adults.displayString)}　　　　　${numberOfPaxOnFareCalculation.paxDiscountTrails.adult.description()}
            大人運賃総合計　　　${padded(adultFare.allAdultsFareTotal.displayString)}
            ---------------------------------------------------------------------
            小人${fareRequest.tripType.displayName}普通運賃　　${padded(childFare.childBasicFare.actualAmountWithTripType.displayString)}　　　　　${childFare.childBasicFare.trailsDescription}
            小人${fareRequest.tripType.displayName}特急料金　　${padded(childFare.childExpressFare.actualAmountWithTripType.displayString)}　　　　　${childFare.childExpressFare.trailsDescription}
            小人運賃合計　　　　${padded(childFare.childFareTotal.displayString)}
            小人適用人数　　　　${padded(numberOfPaxOnFareCalculation.appliedNumberOfPax.children.displayString)}　　　　　${numberOfPaxOnFareCalculation.paxDiscountTrails.child.description()}
            小人運賃総合計　　　${padded(childFare.allChildrenFareTotal.displayString)}
            ---------------------------------------------------------------------
            お支払い金額合計　　${padded(totalPaymentAmount.displayString)}
        """.trimIndent()
    }

    private fun padded(value: String, numberOfDigits: Int = 11): String {
        return value.padStart(numberOfDigits)
    }
}
