package com.example.rail.presentation.api.fare

import com.example.rail.application.service.FareCalculationService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/fare")
@Api(tags = ["JR新幹線 料金計算"])
//@Tag(name = "JR新幹線 料金計算")
class FareApi(

        val fareCalculationService: FareCalculationService
) {

    @GetMapping
    @ApiOperation("料金計算")
//    @Operation(method = "料金計算")
    fun fareFor(requestForm: FareRequestForm): String {

        val fareRequest = requestForm.createFareRequest()
        val fareStatement = fareCalculationService.fareFor(fareRequest)
        return FareStatementRepresentation(fareStatement).plainText()
    }
}
