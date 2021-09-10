package com.example.rail.domain.model.calculation.fare

import com.example.rail.domain.model.calculation.fare.details.AdultBasicFare
import com.example.rail.domain.model.calculation.fare.details.AdultExpressFare
import com.example.rail.domain.model.calculation.fare.details.AdultFareTotal
import com.example.rail.domain.model.calculation.fare.details.AllAdultsFareTotal
import com.example.rail.domain.model.faresystem.rule.pax.NumberOfPaxOnFareCalculation

/**
 * 大人運賃
 *
 * @property numberOfPaxOnFareCalculation 運賃計算上の乗客数
 * @property adultBasicFare 大人普通運賃
 * @property adultExpressFare 大人特急料金
 */
data class AdultFare(

        private val numberOfPaxOnFareCalculation: NumberOfPaxOnFareCalculation,
        val adultBasicFare: AdultBasicFare,
        val adultExpressFare: AdultExpressFare

) {

    //BIZ-RULE: 料金 ＝ 運賃 + 特急料金
    val adultFareTotal: AdultFareTotal = adultBasicFare + adultExpressFare

    val allAdultsFareTotal: AllAdultsFareTotal = adultFareTotal * numberOfPaxOnFareCalculation.appliedNumberOfPax.adults
}
