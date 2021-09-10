package com.example.rail.domain.model.faresystem.rule.pax

/**
 * 運賃計算に適用する乗客数
 *
 * @property adults
 * @property children
 */
data class AppliedNumberOfPax(

        val adults: AppliedNumberOfAdults,
        val children: AppliedNumberOfChildren
)
