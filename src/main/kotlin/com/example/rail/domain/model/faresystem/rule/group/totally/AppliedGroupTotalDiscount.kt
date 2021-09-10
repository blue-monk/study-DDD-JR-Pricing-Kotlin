package com.example.rail.domain.model.faresystem.rule.group.totally

import com.example.rail.domain.model.faresystem.factor.passenger.NumberOfPax
import com.example.rail.domain.model.faresystem.rule.pax.NumberOfFreePax
import com.example.rail.domain.model.faresystem.rule.pax.NumberOfPaxOnFareCalculation

/**
 * 団体総合割引 （適用）
 *
 * @property requestedNumberOfPax
 */
data class AppliedGroupTotalDiscount(

        private val requestedNumberOfPax: NumberOfPax

) : GroupTotalDiscount {


    companion object {

        private const val MINIMUM_APPLICABLE_NUMBER_of_PAX = 31
    }


    override val numberOfPaxOnFareCalculation: NumberOfPaxOnFareCalculation by lazy {
        fixNumberOfPaxOnFareCalculation()
    }

    private fun fixNumberOfPaxOnFareCalculation(): NumberOfPaxOnFareCalculation {

        check(requestedNumberOfPax >= MINIMUM_APPLICABLE_NUMBER_of_PAX) { throw IllegalStateException("総合割引を適用できるのは $MINIMUM_APPLICABLE_NUMBER_of_PAX 人から（プログラミングミス）") }

        val numberOfFreePax = fixNumberOfFreePax()
        return NumberOfPaxOnFareCalculation(requestedNumberOfPax, numberOfFreePax)
    }

    private fun fixNumberOfFreePax(): NumberOfFreePax {

        //BIZ-RULE: 50人に1人無料扱いとする
        //          ・31〜50人の普通団体は、1人分の運賃と特急料金が無料になります
        //          ・51人以上の場合は、50人増えるごとに、1人ずつ運賃・特急料金が無料になります
        return NumberOfFreePax((requestedNumberOfPax + 49) / 50)
    }
}
