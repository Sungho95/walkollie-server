package com.richbasoft.ollie.common.utils.annotation

import com.richbasoft.ollie.common.utils.validator.NotSpaceValidator
import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [NotSpaceValidator::class])
annotation class NotSpace(
    val message: String = "공백이 들어갈 수 없습니다.",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)
