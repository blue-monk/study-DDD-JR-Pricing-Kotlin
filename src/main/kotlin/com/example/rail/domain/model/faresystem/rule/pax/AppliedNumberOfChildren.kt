package com.example.rail.domain.model.faresystem.rule.pax

import com.example.rail.domain.model.faresystem.factor.passenger.NumberOfChildren
import com.example.rail.domain.model.faresystem.factor.passenger.format.NumberOfPassengerFormatter

/**
 * 運賃計算に適用する小人人数
 *
 * @property value
 */
data class AppliedNumberOfChildren(

        val value: Int

) {

    constructor(numberOfChildren: NumberOfChildren) : this(numberOfChildren.value)


    val displayString: String = NumberOfPassengerFormatter.format(value)
}
