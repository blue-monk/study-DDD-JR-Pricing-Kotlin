package com.example.rail.domain.model.calculation.fare

import com.example.rail.domain.model.calculation.fare.details.AllChildrenFareTotal
import com.example.rail.domain.model.calculation.fare.details.ChildBasicFare
import com.example.rail.domain.model.calculation.fare.details.ChildExpressFare
import com.example.rail.domain.model.calculation.fare.details.ChildFareTotal
import com.example.rail.domain.model.faresystem.rule.pax.NumberOfPaxOnFareCalculation

/**
 * 小人運賃
 *
 * @property numberOfPaxOnFareCalculation 運賃計算上の乗客数
 * @property childBasicFare 小人普通運賃
 * @property childExpressFare 小人特急料金
 */
data class ChildFare(

        private val numberOfPaxOnFareCalculation: NumberOfPaxOnFareCalculation,
        val childBasicFare: ChildBasicFare,
        val childExpressFare: ChildExpressFare

) {

    //BIZ-RULE: 料金 ＝ 運賃 + 特急料金
    val childFareTotal: ChildFareTotal = childBasicFare + childExpressFare

    val allChildrenFareTotal: AllChildrenFareTotal = childFareTotal * numberOfPaxOnFareCalculation.appliedNumberOfPax.children
}
