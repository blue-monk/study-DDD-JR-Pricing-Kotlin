package com.example.rail.domain.model.faresystem.rule.express

import com.example.rail.domain.model.faresystem._foundation.monetary.accumulation.AccumulatableAmount
import com.example.rail.domain.model.faresystem._foundation.monetary.amount.DiscountAmount
import com.example.rail.domain.model.faresystem._foundation.monetary.trail.Commentary
import com.example.rail.domain.model.faresystem._foundation.monetary.trail.TrailName
import com.example.rail.domain.model.faresystem._foundation.monetary.trail.amount.FareType
import com.example.rail.domain.model.faresystem._foundation.monetary.trail.amount.MonetaryDiscountTrail
import com.example.rail.domain.model.faresystem._foundation.monetary.trail.amount.SignRequisition
import com.example.rail.domain.model.faresystem.factor.equipment.SeatType
import com.example.rail.domain.model.faresystem.pricing.discount.LimitedExpressNonReservedSeatDiscountPricing

/**
 * 特急自由席割引
 *
 * @property factor
 */
data class LimitedExpressNonReservedSeatDiscount(

        private val factor: Factor

) : AccumulatableAmount<DiscountAmount> {

    data class Factor(
            val seatType: SeatType
    )


    companion object {

        fun withFactor(seatType: SeatType): LimitedExpressNonReservedSeatDiscount {
            return LimitedExpressNonReservedSeatDiscount(Factor(seatType))
        }
    }


    //BIZ-RULE: 自由席特急料金: 季節によって変動しない。 通常期の指定席特急券より530円を引いた金額で年間固定。
    override val amount = when (factor.seatType) {
        SeatType.NonReserved -> LimitedExpressNonReservedSeatDiscountPricing.discountAmount
        SeatType.Reserved    -> DiscountAmount.ZERO
    }

    override val discountTrail: MonetaryDiscountTrail
        get() = MonetaryDiscountTrail.trail(name = TrailName("特急自由席割引"), commentary = Commentary("適用済み"), value = FareType(amount), signRequisition = SignRequisition.No)
}
