package com.wonhyung.board_springboot_mybatis.dto

import java.time.LocalDateTime

data class BoardListDTO(
    val id: Long? = null,

    val boardTitle: String?,

    val boardWriter: String?,

    val boardHits: Int = 0,

    val createdAt: LocalDateTime? = null,
)
