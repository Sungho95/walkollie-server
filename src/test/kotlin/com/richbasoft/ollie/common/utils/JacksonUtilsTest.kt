package com.richbasoft.ollie.common.utils

import com.fasterxml.jackson.core.JsonFactory
import com.fasterxml.jackson.databind.ObjectMapper
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import java.io.StringWriter
import java.time.LocalDateTime
import kotlin.properties.Delegates

internal class JacksonUtilsTest : StringSpec({

    var objectMapper: ObjectMapper by Delegates.notNull()

    beforeTest {
        objectMapper = customObjectMapper()
    }

    "LocalDateTime을 직렬화해야 한다" {
        val localDateTime = LocalDateTime.of(2023, 1, 1, 0, 0, 0)
        val expected = "\"2023-01-01 00:00:00\""

        val writer = StringWriter()
        val gen = JsonFactory().createGenerator(writer)
        objectMapper.writeValue(gen, localDateTime)
        gen.close()
        val json = writer.toString()

        json shouldBe expected
    }

    "LocalDateTime을 역직렬화해야 한다" {
        val json = "\"2023-01-01 00:00:00\""
        val expected = LocalDateTime.of(2023, 1, 1, 0, 0, 0)

        val parser = JsonFactory().createParser(json)
        val localDateTime = objectMapper.readValue(parser, LocalDateTime::class.java)

        localDateTime shouldBe expected
    }
})
