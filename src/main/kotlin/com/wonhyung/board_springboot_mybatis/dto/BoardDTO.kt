package com.wonhyung.board_springboot_mybatis.dto

import java.time.LocalDateTime // 날짜와 시간 정보를 다루기 위한 `LocalDateTime` 클래스를 임포트합니다.

// 'data class'는 Kotlin에서 데이터를 저장하는 용도의 클래스임을 나타내는 특별한 키워드입니다.
// 이 키워드를 사용하면 `equals()`, `hashCode()`, `toString()`, `copy()` 등의 유용한 메서드들이 컴파일러에 의해 자동으로 생성됩니다.
// DTO(Data Transfer Object)는 계층 간(예: 컨트롤러 <-> 서비스, 서비스 <-> 리포지토리) 데이터 전송을 위해 사용됩니다.
data class BoardDTO(
    val id: Long? = null, // 게시글의 고유 식별 번호(ID)입니다. 데이터베이스에서 자동으로 생성될 수 있으므로 `null` 값을 가질 수 있는 `Long?` 타입이며, 초기값은 `null`입니다.
    // `?`는 Kotlin의 널 안정성(Null Safety) 문법으로, 이 변수가 `null`일 수도 있음을 명시합니다.

    val boardTitle: String?, // 게시글의 제목을 저장하는 필드입니다. `null` 값을 가질 수 있는 `String?` 타입입니다.

    val boardWriter: String?, // 게시글의 작성자 이름을 저장하는 필드입니다. `null` 값을 가질 수 있는 `String?` 타입입니다.

    val boardPass: String?, // 게시글 수정/삭제 시 사용될 비밀번호를 저장하는 필드입니다. `null` 값을 가질 수 있는 `String?` 타입입니다.

    val boardContents: String?, // 게시글의 본문 내용을 저장하는 필드입니다. `null` 값을 가질 수 있는 `String?` 타입입니다.

    val boardHits: Int = 0, // 게시글의 조회수를 저장하는 필드입니다. `Int` 타입이며, 명시적으로 초기값 `0`을 가집니다.

    val createdAt: LocalDateTime? = null, // 게시글이 생성된 날짜와 시간을 저장하는 필드입니다. `null` 값을 가질 수 있는 `LocalDateTime?` 타입이며, 초기값은 `null`입니다.
    // `LocalDateTime` 타입을 사용하면 날짜/시간 데이터를 더 정확하고 편리하게 다룰 수 있습니다.

    val fileAttached: Int = 0, // 게시글에 파일이 첨부되었는지 여부를 나타내는 필드입니다. `Int` 타입이며, 초기값은 `0` (파일 없음)으로 설정됩니다.
    // 일반적으로 `0`은 파일 없음, `1`은 파일 있음과 같이 숫자로 상태를 표현할 때 사용됩니다.
)