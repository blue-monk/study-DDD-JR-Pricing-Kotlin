package com.example.rail.domain.model.faresystem._foundation.monetary.trail.amount

enum class SignRequisition {

    No,
    Yes,
    ;

    fun isNotRequired(): Boolean = this == No
}
