package com.example.rail.domain._policy.format

import com.example.rail.domain._policy.format.fabric.CommaSeparated
import com.example.rail.domain._policy.format.fabric.DecimalFormatPattern
import com.example.rail.domain._policy.format.fabric.Plain
import com.example.rail.domain.model.faresystem.factor.passenger.format.NumberOfPassengerFormatterProtocol

class NumberOfPassengerFormatPolicy(

        private val decimalFormatPattern: DecimalFormatPattern,
        private val trailingPaxNotation: String

) : NumberOfPassengerFormatterProtocol {

    override fun format(numberOfPax: Int): String {

        return when (decimalFormatPattern) {
            is Plain          -> "$numberOfPax $trailingPaxNotation"
            is CommaSeparated -> "${decimalFormatPattern.decimalFormat.format(numberOfPax)} $trailingPaxNotation"
        }
    }
}
