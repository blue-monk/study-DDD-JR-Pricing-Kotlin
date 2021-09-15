package com.example.rail.domain.model.faresystem._foundation.monetary.trail.freepax.format

interface PaxDiscountTrailFormatterProtocol {

    fun format(numberOfPax: Int): String
}


object PaxDiscountTrailFormatter {

    lateinit var paxDiscountTrailFormatter: PaxDiscountTrailFormatterProtocol

    fun format(numberOfPax: Int): String {
        return paxDiscountTrailFormatter.format(numberOfPax)
    }
}
