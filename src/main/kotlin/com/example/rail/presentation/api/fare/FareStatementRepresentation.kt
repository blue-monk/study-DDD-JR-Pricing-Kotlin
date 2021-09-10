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
            大人${fareRequest.tripType.displayName}普通運賃　　${adultFare.adultBasicFare.actualAmountWithTripType.displayString}
            大人${fareRequest.tripType.displayName}特急料金　　${adultFare.adultExpressFare.actualAmountWithTripType.displayString}
            大人運賃合計　　　　${adultFare.adultFareTotal.displayString}
            大人適用人数　　　　${numberOfPaxOnFareCalculation.appliedNumberOfPax.adults}
            大人運賃総合計　　　${adultFare.allAdultsFareTotal.displayString}
            ---------------------------------------------------------------------
            小人${fareRequest.tripType.displayName}普通運賃　　${childFare.childBasicFare.actualAmountWithTripType.displayString}
            小人${fareRequest.tripType.displayName}特急料金　　${childFare.childExpressFare.actualAmountWithTripType.displayString}
            小人運賃合計　　　　${childFare.childFareTotal.displayString}
            小人適用人数　　　　${numberOfPaxOnFareCalculation.appliedNumberOfPax.children}
            小人運賃総合計　　　${childFare.allChildrenFareTotal.displayString}
            ---------------------------------------------------------------------
            お支払い金額合計　　${totalPaymentAmount.displayString}
        """.trimIndent()
    }
}
