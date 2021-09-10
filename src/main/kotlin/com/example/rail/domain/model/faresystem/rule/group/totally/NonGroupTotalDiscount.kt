package com.example.rail.domain.model.faresystem.rule.group.totally

import com.example.rail.domain.model.faresystem.factor.passenger.NumberOfPax
import com.example.rail.domain.model.faresystem.rule.pax.NumberOfFreePax
import com.example.rail.domain.model.faresystem.rule.pax.NumberOfPaxOnFareCalculation

/**
 * 団体総合割引 （適用なし）
 *
 * @property requestedNumberOfPax
 */
data class NonGroupTotalDiscount(

        private val requestedNumberOfPax: NumberOfPax

) : GroupTotalDiscount {


    override val numberOfPaxOnFareCalculation: NumberOfPaxOnFareCalculation by lazy {
        NumberOfPaxOnFareCalculation(requestedNumberOfPax = requestedNumberOfPax, numberOfFreePax = NumberOfFreePax.ZERO)
    }
}
