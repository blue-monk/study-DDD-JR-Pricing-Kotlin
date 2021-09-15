package com.example.rail.domain.model.faresystem.rule.group.individually

import com.example.rail.domain.model.faresystem._foundation.monetary.amount.DiscountAmount
import com.example.rail.domain.model.faresystem._foundation.monetary.trail.amount.MonetaryDiscountTrail

/**
 * 団体個人別割引 （適用なし）
 */
object NonGroupIndividualDiscount : GroupIndividualDiscount {

    override val amount: DiscountAmount
        get() = DiscountAmount.ZERO

    override val discountTrail: MonetaryDiscountTrail
        get() = MonetaryDiscountTrail.nothing()
}
