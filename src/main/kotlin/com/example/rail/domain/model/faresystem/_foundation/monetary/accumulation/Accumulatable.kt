package com.example.rail.domain.model.faresystem._foundation.monetary.accumulation

/**
 * 累算可能であることを規定する．
 *
 */
interface Accumulatable {

    val operation: Operation

    enum class Operation {

        Addition,
        Subtraction,
    }
}
