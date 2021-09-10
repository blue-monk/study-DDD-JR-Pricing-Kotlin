package com.example.rail._config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors.regex
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
class SwaggerConfig {

    @Bean
    fun docket(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
                .groupName("rail-fare-calculation-api")
                .apiInfo(apiInfo())
                .select()
                .paths(regex("/api/.*"))
                .build()
    }

    private fun apiInfo(): ApiInfo {
        return ApiInfoBuilder()
                .title("JR 新幹線料金計算 API")
                .description("""
                    下記の要領で実行
                    * [JR新幹線 料金計算] をクリック
                    * [GET  /api/fare 料金計算] をクリック
                    * [Try it out] をクリック
                    * パラメータを入力（出発日は yyyy-MM-dd）
                    * [Execute] をクリック
                     """)
                .version("1.0")
                .build()
    }
}
