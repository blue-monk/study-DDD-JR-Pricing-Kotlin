package com.example.rail.domain.model.faresystem._foundation.monetary.trail.amount

import com.example.rail.domain.model.faresystem._foundation.monetary.DiscountRate
import com.example.rail.domain.model.faresystem._foundation.monetary.amount.AmountOfMoney

sealed interface AmountType

data class FareType(val amountOfMoney: AmountOfMoney) : AmountType
data class RateType(val discountRate: DiscountRate) : AmountType
