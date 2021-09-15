package com.example.rail.domain.model.faresystem._foundation.monetary.trail

class DiscountTrails(

        private val trails: List<DiscountTrail>

) {

    fun joinedDescription(separator: String = "、"): String {

        return trails
                .filter { it.isNotEmpty }
                .joinToString(separator, transform = DiscountTrail::description)
    }
}
