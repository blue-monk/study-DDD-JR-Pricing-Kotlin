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


    val absolute: AppliedNumberOfAdults
        get() = AppliedNumberOfAdults(kotlin.math.abs(value))


    val displayString: String = NumberOfPassengerFormatter.format(value)
}
