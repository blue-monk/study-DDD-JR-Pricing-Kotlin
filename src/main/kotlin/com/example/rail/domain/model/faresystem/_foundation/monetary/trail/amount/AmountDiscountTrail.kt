package com.example.rail.domain.model.faresystem._foundation.monetary.trail.amount

import com.example.rail.domain.model.faresystem._foundation.monetary.trail.Commentary
import com.example.rail.domain.model.faresystem._foundation.monetary.trail.TrailName
import java.math.BigDecimal

class AmountDiscountTrail(

        override val trailName: TrailName,
        override val commentary: Commentary,

        private val value: AmountType,
        private val signRequisition: SignRequisition

) : MonetaryDiscountTrail {

    override val isNotEmpty: Boolean
        get() = value != BigDecimal.ZERO

    override fun description(): String {

        val formattedValue = when (value) {
            is FareType -> value.amountOfMoney.displayStringAsAbsoluteValue()
            is RateType -> value.discountRate.description()
        }

        return if (isNotEmpty) "${trailName.value} ${signString()}${formattedValue} ${commentary.value}" else ""
    }


    private fun signString(): String {

        if (signRequisition.isNotRequired()) return ""

        return when (value) {
            is FareType -> value.amountOfMoney.signString
            is RateType -> value.discountRate.signString
        }
    }
}
