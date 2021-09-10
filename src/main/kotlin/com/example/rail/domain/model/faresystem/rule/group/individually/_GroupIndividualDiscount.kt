package com.example.rail.domain.model.faresystem.rule.group.individually

import com.example.rail.domain.model.faresystem._foundation.monetary.accumulation.AccumulatableAmount
import com.example.rail.domain.model.faresystem._foundation.monetary.amount.DiscountAmount

/**
 * 団体個人別割引
 */
sealed interface GroupIndividualDiscount : AccumulatableAmount<DiscountAmount>




