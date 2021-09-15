package com.example.rail.domain.model.faresystem._foundation.monetary.trail

interface DiscountTrail {

    val trailName: TrailName
    val commentary: Commentary

    val isNotEmpty: Boolean

    fun description(): String
}

data class TrailName(val value: String)
data class Commentary(val value: String)
