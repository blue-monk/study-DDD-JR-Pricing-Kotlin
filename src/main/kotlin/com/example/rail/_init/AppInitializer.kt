package com.example.rail._init

import com.example.rail.domain._config.DomainConfigProxy
import com.example.rail.domain._policy.format.MonetaryFormatPolicy
import com.example.rail.domain._policy.format.NumberOfPassengerFormatPolicy
import com.example.rail.domain._policy.format.fabric.CommaSeparated
import com.example.rail.domain._policy.format.fabric.CurrencyNotation
import com.example.rail.domain._policy.format.fabric.Plain
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
object AppInitializer {

    @PostConstruct
    fun initialize() {

        DomainConfigProxy.apply {

            monetaryFormatPolicy = MonetaryFormatPolicy(
                    decimalFormatPattern = CommaSeparated(),
                    trailingCurrencyNotation = CurrencyNotation.JapaneseKanji
            )

            numberOfPaxFormatPolicy = NumberOfPassengerFormatPolicy(
                    decimalFormatPattern = Plain,
                    trailingPaxNotation = "名"
            )
        }
    }
}