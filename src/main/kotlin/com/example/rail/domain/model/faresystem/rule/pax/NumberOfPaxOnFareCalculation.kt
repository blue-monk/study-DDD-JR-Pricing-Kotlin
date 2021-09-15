package com.example.rail.domain.model.faresystem.rule.pax

import com.example.rail.domain.model.faresystem._foundation.monetary.trail.freepax.PaxDiscountTrails
import com.example.rail.domain.model.faresystem.factor.passenger.NumberOfPax

/**
 * 運賃計算上の乗客数
 *
 * @property requestedNumberOfPax 運賃計算要求された乗客数
 * @property numberOfFreePax 無料扱いの乗客数
 */
data class NumberOfPaxOnFareCalculation(

        private val requestedNumberOfPax: NumberOfPax,
        private val numberOfFreePax: NumberOfFreePax = NumberOfFreePax.ZERO

) {

    /** 無料扱い考慮済みの乗客数（運賃計算で使用する） */
    val appliedNumberOfPax: AppliedNumberOfPax

    val paxDiscountTrails: PaxDiscountTrails

    init {
        val (appliedNumberOfPax, paxDiscountTrails) = numberOfFreePax.applyTo(requestedNumberOfPax)
        this.appliedNumberOfPax = appliedNumberOfPax
        this.paxDiscountTrails = paxDiscountTrails
    }
}
