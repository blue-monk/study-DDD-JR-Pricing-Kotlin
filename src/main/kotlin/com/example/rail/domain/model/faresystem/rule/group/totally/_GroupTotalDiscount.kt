package com.example.rail.domain.model.faresystem.rule.group.totally

import com.example.rail.domain.model.faresystem.rule.pax.NumberOfPaxOnFareCalculation

/**
 * 団体総合割引
 *
 * 団体総合割引は、「n人分無料扱い」というかたちなので、
 * 団体総合割引を判定すると運賃計算上の乗客数が判明する．
 */
sealed interface GroupTotalDiscount {

    val numberOfPaxOnFareCalculation: NumberOfPaxOnFareCalculation
}
