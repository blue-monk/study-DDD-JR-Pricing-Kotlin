package com.example.rail.domain.model.faresystem.factor.passenger

/**
 * 小人人数
 *
 * @property value
 */
data class NumberOfChildren(

        override val value: Int

) : NumberOfPassenger {

    init {
        if (value < 0) throw IllegalArgumentException("小人人数は正数のみ可")
    }
}
