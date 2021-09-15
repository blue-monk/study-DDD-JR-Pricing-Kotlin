package com.example.rail.domain._policy.format

import com.example.rail.domain._policy.format.fabric.CommaSeparated
import com.example.rail.domain._policy.format.fabric.DecimalFormatPattern
import com.example.rail.domain._policy.format.fabric.Plain
import com.example.rail.domain._policy.format.fabric.RateNotation
import com.example.rail.domain.model.faresystem._foundation.monetary.format.RateFormatterProtocol
import java.math.BigDecimal

class RateFormatPolicy(

        private val decimalFormatPattern: DecimalFormatPattern,
        private val trailingRateNotation: RateNotation

) : RateFormatterProtocol {

    override fun format(suiteValue: (rateNotation: RateNotation) -> BigDecimal): String {

        val value = suiteValue(trailingRateNotation)

        return when (decimalFormatPattern) {
            is Plain -> "${value.toPlainString()} ${trailingRateNotation.value}"
            is CommaSeparated -> "${decimalFormatPattern.decimalFormat.format(value)} ${trailingRateNotation.value}"
        }
    }
}
