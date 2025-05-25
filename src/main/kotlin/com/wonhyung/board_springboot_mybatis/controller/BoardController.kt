package com.wonhyung.board_springboot_mybatis.controller

import com.wonhyung.board_springboot_mybatis.service.BoardService
import org.springframework.stereotype.Controller

@Controller // 1. Spring MVC 컨트롤러임을 나타냅니다.
class BoardController(private val boardService: BoardService ) { // 2. 주 생성자를 통한 의존성 주입

    // 예시: 게시판 목록 페이지를 보여주는 핸들러 메서드
    // @GetMapping("/list")
    // fun boardListPage(): String {
    //     // boardService를 사용하는 로직
    //     return "boardList" // 뷰 이름 반환
    // }

    // 다른 컨트롤러 메서드들이 여기에 위치
}