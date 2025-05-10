package com.richbasoft.ollie.common.fileupload

import com.richbasoft.ollie.common.base.dto.SingleResponse
import com.richbasoft.ollie.common.fileupload.enums.ImagePath
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/api/v1/file")
class FileController(
    private val fileService: FileService
) {

    @PostMapping("/image/upload")
    fun imageUpload(
        @RequestParam image: MultipartFile,
        @RequestParam path: ImagePath
    ): ResponseEntity<*> {
        val response = fileService.imageUpload(image, path)

        return ResponseEntity.status(HttpStatus.OK)
            .body(SingleResponse(response))
    }
}