package com.example.rail.domain._config

import com.example.rail.domain._policy.format.MonetaryFormatPolicy
import com.example.rail.domain._policy.format.NumberOfPassengerFormatPolicy
import com.example.rail.domain._policy.format.fabric.CurrencyNotation
import com.example.rail.domain._policy.format.fabric.Plain
import com.example.rail.domain.model.faresystem._foundation.monetary.format.MonetaryFormatter
import com.example.rail.domain.model.faresystem._foundation.monetary.format.MonetaryFormatterProtocol
import com.example.rail.domain.model.faresystem.factor.passenger.format.NumberOfPassengerFormatter
import com.example.rail.domain.model.faresystem.factor.passenger.format.NumberOfPassengerFormatterProtocol
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
object DomainConfigProxy {

    @PostConstruct
    fun configureDefaults() {

        monetaryFormatPolicy = monetaryFormatPolicy
        numberOfPaxFormatPolicy = numberOfPaxFormatPolicy
    }


    var monetaryFormatPolicy: MonetaryFormatterProtocol = MonetaryFormatPolicy(decimalFormatPattern = Plain, CurrencyNotation.English)
        set(value) {
            field = value
            MonetaryFormatter.monetaryFormatter = value
        }

    var numberOfPaxFormatPolicy: NumberOfPassengerFormatterProtocol = NumberOfPassengerFormatPolicy(decimalFormatPattern = Plain, trailingPaxNotation = "名")
        set(value) {
            field = value
            NumberOfPassengerFormatter.numberOfPaxFormatter = value
        }
}
