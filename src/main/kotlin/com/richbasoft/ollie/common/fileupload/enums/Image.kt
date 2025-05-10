package com.richbasoft.ollie.common.fileupload.enums

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.UUID

enum class ImagePath(
    val path: String
) {
    DEFAULT("/image/richbasoft"),
    TITLE("/image/title"),
    ITEM("/image/item"),
    INQUIRY("/image/inquiry");

    companion object {
        fun ImagePath.generateDynamicPath(): String {
            return if (this == INQUIRY) {
                val currentDate = LocalDate.now()
                val formattedDate = currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))

                "${INQUIRY.path}/$formattedDate"
            } else {
                this.path
            }
        }

        fun ImagePath.generateFilename(originalFilename: String?): String {
            return if (this == INQUIRY) {
                "${UUID.randomUUID()}-$originalFilename"
            } else {
                originalFilename.orEmpty()
            }
        }
    }
}

enum class ImageType(
    val type: String
) {
    JPEG("image/jpeg"),
    PNG("image/png"),
    BMP("image/bmp"),
    WEBP("image/webp"),
    SVG("image/svg+xml"),
    GIF("image/gif");

    companion object {
        fun isSupported(contentType: String?): Boolean {
            return values().any { it.type == contentType }
        }
    }
}