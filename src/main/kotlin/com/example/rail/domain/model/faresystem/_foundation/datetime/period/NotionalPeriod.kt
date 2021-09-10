package com.example.rail.domain.model.faresystem._foundation.datetime.period

import java.time.MonthDay

/**
 * 月日で表現する概念的期間
 *
 * 特定の年にしばられない期間を表現する．
 *
 * @property range
 */
data class NotionalPeriod(

        private val range: ClosedRange<MonthDay>

) {

    /**
     * @param first フォーマットは `MM-dd`
     * @param last フォーマットは `MM-dd`
     */
    constructor(first: String, last: String) : this(MonthDay.parse("--$first")..MonthDay.parse("--$last"))

    init {
        check(range.start <= range.endInclusive) { throw IllegalStateException("範囲指定の順序間違い [range.start=${range.start}, range.endInclusive=${range.endInclusive}]") }
    }


    operator fun contains(other: MonthDay): Boolean {
        return other in range
    }
}
