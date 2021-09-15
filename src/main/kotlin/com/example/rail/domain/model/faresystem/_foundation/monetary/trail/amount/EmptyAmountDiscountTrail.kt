package com.example.rail.domain.model.faresystem._foundation.monetary.trail.amount

import com.example.rail.domain.model.faresystem._foundation.monetary.trail.Commentary
import com.example.rail.domain.model.faresystem._foundation.monetary.trail.TrailName

class EmptyAmountDiscountTrail : MonetaryDiscountTrail {

    override val trailName: TrailName = TrailName("")
    override val commentary: Commentary = Commentary("")

    override val isNotEmpty: Boolean
        get() = false

    override fun description(): String {
        return ""
    }
}
