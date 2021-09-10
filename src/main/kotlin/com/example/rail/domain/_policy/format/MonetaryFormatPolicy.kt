package com.example.rail.domain._policy.format

import com.example.rail.domain._policy.format.fabric.CommaSeparated
import com.example.rail.domain._policy.format.fabric.CurrencyNotation
import com.example.rail.domain._policy.format.fabric.DecimalFormatPattern
import com.example.rail.domain._policy.format.fabric.Plain
import com.example.rail.domain.model.faresystem._foundation.monetary.format.MonetaryFormatterProtocol
import java.math.BigDecimal

class MonetaryFormatPolicy(

        private val decimalFormatPattern: DecimalFormatPattern,
        private val trailingCurrencyNotation: CurrencyNotation

) : MonetaryFormatterProtocol {

    override fun format(bigDecimal: BigDecimal): String {

        return when (decimalFormatPattern) {
            is Plain          -> "${bigDecimal.toPlainString()} ${trailingCurrencyNotation.value}"
            is CommaSeparated -> "${decimalFormatPattern.decimalFormat.format(bigDecimal)} ${trailingCurrencyNotation.value}"
        }
    }
}
