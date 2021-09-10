package com.example.rail.domain.model.faresystem.factor.route

/**
 * 駅
 *
 * @property displayName
 */
enum class Station(

        val displayName: String

) {

    Tokyo(displayName = "東京"),
    SinOsaka(displayName = "新大阪"),
    Himeji(displayName = "姫路"),
    ;
}
