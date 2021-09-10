package com.example.rail.domain.model.faresystem._foundation.monetary.format

import java.math.BigDecimal

interface MonetaryFormatterProtocol {

    fun format(bigDecimal: BigDecimal): String
}



object MonetaryFormatter {

    lateinit var monetaryFormatter: MonetaryFormatterProtocol
}
