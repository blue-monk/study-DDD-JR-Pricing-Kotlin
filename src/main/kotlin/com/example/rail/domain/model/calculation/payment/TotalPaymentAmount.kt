package com.example.rail.domain.model.calculation.payment

import com.example.rail.domain.model.calculation.fare.AdultFare
import com.example.rail.domain.model.calculation.fare.ChildFare
import com.example.rail.domain.model.faresystem._foundation.monetary.JpMoney

/**
 * 合計支払額
 *
 * @property jpMoney 金額（日本円）
 */
data class TotalPaymentAmount(

        private val jpMoney: JpMoney

) {


    companion object {

        fun of(adultFare: AdultFare, childFare: ChildFare): TotalPaymentAmount {
            return TotalPaymentAmount((adultFare.allAdultsFareTotal + childFare.allChildrenFareTotal).jpMoney)
        }
    }


    val displayString: String = jpMoney.displayString
}
