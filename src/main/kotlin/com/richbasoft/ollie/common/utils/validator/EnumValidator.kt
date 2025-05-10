package com.richbasoft.ollie.common.utils.validator

import java.lang.Enum.*

fun <T : Enum<T>> String.toEnum(enumClass: Class<T>): T {
    try {
        return valueOf(enumClass, this)
    } catch (e: IllegalArgumentException) {
        throw IllegalArgumentException("Invalid value for enum ${enumClass.simpleName}: $this")
    }
}