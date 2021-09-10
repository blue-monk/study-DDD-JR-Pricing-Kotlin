package com.example.rail.domain.model.faresystem.rule.express

import com.example.rail.domain.model.faresystem._foundation.monetary.amount.FareAmount
import com.example.rail.domain.model.faresystem._foundation.monetary.accumulation.AccumulatableAmount
import com.example.rail.domain.model.faresystem.factor.equipment.TrainType
import com.example.rail.domain.model.faresystem.factor.route.Route
import com.example.rail.domain.model.faresystem.pricing.byRoute.NozomiPremiumChargePricing

/**
 * 特急追加料金
 *
 * 列車タイプとルートにより追加される料金．
 *
 * @property factor
 */
data class LimitedExpressSurcharge(

        private val factor: Factor

) : AccumulatableAmount<FareAmount> {

    data class Factor(
            val route: Route,
            val trainType: TrainType
    )


    companion object {

        fun withFactor(route: Route, trainType: TrainType): LimitedExpressSurcharge {
            return LimitedExpressSurcharge(Factor(route, trainType))
        }
    }


    //BIZ-RULE: のぞみ割り増し
    override val amount: FareAmount = when (factor.trainType) {
        TrainType.Hikari -> FareAmount.ZERO
        TrainType.Nozomi -> NozomiPremiumChargePricing.fareFor(factor.route)
    }
}
