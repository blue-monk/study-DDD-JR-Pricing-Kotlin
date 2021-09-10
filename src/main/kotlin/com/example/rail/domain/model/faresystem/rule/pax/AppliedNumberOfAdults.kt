package com.example.rail.domain.model.faresystem.rule.pax

import com.example.rail.domain.model.faresystem.factor.passenger.format.NumberOfPassengerFormatter

/**
 * 運賃計算に適用する大人人数
 *
 * @property value
 */
data class AppliedNumberOfAdults(

        val value: Int

) {


    companion object Factory {

        val ZERO = AppliedNumberOfAdults(0)
    }


    override fun toString(): String {
        return NumberOfPassengerFormatter.format(value)
    }
}
