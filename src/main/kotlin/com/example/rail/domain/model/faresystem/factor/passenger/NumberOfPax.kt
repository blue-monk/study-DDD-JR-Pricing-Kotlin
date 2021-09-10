package com.example.rail.domain.model.faresystem.factor.passenger

import com.example.rail.domain.model.faresystem.factor.passenger.format.NumberOfPassengerFormatter

/**
 * 乗客数
 *
 * @property adults 大人人数
 * @property children 小人人数
 */
data class NumberOfPax(

        val adults: NumberOfAdults,
        val children: NumberOfChildren

) : NumberOfPassenger {

    init {
        check(adults.value + children.value <= 200) { throw IllegalArgumentException("利用人数は、大人・小人合わせて 200人までです [numberOfAdults=$adults, numberOfChildren=$children]") }
    }

    override val value: Int = adults.value + children.value

    val allPax: NumberOfPassenger = adults + children

    override fun toString(): String {
        return "大人 ${NumberOfPassengerFormatter.format(adults.value)},　小人 ${NumberOfPassengerFormatter.numberOfPaxFormatter.format(children.value)}"
    }
}
