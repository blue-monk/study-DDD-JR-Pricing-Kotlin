package com.example.rail.domain.model.faresystem._foundation.monetary.format

import com.example.rail.domain._policy.format.fabric.RateNotation
import java.math.BigDecimal

interface MonetaryFormatterProtocol {

    fun format(bigDecimal: BigDecimal): String
}

interface RateFormatterProtocol {

    fun format(suiteValue: (rateNotation: RateNotation) -> BigDecimal): String
}



object MonetaryFormatter {

    lateinit var monetaryFormatter: MonetaryFormatterProtocol

    lateinit var rateFormatter: RateFormatterProtocol
}
