package com.richbasoft.ollie.common.utils.validator

import com.richbasoft.ollie.common.utils.annotation.NotSpace
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

class NotSpaceValidator : ConstraintValidator<NotSpace, String> {

    override fun initialize(constraintAnnotation: NotSpace) {
        super.initialize(constraintAnnotation)
    }

    override fun isValid(value: String, context: ConstraintValidatorContext): Boolean {
        return value.isBlank() || !value.contains("\\s+".toRegex())
    }
}
