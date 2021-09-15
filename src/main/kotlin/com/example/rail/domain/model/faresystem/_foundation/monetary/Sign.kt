package com.example.rail.domain.model.faresystem._foundation.monetary

import com.example.rail.shared.enum.findEnum
import java.math.BigDecimal

enum class Sign(

        val signum: Int,
        val signString: String,

) {

    Positive(signum = 1, signString = "+"),
    Zero(signum = 0, signString = "+"),
    Negative(signum = -1, signString = "-"),
    ;


    companion object {

        fun of(signum: Int): Sign {
            return findEnum { it.signum == signum }
        }
    }
}

fun BigDecimal.sign(): Sign {
    return Sign.of(this.signum())
}
