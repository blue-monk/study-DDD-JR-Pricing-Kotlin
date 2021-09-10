package com.example.rail.domain.model.faresystem.factor.passenger.format

interface NumberOfPassengerFormatterProtocol {

    fun format(numberOfPax: Int): String
}

object NumberOfPassengerFormatter {

    lateinit var numberOfPaxFormatter: NumberOfPassengerFormatterProtocol

    fun format(numberOfPax: Int): String {
        return numberOfPaxFormatter.format(numberOfPax)
    }
}
