package com.wonhyung.board_springboot_mybatis.controller

import com.wonhyung.board_springboot_mybatis.dto.BoardDTO
import com.wonhyung.board_springboot_mybatis.service.BoardService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping

@Controller // 1. Spring MVC 컨트롤러임을 나타냅니다.
class BoardController(private val boardService: BoardService) { // 2. 주 생성자를 통한 의존성 주입

    /**게시글 작성 페이지(save.html)를 보여주는 메서드
    HTTP GET 요청을 "/save" 경로로 받음
    @return "save" 문자열을 반환하며, Thymeleaf 등의 뷰 리졸버에 의해
    src/main/resources/templates/save.html 파일을 찾아 렌더링합니다. */
    @GetMapping("/save")
    fun save(): String {
        // 특별한 로직 없이 바로 뷰 페이지만 보여주는 경우

        return "save"
    }

    /**   게시글 저장을 처리하는 메서드
    HTTP POST 요청을 "/save" 경로로 받습니다
    클라이언트로부터 전달된 폼 데이터를 BoardDTO 객체로 바인딩합니다
    @param boardDTO 클라이언트가 전송한 게시글 데이터 (폼 데이터로부터 자동 매핑)
    @return "index" 문자열을 반환하여 index.html 뷰를 보여주거나
    실제로는 저장 후 리다이렉트 하는 것이 일반적*/
    @PostMapping("/save") // 위의 코드와 다른 이유 1. HTTP 메서드 기준
    fun save(boardDTO: BoardDTO): String { // 2. 매개변수의 개수나 타입이 다르면 다른 함수로 인식
        println("boardDTO = $boardDTO")
        return "index"
    }
}

