package com.example.rail.domain.model.faresystem.rule.group.individually

import com.example.rail.domain.model.faresystem._foundation.monetary.DiscountRate
import com.example.rail.domain.model.faresystem._foundation.monetary.amount.DiscountAmount
import com.example.rail.domain.model.faresystem.factor.date.DepartureDate
import com.example.rail.domain.model.faresystem.pricing.discount.GroupIndividualDiscountPricing
import com.example.rail.domain.model.faresystem.rule.basis.BasicFare

/**
 * 団体個人別割引 （適用）
 *
 * 各乗客の片道運賃に対して割引率を適用する．
 *
 * @property basicFare 普通運賃
 * @constructor
 * 指定された出発日における割引率が採用される．
 *
 * @param departureDate 出発日
 */
class AppliedGroupIndividualDiscount(

        departureDate: DepartureDate,
        private val basicFare: BasicFare

) : GroupIndividualDiscount {

    private val discountRate: DiscountRate = GroupIndividualDiscountPricing.discountRateOn(departureDate)

    override val amount: DiscountAmount
        get() = basicFare * discountRate

}
