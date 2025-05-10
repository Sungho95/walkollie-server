package com.richbasoft.ollie.common.utils.calculator

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDate

internal class DateCalculatorKtTest : StringSpec({

    "birthDate가 1900-01-01이면, 0을 반환한다" {
        val birthDate = LocalDate.of(1900, 1, 1)
        val result = calculateAge(birthDate)

        result shouldBe 0
    }

    "birthDate에 대한 현재 나이를 계산해야 한다" {
        val birthDate = LocalDate.of(1995, 8, 8)
        val result = calculateAge(birthDate)

        val isBirthdayPast = LocalDate.now().dayOfYear >= birthDate.dayOfYear
        val expected = LocalDate.now().year - birthDate.year - if (isBirthdayPast) 0 else 1

        result shouldBe expected
    }
})