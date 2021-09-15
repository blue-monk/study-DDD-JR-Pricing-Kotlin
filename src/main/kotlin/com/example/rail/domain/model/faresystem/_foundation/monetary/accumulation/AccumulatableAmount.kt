package com.example.rail.domain.model.faresystem._foundation.monetary.accumulation

import com.example.rail.domain.model.faresystem._foundation.monetary.amount.AmountOfMoney
import com.example.rail.domain.model.faresystem._foundation.monetary.trail.amount.MonetaryDiscountTrail

/**
 * 累算可能な料金
 *
 * ## 料金型と運賃計算について
 *
 * この interface に準拠した料金型を設けて、
 * その料金型に準拠した運賃型を用意し、
 * [accumulateAmount]関数で積み上げることで運賃計算する．
 *
 * 料金のタイプとしては、[2種類][Accumulatable.Operation]用意している．
 *
 * 加算系と減算系の2種類の料金型をベースとして用意し、
 * 作成する運賃型には、
 * その料金のタイプを見極めて、加算系か減算系の料金型に準拠するようにする．
 *
 * 概ね次のような感じ．
 * * 加算系
 *      * 普通運賃
 *      * 特急料金や季節性料金などの割増系
 *  * 減算系
 *      * 割引料金
 *
 * @param T AmountOfMoney および Accumulatable に準拠した型
 */
interface AccumulatableAmount<T> where T : AmountOfMoney, T : Accumulatable {

    val amount: T

    val isZero: Boolean
        get() = amount.jpMoney.isZero

    val operation: Accumulatable.Operation
        get() = amount.operation

    val discountTrail: MonetaryDiscountTrail
}
