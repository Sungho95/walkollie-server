package com.richbasoft.ollie.common.fileupload

import com.richbasoft.ollie.common.fileupload.enums.ImagePath
import org.springframework.web.multipart.MultipartFile

interface FileService {

    fun imageUpload(image: MultipartFile, path: ImagePath): String

}