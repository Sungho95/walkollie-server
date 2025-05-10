package com.richbasoft.ollie.common.exception

class BusinessLogicException(
    val exceptionCode: ExceptionCode
) : RuntimeException(exceptionCode.message) {
}