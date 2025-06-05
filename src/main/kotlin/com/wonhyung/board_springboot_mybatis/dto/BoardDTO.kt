package com.wonhyung.board_springboot_mybatis.dto

import java.time.LocalDateTime

//필드를 정의해야함
data class BoardDTO(
    val id: Long? = null, // 게시글 ID (DB에서 자동 생성될 수 있으므로 nullable, 기본값 null)

    val boardTitle: String?,  // 제목

    val boardWriter: String?, // 작성자

    val boardPass: String?,   // 비밀번호

    val boardContents: String?, // 내용

    val boardHits: Int = 0,   // 조회수 (기본값 0)

    val createdAt: LocalDateTime? = null, // 실제로는 날짜/시간 타입을 사용하는 것이 좋음

    // val createdAt: String? = null, // 작성 시간 (문자열 형태)

    val fileAttached: Int = 0,

)