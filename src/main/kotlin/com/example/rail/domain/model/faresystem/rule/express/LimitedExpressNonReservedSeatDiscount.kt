package com.example.rail.domain.model.faresystem.rule.express

import com.example.rail.domain.model.faresystem._foundation.monetary.amount.DiscountAmount
import com.example.rail.domain.model.faresystem._foundation.monetary.accumulation.AccumulatableAmount
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
}
