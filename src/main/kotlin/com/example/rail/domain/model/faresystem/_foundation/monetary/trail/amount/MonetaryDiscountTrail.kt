package com.example.rail.domain.model.faresystem._foundation.monetary.trail.amount

import com.example.rail.domain.model.faresystem._foundation.monetary.trail.DiscountTrail
import com.example.rail.domain.model.faresystem._foundation.monetary.trail.Commentary
import com.example.rail.domain.model.faresystem._foundation.monetary.trail.TrailName

sealed interface MonetaryDiscountTrail : DiscountTrail {

    companion object Factory {

        fun nothing(): MonetaryDiscountTrail = EmptyAmountDiscountTrail()

        fun trail(name: TrailName, commentary: Commentary, value: AmountType, signRequisition: SignRequisition): MonetaryDiscountTrail = AmountDiscountTrail(name, commentary, value, signRequisition)
    }
}
