package com.wonhyung.board_springboot_mybatis.controller

import com.wonhyung.board_springboot_mybatis.dto.BoardDTO
import com.wonhyung.board_springboot_mybatis.dto.BoardListDTO
import com.wonhyung.board_springboot_mybatis.service.BoardService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
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
        println("boardDTO = $boardDTO")  // 전달받은 BoardDTO 내용 출력 (Kotlin 스타일)

        // BoardService의 save 메서드를 호출하여 게시글 데이터 저장
        boardService.save(boardDTO)

        // 저장 후 리다이렉트가 일반적 (PRG 패턴)
        // 예: return "redirect:/board/list" // 게시글 목록 페이지로 리다이렉트
        // 예: return "redirect:/board/${boardDTO.id}" // 저장된 게시글 상세 페이지로 리다이렉트

        return "index" // 현재는 "index.html" 뷰를 직접 렌더링 (디버깅/초기 단계용)
    }

    /** 모든 게시글 목록을 조회하여 뷰에 전달하는 메서드
    HTTP GET 요청을 "/list" 경로로 처리합니다.
    @param model Thymeleaf에 전달할 데이터를 담는 모델 객체
    @return "list" 뷰 이름을 반환하여 templates/list.html을 렌더링합니다.
     */
    @GetMapping("/list") // HTTP GET 요청으로 "/list" 경로에 접근할 때 실행됨
    fun findAll(model: Model): String {
        // 1. BoardService의 findAll 메서드를 호출해 모든 게시글을 가져옵니다.
        val boardDTOList: List<BoardListDTO> = boardService.findAll()

        // 2. 가져온 게시글 목록을 모델 객체에 담아 뷰에 전달합니다.
        // 모델에 담긴 "boardList" 이름으로 Thymeleaf에서 사용할 수 있습니다.
        model.addAttribute("boardList", boardDTOList)
        System.out.println("boardDTOList = " + boardDTOList)  // 디버깅을 위해 콘솔에 게시글 목록 출력

        // 3. 최종적으로 "list.html" 뷰를 렌더링합니다.
        // Thymeleaf 뷰 리졸버에 의해 src/main/resources/templates/list.html 파일을 찾아 표시합니다.
        return "list"
    }

    /**
    특정 ID를 가진 게시글의 상세 내용을 조회하는 메서드입니다.
    HTTP GET 요청을 "/{id}" 경로로 받습니다.

    @param id URL 경로에서 추출한 게시글의 고유 ID (Long 타입)
    @param model Thymeleaf에 전달할 데이터를 담는 모델 객체
    @return 뷰 이름 ("detail" 또는 다른 처리)
     */
    @GetMapping("/{id}") // HTTP GET 요청을 /{id} 경로로 받습니다. {id}는 경로 변수입니다.
    fun findById(@PathVariable("id") id: Long, model: Model): String { // @PathVariable을 사용하여 URL의 id 값을 Long 타입으로 받습니다.
        // 게시글 조회수 처리 (서비스 호출)
        boardService.updateHits(id)
        // 게시글 상세 내용 가져오기 (서비스 호출)
        val boardDTO: BoardDTO? = boardService.findById(id) // boardService.findById(id)를 호출하여 게시글 상세 정보를 가져옵니다.

        model.addAttribute("board", boardDTO) // model.addAttribute("board", boardDTO)를 사용하여 뷰에 데이터를 전달합니다.
        System.out.println("boardDTO = " + boardDTO)  // 디버깅을 위해 콘솔에 게시글 목록 출력

        // "detail.html" 뷰 반환
        return "detail" // 최종적으로 "detail.html" 뷰를 렌더링하도록 반환합니다.
    }
}