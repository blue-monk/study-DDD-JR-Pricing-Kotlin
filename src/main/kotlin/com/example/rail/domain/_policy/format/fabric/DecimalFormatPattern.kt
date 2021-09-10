package com.example.rail.domain._policy.format.fabric

import java.text.DecimalFormat


sealed interface DecimalFormatPattern

object Plain : DecimalFormatPattern

class CommaSeparated : DecimalFormatPattern {

    val decimalFormat: DecimalFormat = DecimalFormat("#,###")
}
