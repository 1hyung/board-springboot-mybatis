package com.wonhyung.board_springboot_mybatis.dto

import java.time.LocalDateTime // 날짜와 시간 정보를 다루기 위한 `LocalDateTime` 클래스를 임포트합니다.

// 이 클래스는 게시글 목록을 조회할 때 사용되는 DTO(Data Transfer Object)입니다.
// 게시글 전체 상세 정보가 아닌, 목록 화면에 필요한 최소한의 정보만 담아 전송함으로써 데이터 전송 효율을 높입니다.
data class BoardListDTO(
    val id: Long? = null, // 게시글의 고유 식별 번호(ID)입니다. `null` 값을 가질 수 있는 `Long?` 타입이며, 초기값은 `null`입니다.

    val boardTitle: String?, // 게시글의 제목을 저장하는 필드입니다. `null` 값을 가질 수 있는 `String?` 타입입니다.

    val boardWriter: String?, // 게시글의 작성자 이름을 저장하는 필드입니다. `null` 값을 가질 수 있는 `String?` 타입입니다.

    val boardHits: Int = 0, // 게시글의 조회수를 저장하는 필드입니다. `Int` 타입이며, 초기값은 `0`입니다.

    val createdAt: LocalDateTime? = null, // 게시글이 생성된 날짜와 시간을 저장하는 필드입니다. `null` 값을 가질 수 있는 `LocalDateTime?` 타입이며, 초기값은 `null`입니다.
)