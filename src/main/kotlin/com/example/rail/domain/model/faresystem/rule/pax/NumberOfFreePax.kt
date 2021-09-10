package com.example.rail.domain.model.faresystem.rule.pax

import com.example.rail.domain.model.faresystem.factor.passenger.NumberOfAdults
import com.example.rail.domain.model.faresystem.factor.passenger.NumberOfChildren
import com.example.rail.domain.model.faresystem.factor.passenger.NumberOfPassenger
import com.example.rail.domain.model.faresystem.factor.passenger.NumberOfPax
import com.example.rail.domain.model.faresystem.factor.passenger.TemporalNumberOfPax

/**
 * 無料扱い乗客数
 *
 * @property value 無料扱いとする乗客数
 */
data class NumberOfFreePax(

        private val value: Int

) {

    constructor(numberOfPax: TemporalNumberOfPax) : this(numberOfPax.value)


    companion object {

        val ZERO: NumberOfFreePax = NumberOfFreePax(0)
    }


    fun applyTo(numberOfPax: NumberOfPax): AppliedNumberOfPax {

        check(this <= numberOfPax.allPax) { throw IllegalStateException("無料扱いの人数が想定外") }

        val numberOfAdults = numberOfPax.adults
        val numberOfChildren = numberOfPax.children

        //SPEC: 無料扱いは大人から適用していく
        return (numberOfAdults - this).let {
            if (it >= 0) {
                AppliedNumberOfPax(adults = it, children = AppliedNumberOfChildren(numberOfChildren))
            }
            else {
                AppliedNumberOfPax(adults = AppliedNumberOfAdults.ZERO, children = numberOfChildren + it)
            }
        }
    }



    operator fun compareTo(other: NumberOfPassenger) = value.compareTo(other.value)

    operator fun NumberOfAdults.minus(numberOfFreePax: NumberOfFreePax) = AppliedNumberOfAdults(this.value - numberOfFreePax.value)

    operator fun AppliedNumberOfAdults.compareTo(other: Int) = value.compareTo(other)

    operator fun NumberOfChildren.plus(applicableNumberOfAdults: AppliedNumberOfAdults) = AppliedNumberOfChildren(this.value + applicableNumberOfAdults.value)
}
