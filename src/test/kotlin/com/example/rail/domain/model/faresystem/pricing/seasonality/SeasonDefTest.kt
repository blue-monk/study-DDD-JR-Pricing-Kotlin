package com.example.rail.domain.model.faresystem.pricing.seasonality

import com.example.rail._init.AppInitializer
import com.example.rail._toolbox_.dateStream
import com.example.rail.domain.model.faresystem.factor.date.DepartureDate
import io.kotest.core.spec.style.FreeSpec
import org.junit.jupiter.api.assertDoesNotThrow
import java.time.LocalDate
import java.time.Month

class SeasonDefTest : FreeSpec({

    beforeSpec {
        AppInitializer.initialize()
    }


    "seasonTypeOn" - {

        val sut = SeasonDef

        "季節タイプテーブルの期間重複チェック" - {

            assertDoesNotThrow {

                dateStream(from = LocalDate.of(2020, Month.JANUARY, 1), to = LocalDate.of(2020, Month.DECEMBER, 31))
                        .forEach {
                            val seasonType = sut.seasonTypeOn(DepartureDate(it))
                            println("it=$it, seasonType=$seasonType")
                        }
            }
        }
    }

})
