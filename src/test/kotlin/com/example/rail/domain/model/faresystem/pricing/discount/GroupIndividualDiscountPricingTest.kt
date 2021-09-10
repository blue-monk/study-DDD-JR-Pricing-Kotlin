package com.example.rail.domain.model.faresystem.pricing.discount

import com.example.rail._init.AppInitializer
import com.example.rail._toolbox_.dateStream
import com.example.rail.domain.model.faresystem.factor.date.DepartureDate
import io.kotest.core.spec.style.FreeSpec
import org.junit.jupiter.api.assertDoesNotThrow
import java.time.LocalDate
import java.time.Month

class GroupIndividualDiscountPricingTest : FreeSpec({

    beforeSpec {
        AppInitializer.initialize()
    }


    "discountRateOn" - {

        "団体個人別割引率テーブルの期間重複チェック" - {

            val sut = GroupIndividualDiscountPricing

            assertDoesNotThrow {

                dateStream(from = LocalDate.of(2020, Month.JANUARY, 1), to = LocalDate.of(2020, Month.DECEMBER, 31))
                        .forEach {
                            val rate = sut.discountRateOn(DepartureDate(it))
                            println("it=$it, rate=$rate")
                        }
            }
        }
    }

})
