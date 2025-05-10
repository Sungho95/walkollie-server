package com.richbasoft.ollie.common.utils

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.*
import com.fasterxml.jackson.databind.module.SimpleModule
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun customObjectMapper(): ObjectMapper {
    val objectMapper = ObjectMapper()
    val module = SimpleModule()

    module.addSerializer(LocalDateTime::class.java, LocalDateTimeSerializer())
    module.addDeserializer(LocalDateTime::class.java, LocalDateTimeDeserializer())
    objectMapper.registerModule(module)

    return objectMapper
}

class LocalDateTimeSerializer : JsonSerializer<LocalDateTime>() {

    override fun serialize(
        value: LocalDateTime,
        gen: JsonGenerator,
        serializers: SerializerProvider
    ) {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        gen.writeString(value.format(formatter))
    }
}

class LocalDateTimeDeserializer : JsonDeserializer<LocalDateTime>() {

    override fun deserialize(
        jsonParser: JsonParser,
        deserializationContext: DeserializationContext
    ): LocalDateTime {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

        return LocalDateTime.parse(jsonParser.text, formatter)
    }
}