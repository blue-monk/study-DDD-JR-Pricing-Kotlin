package com.example.rail._toolbox_

import java.time.LocalDate
import java.util.stream.Stream


fun dateStream(from: LocalDate, to: LocalDate): Stream<LocalDate> {

    return dateInfiniteStream(from)
            .takeWhile { !it.isAfter(to) }
}

fun dateInfiniteStream(from: LocalDate): Stream<LocalDate> {

    return Stream.iterate(from) { it.plusDays(1) }
}