package com.example.rail.domain._policy.format.fabric

enum class RateNotation(

        val value: String

) {

    None(""),
    Percentage("%"),
    PercentageWideLetter("％"),
    ;
}
