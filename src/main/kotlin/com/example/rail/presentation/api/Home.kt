package com.example.rail.presentation.api

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller("ホーム")
@RequestMapping("/")
class Home {

    @GetMapping
    open fun redirectToSwaggerUi(): String? {
        return "redirect:/swagger-ui/index.html"
//        return "redirect:/swagger-ui.html"
    }
}
