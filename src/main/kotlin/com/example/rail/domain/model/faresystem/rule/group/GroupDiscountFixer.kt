package com.example.rail.domain.model.faresystem.rule.group

import com.example.rail.domain.model.faresystem.factor.date.DepartureDate
import com.example.rail.domain.model.faresystem.factor.passenger.NumberOfPax
import com.example.rail.domain.model.faresystem.rule.basis.BasicFare
import com.example.rail.domain.model.faresystem.rule.group.individually.AppliedGroupIndividualDiscount
import com.example.rail.domain.model.faresystem.rule.group.individually.GroupIndividualDiscount
import com.example.rail.domain.model.faresystem.rule.group.individually.NonGroupIndividualDiscount
import com.example.rail.domain.model.faresystem.rule.group.totally.AppliedGroupTotalDiscount
import com.example.rail.domain.model.faresystem.rule.group.totally.GroupTotalDiscount
import com.example.rail.domain.model.faresystem.rule.group.totally.NonGroupTotalDiscount

/**
 * 団体割引フィクサー
 */
object GroupDiscountFixer {

    data class Factor(
            val numberOfPax: NumberOfPax,
            val departureDate: DepartureDate,
            val basicFare: BasicFare
    )


    //THINK: 30人超の場合に団体個人別割引も適用するという要求もあり得る
    fun fixFor(factor: Factor): Pair<GroupIndividualDiscount, GroupTotalDiscount> {

        val allPax = factor.numberOfPax.allPax.value
        return when {
            allPax in 0..7  -> {    //BIZ-RULE: 団体割引なし
                Pair(
                        NonGroupIndividualDiscount,
                        NonGroupTotalDiscount(factor.numberOfPax)
                )
            }
            allPax in 8..30 -> {    //BIZ-RULE: 団体個人別割引を適用
                Pair(
                        AppliedGroupIndividualDiscount(factor.departureDate, factor.basicFare),
                        NonGroupTotalDiscount(factor.numberOfPax)
                )
            }
            allPax > 30     -> {    //BIZ-RULE: 団体総合割引を適用
                Pair(
                        NonGroupIndividualDiscount,
                        AppliedGroupTotalDiscount(factor.numberOfPax)
                )
            }
            else            -> throw IllegalStateException()
        }
    }
}
