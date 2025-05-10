package com.richbasoft.ollie.common.utils.calculator

import java.time.LocalDate
import java.time.Period

fun calculateAge(birthDate: LocalDate): Int {
    return if (birthDate == LocalDate.of(1900, 1, 1)) {
        0
    } else {
        Period.between(birthDate, LocalDate.now()).years
    }
}