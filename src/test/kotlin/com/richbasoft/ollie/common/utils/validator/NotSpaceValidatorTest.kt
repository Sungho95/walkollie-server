package com.richbasoft.ollie.common.utils.validator

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.mockk.mockk
import jakarta.validation.ConstraintValidatorContext

internal class NotSpaceValidatorTest : StringSpec({

    lateinit var notSpaceValidator: NotSpaceValidator
    lateinit var context: ConstraintValidatorContext

    beforeTest {
        notSpaceValidator = NotSpaceValidator()
        context = mockk()
    }

    "비어있지 않은 문자열에 대해서는 true를 반환해야 한다" {
        val value = "Ollie"
        val isValid = notSpaceValidator.isValid(value, context)

        isValid shouldBe true
    }

    "공백 문자를 포함하는 문자열에 대해서는 false를 반환해야 한다" {
        val value = "Richba Soft"
        val isValid = notSpaceValidator.isValid(value, context)

        isValid shouldBe false
    }
})