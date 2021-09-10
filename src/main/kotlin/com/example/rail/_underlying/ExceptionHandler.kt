package com.example.rail._underlying

import org.springframework.http.HttpStatus
import org.springframework.validation.BindException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice


@RestControllerAdvice
class ExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException::class)
    fun handle(e: IllegalArgumentException): String {

        return "入力エラー: ${e.message ?: "IllegalArgumentException が発生（メッセージなし）"}"
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(IllegalStateException::class)
    fun handle(e: IllegalStateException): String {

        println(e.printStackTrace())
        return "プログラミングエラー: ${e.message ?: "IllegalStateException が発生（メッセージなし）"}"
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException::class)
    fun handle(e: BindException): String {

        return "入力エラー: ${e.message}"
    }
}
