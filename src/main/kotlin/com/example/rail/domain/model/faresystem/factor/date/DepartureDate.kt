package com.example.rail.domain.model.faresystem.factor.date

import com.example.rail.domain.model.faresystem._foundation.datetime.period.NotionalPeriod
import kotlinx.collections.immutable.ImmutableMap
import java.time.LocalDate
import java.time.MonthDay
import java.time.format.DateTimeFormatter

/**
 * 出発日
 *
 * @property value
 */
data class DepartureDate(

        private val value: LocalDate

) {

    constructor(text: String) : this(LocalDate.parse(text, DateTimeFormatter.ISO_LOCAL_DATE))

    fun monthDay(): MonthDay {
        return MonthDay.from(value)
    }

    override fun toString(): String {
        return value.toString()
    }
}



/**
 * キーを NotionalPeriod とする ImmutableMap に登録されたエントリーから、
 * 指定された出発日の月日が、NotionalPeriod の範囲内のエントリーを探し、その値を返す．
 *
 * 見つからない場合は null を返す．
 * 複数見つかった場合は IllegalStateException をスローする．
 *
 * @param V ImmutableMap の値の型
 * @param departureDate 出発日
 * @param subject ImmutableMap の内容を表す名称．重複エラーを検出した場合に、この文字列をエラーメッセージで使用する
 *
 * @return 検索ヒットした Mapエントリーの値
 *
 * @throws IllegalStateException 複数見つかった場合
 */
fun <V> ImmutableMap<NotionalPeriod, V>.valueFor(departureDate: DepartureDate, subject: String): V? {

    val candidates = departureDate.monthDay().let {
        this.filter { (period, _) ->
            it in period                        //NOTE: あるいは period.contains(it) のどちらでも
        }
    }

    check(candidates.size < 2) { throw IllegalStateException("${subject}で期間の重複あり [departureDate=$departureDate]") }

    return if (candidates.isEmpty()) {
        null
    }
    else {
        candidates.entries.elementAt(0).value
    }
}
