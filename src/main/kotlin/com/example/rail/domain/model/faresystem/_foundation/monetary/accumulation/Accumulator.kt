package com.example.rail.domain.model.faresystem._foundation.monetary.accumulation

import com.example.rail.domain.model.faresystem._foundation.monetary.JpMoney
import com.example.rail.domain.model.faresystem._foundation.monetary.amount.TemporalAmount
import com.example.rail.domain.model.faresystem._foundation.monetary.trail.DiscountTrail
import com.example.rail.domain.model.faresystem._foundation.monetary.trail.DiscountTrails

/**
 * 累算器
 *
 * ## 使い方
 * [Accumulator] を直接使うのではなく、[accumulateAmount]関数を使う．
 *
 */
class Accumulator {

    private var amount: TemporalAmount = TemporalAmount.ZERO

    private val trails: MutableList<DiscountTrail> = mutableListOf()


    fun <T : AccumulatableAmount<*>> accumulate(accumulatableAmount: T): Accumulator {

        if (accumulatableAmount.isZero) {
            return this
        }

        when (accumulatableAmount.operation) {
            Accumulatable.Operation.Addition    -> this.amount += accumulatableAmount.amount
            Accumulatable.Operation.Subtraction -> this.amount -= accumulatableAmount.amount
        }

        trails.add(accumulatableAmount.discountTrail)

        return this
    }


    val roundedJpMoney: JpMoney
        get() = amount.roundedJpMoney

    val discountTrails: DiscountTrails
        get() = DiscountTrails(trails)
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
 * @return (累算結果金額, 割引証跡)
 */
fun accumulateAmount(accumulatingBlock: Accumulator.() -> Unit): Pair<AccumulatedAmount, DiscountTrails> {

    val accumulator = Accumulator()
    accumulator.accumulatingBlock()
    return AccumulatedAmount(accumulator.roundedJpMoney) to accumulator.discountTrails
}
