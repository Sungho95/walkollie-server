package com.richbasoft.ollie.common.fileupload

import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.services.s3.model.ObjectMetadata
import com.richbasoft.ollie.common.exception.BusinessLogicException
import com.richbasoft.ollie.common.exception.ExceptionCode
import com.richbasoft.ollie.common.fileupload.enums.ImagePath
import com.richbasoft.ollie.common.fileupload.enums.ImagePath.Companion.generateDynamicPath
import com.richbasoft.ollie.common.fileupload.enums.ImagePath.Companion.generateFilename
import com.richbasoft.ollie.common.fileupload.enums.ImageType
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class FileServiceImpl(
    private val amazonS3Client: AmazonS3Client,

    @Value("\${cloud.aws.s3.bucket}")
    private val bucket: String
) : FileService {

    override fun imageUpload(image: MultipartFile, path: ImagePath): String {
        imageValidation(image)

        val storePath = path.generateDynamicPath()
        val filename = path.generateFilename(image.originalFilename)
        val metadata = createObjectMetadata(image)

        amazonS3Client.putObject(bucket + storePath, filename, image.inputStream, metadata)
        return amazonS3Client.getUrl(bucket + storePath, filename).toString()
    }

    private fun createObjectMetadata(image: MultipartFile): ObjectMetadata {
        val metadata = ObjectMetadata()
        metadata.contentLength = image.size
        metadata.contentType = image.contentType
        return metadata
    }

    private fun imageValidation(image: MultipartFile) {
        if (!ImageType.isSupported(image.contentType)) {
            throw BusinessLogicException(ExceptionCode.INVALID_IMAGE_TYPE)
        }
    }
}