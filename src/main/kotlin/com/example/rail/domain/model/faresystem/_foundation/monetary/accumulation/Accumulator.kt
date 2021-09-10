package com.example.rail.domain.model.faresystem._foundation.monetary.accumulation

import com.example.rail.domain.model.faresystem._foundation.monetary.JpMoney
import com.example.rail.domain.model.faresystem._foundation.monetary.amount.TemporalAmount

/**
 * 累算器
 *
 * ## 使い方
 * [Accumulator] を直接使うのではなく、[accumulateAmount]関数を使う．
 *
 */
class Accumulator {

    private var amount: TemporalAmount = TemporalAmount.ZERO


    fun <T : AccumulatableAmount<*>> accumulate(accumulatableAmount: T): Accumulator {

        if (accumulatableAmount.isZero) {
            return this
        }

        when (accumulatableAmount.operation) {
            Accumulatable.Operation.Addition    -> this.amount += accumulatableAmount.amount
            Accumulatable.Operation.Subtraction -> this.amount -= accumulatableAmount.amount
        }

        return this
    }


    val roundedJpMoney: JpMoney
        get() = amount.roundedJpMoney
}


/**
 * 累算された料金
 *
 * @property roundedJpMoney 丸め済み
 */
data class AccumulatedAmount(val roundedJpMoney: JpMoney)

/**
 * 運賃累算のための関数
 *
 * ## 使い方
 * [accumulatingBlock] に [Accumulator] がレシーバオブジェクトとして渡されるので、[Accumulator.accumulate] を使って積み上げる．
 *
 * @param accumulatingBlock 運賃積み上げブロック．
 * @receiver [Accumulator]
 *
 * @return 累算結果金額
 */
fun accumulateAmount(accumulatingBlock: Accumulator.() -> Unit): AccumulatedAmount {

    val accumulator = Accumulator()
    accumulator.accumulatingBlock()
    return AccumulatedAmount(accumulator.roundedJpMoney)
}
