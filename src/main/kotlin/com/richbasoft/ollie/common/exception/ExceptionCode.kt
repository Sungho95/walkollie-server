package com.richbasoft.ollie.common.exception

enum class ExceptionCode(
    var status: Int,
    var message: String
) {
    // MEMBER
    MEMBER_NOT_FOUND(404, "회원을 찾을 수 없습니다."),
    MEMBER_EXISTS(409, "이미 존재하는 회원입니다."),
    MEMBER_IDENTITY_MISMATCH(400, "잘못된 접근입니다."),
    // TODO: 예외처리 추가

    // Ollie
    OLLIE_NOT_FOUND(404, "올리를 찾을 수 없습니다."),

    // OllieItem
    OLLIE_ITEM_NOT_FOUND(404, "해당 아이템을 가지고 있지 않습니다."),
    OLLIE_ITEM_IS_EXISTS(409, "이미 소유한 아이템입니다."),
    INSUFFICIENT_POINTS(403, "구매하기 위한 포인트가 부족합니다."),

    // OllieTitle
    OLLIE_TITLE_NOT_FOUND(404, "해당 칭호를 가지고 있지 않습니다."),

    // Title
    TITLE_NOT_FOUND(404, "칭호를 찾을 수 없습니다."),

    // Item
    ITEM_NOT_FOUND(404, "아이템을 찾을 수 없습니다."),

    // Inquiry
    INQUIRY_NOT_FOUND(404, "문의를 찾을 수 없습니다."),
    INQUIRY_ID_NOT_MATCH(400, "문의 ID가 일치하지 않습니다."),
    INQUIRY_IS_NOT_ANSWERED(400, "답변이 없는 문의입니다."),

    // Answer
    ANSWER_NOT_FOUND(404, "문의 답변을 찾을 수 없습니다."),

    // File
    INVALID_IMAGE_TYPE(415, "지원하지 않는 이미지 형식입니다."),
    FILE_NOT_FOUND(404, "파일을 찾을 수 없습니다."),
    
    // Jwt
    TOKEN_IS_EXPIRED(404, "만료된 토큰입니다."),
    TOKEN_DOES_NOT_MATCH(400, "토큰이 일치하지 않습니다.")
}