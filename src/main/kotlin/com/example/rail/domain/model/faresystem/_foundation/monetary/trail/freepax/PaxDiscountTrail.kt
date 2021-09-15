package com.example.rail.domain.model.faresystem._foundation.monetary.trail.freepax

import com.example.rail.domain.model.faresystem._foundation.monetary.trail.DiscountTrail
import com.example.rail.domain.model.faresystem._foundation.monetary.trail.Commentary
import com.example.rail.domain.model.faresystem._foundation.monetary.trail.TrailName
import com.example.rail.domain.model.faresystem._foundation.monetary.trail.freepax.format.PaxDiscountTrailFormatter

sealed class PaxDiscountTrail(

        override val trailName: TrailName,
        override val commentary: Commentary,

        private val paxType: PaxType,
        private val value: Int

) : DiscountTrail {


    companion object Factory {

        fun adultPaxDiscountTrail(name: TrailName, commentary: Commentary, value: Int): AdultPaxDiscountTrail = AdultPaxDiscountTrail(name, commentary, value)

        fun childPaxDiscountTrail(name: TrailName, commentary: Commentary, value: Int): ChildPaxDiscountTrail = ChildPaxDiscountTrail(name, commentary, value)
    }


    override val isNotEmpty: Boolean
        get() = value != 0

    override fun description(): String {
        return if (isNotEmpty) "${trailName.value} ${paxType.displayString} ${PaxDiscountTrailFormatter.format(value)} ${commentary.value}" else ""
    }
}



class AdultPaxDiscountTrail(name: TrailName, commentary: Commentary, value: Int) : PaxDiscountTrail(name, commentary, PaxType.Adult, value)

class ChildPaxDiscountTrail(name: TrailName, commentary: Commentary, value: Int) : PaxDiscountTrail(name, commentary, PaxType.Child, value) {

    companion object Factory {

        val nothing: ChildPaxDiscountTrail = ChildPaxDiscountTrail(TrailName(""), Commentary(""), 0)
    }
}



data class PaxDiscountTrails(

        val adult: AdultPaxDiscountTrail,
        val child: ChildPaxDiscountTrail
)
