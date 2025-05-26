package com.wonhyung.board_springboot_mybatis.controller

import com.wonhyung.board_springboot_mybatis.service.BoardService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller // 1. Spring MVC 컨트롤러임을 나타냅니다.
class BoardController(private val boardService: BoardService) { // 2. 주 생성자를 통한 의존성 주입

    /*게시글 작성 페이지(save.html)를 보여주는 메서드
    HTTP GET 요청을 "/save" 경로로 받음
    @return "save" 문자열을 반환하며, Thymeleaf 등의 뷰 리졸버에 의해
     src/main/resources/templates/save.html 파일을 찾아 렌더링합니다. */
    @GetMapping("/save")
    fun save(): String {
        // 특별한 로직 없이 바로 뷰 페이지만 보여주는 경우

        return "save"
    }
    // 예시: 게시판 목록 페이지를 보여주는 핸들러 메서드
    // @GetMapping("/list")
    // fun boardListPage(): String {
    //     // boardService를 사용하는 로직
    //     return "boardList" // 뷰 이름 반환
    // }

    // 다른 컨트롤러 메서드들이 여기에 위치
}