package com.example.rail.application.service

import com.example.rail.domain.model.calculation.FareRequest
import com.example.rail.domain.model.calculation.FareStatement
import org.springframework.stereotype.Service

@Service
object FareCalculationService {

    fun fareFor(fareRequest: FareRequest): FareStatement {

        val fareSeed = fareRequest.makeFareSeed()
        return fareSeed.sprout()
    }
}
