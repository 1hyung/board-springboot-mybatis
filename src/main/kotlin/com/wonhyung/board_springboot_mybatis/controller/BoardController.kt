package com.wonhyung.board_springboot_mybatis.controller

import com.wonhyung.board_springboot_mybatis.dto.BoardDTO // 게시글의 상세 데이터를 담는 DTO(Data Transfer Object) 클래스를 임포트합니다.
import com.wonhyung.board_springboot_mybatis.dto.BoardListDTO // 게시글 목록 조회 시 사용될 간략화된 DTO 클래스를 임포트합니다.
import com.wonhyung.board_springboot_mybatis.service.BoardService // 비즈니스 로직을 처리하는 서비스 계층의 BoardService 클래스를 임포트합니다.
import org.springframework.stereotype.Controller // 이 클래스가 Spring MVC의 컨트롤러임을 나타내는 어노테이션을 임포트합니다.
import org.springframework.ui.Model // 뷰(HTML 템플릿)로 데이터를 전달하는 데 사용되는 Model 인터페이스를 임포트합니다.
import org.springframework.web.bind.annotation.GetMapping // HTTP GET 요청을 특정 URL 경로에 매핑하는 어노테이션을 임포트합니다.
import org.springframework.web.bind.annotation.PathVariable // URL 경로에서 동적인 변수 값을 추출하는 데 사용되는 어노테이션을 임포트합니다.
import org.springframework.web.bind.annotation.PostMapping // HTTP POST 요청을 특정 URL 경로에 매핑하는 어노테이션을 임포트합니다.

@Controller // @Controller 어노테이션은 이 클래스가 Spring MVC의 컨트롤러 역할을 수행함을 Spring 프레임워크에 알립니다.
// 컨트롤러는 클라이언트(웹 브라우저)로부터의 웹 요청(HTTP 요청)을 받아들이고, 해당 요청에 대한 비즈니스 로직을 서비스 계층으로 위임하며,
// 최종적으로 응답(보통 뷰 페이지)을 생성하여 클라이언트에게 반환하는 역할을 담당합니다.
class BoardController(private val boardService: BoardService) { // BoardController의 주 생성자(Primary Constructor)를 통해 `BoardService` 타입의 객체를 의존성 주입(Dependency Injection) 받습니다.
    // 'private val boardService: BoardService'는 Spring 컨테이너가 이 BoardController의 인스턴스를 생성할 때,
    // BoardService 타입에 해당하는 빈(Bean)을 찾아 자동으로 이 생성자의 매개변수에 주입해준다는 의미입니다.
    // 이를 통해 컨트롤러는 직접 서비스를 생성하거나 관리할 필요 없이, 서비스 계층의 비즈니스 로직을 손쉽게 호출하고 활용할 수 있습니다.

    /**
     * 게시글 작성 폼(HTML 파일: `save.html`)을 사용자에게 보여주는 메서드입니다.
     * 이 메서드는 클라이언트로부터 HTTP GET 요청을 "/save" URL 경로로 받았을 때 실행됩니다.
     * @return "save"라는 문자열(뷰 이름)을 반환하며, Spring의 뷰 리졸버(예: Thymeleaf)에 의해
     * `src/main/resources/templates/save.html` 파일을 찾아 렌더링하도록 지시합니다.
     */
    @GetMapping("/save") // HTTP GET 메서드 요청이 들어오고, URL 경로가 "/save"일 때 이 `save` 함수가 호출되도록 Spring에 매핑합니다.
    fun save(): String { // `save`라는 이름의 함수를 정의하며, 문자열(`String`) 타입을 반환합니다.
        // 이 메서드는 게시글 작성 폼을 단순히 보여주는 역할만 하므로, 서버 측에서 특별한 비즈니스 로직을 수행하지 않습니다.
        return "save" // 뷰 이름 "save"를 반환하여 `save.html` 템플릿을 렌더링합니다.
    }

    /**
     * 사용자가 게시글 작성 폼(`save.html`)에서 입력한 데이터를 받아 데이터베이스에 저장하는 메서드입니다.
     * 이 메서드는 클라이언트로부터 HTTP POST 요청을 "/save" URL 경로로 받았을 때 실행됩니다.
     * 클라이언트(브라우저)로부터 전달된 폼 데이터(예: 제목, 작성자, 내용 등)는 Spring에 의해 자동으로 `BoardDTO` 객체의 해당 필드에 바인딩(매핑)됩니다.
     * @param boardDTO 클라이언트가 폼을 통해 전송한 게시글 데이터를 담는 `BoardDTO` 객체입니다. Spring이 요청 파라미터 이름(`input` 태그의 `name` 속성)과 `BoardDTO` 필드 이름을 매칭하여 자동으로 값을 채워줍니다.
     * @return 게시글 저장 처리 후, "/list" URL로 리다이렉트(`redirect`)하도록 지시합니다.
     * 이는 웹 개발에서 일반적으로 사용되는 Post-Redirect-Get (PRG) 패턴을 따르며, 새로고침 시 중복 제출을 방지하고 사용자에게 일관된 경험을 제공합니다.
     */
    @PostMapping("/save") // HTTP POST 메서드 요청이 들어오고, URL 경로가 "/save"일 때 이 `save` 함수가 호출되도록 Spring에 매핑합니다.
    // 동일한 URL 경로("/save")이지만 HTTP 메서드(GET vs POST)가 다르므로, Spring은 `GetMapping`의 `save` 함수와 이 `PostMapping`의 `save` 함수를 서로 다른 엔드포인트로 인식합니다.
    fun save(boardDTO: BoardDTO): String { // `save` 함수를 정의하며, `BoardDTO` 타입의 `boardDTO` 객체를 매개변수로 받고 문자열을 반환합니다.
        println("boardDTO = $boardDTO") // 전달받은 `boardDTO` 객체의 내용을 콘솔에 출력합니다. (디버깅 목적으로 사용)

        boardService.save(boardDTO) // 주입받은 `boardService` 객체의 `save` 메서드를 호출하여 게시글 데이터를 저장하도록 서비스 계층에 책임을 위임합니다.
        // 컨트롤러는 직접 비즈니스 로직이나 데이터베이스 접근을 처리하지 않고, 해당 책임을 서비스 계층에 넘깁니다.

        return "redirect:/list" // 게시글 저장 처리 후, 클라이언트에게 "/list" URL로 다시 요청하라고 지시하는 리다이렉트 응답을 반환합니다.
    }

    /**
     * 데이터베이스에 저장된 모든 게시글 목록을 조회하여 뷰(HTML 템플릿)에 전달하는 메서드입니다.
     * 이 메서드는 클라이언트로부터 HTTP GET 요청을 "/list" URL 경로로 받았을 때 실행됩니다.
     * @param model Spring이 제공하는 `Model` 객체입니다. 컨트롤러에서 비즈니스 로직 처리 후 얻은 데이터를 뷰(예: Thymeleaf 템플릿)로 전달할 때 사용됩니다.
     * @return "list"라는 문자열(뷰 이름)을 반환하며, Spring의 뷰 리졸버에 의해 `src/main/resources/templates/list.html` 파일을 찾아 렌더링하도록 지시합니다.
     */
    @GetMapping("/list") // HTTP GET 메서드 요청이 들어오고, URL 경로가 "/list"일 때 이 `findAll` 함수가 호출되도록 Spring에 매핑합니다.
    fun findAll(model: Model): String { // `findAll` 함수를 정의하며, `Model` 객체를 매개변수로 받고 문자열을 반환합니다.
        val boardDTOList: List<BoardListDTO> =
            boardService.findAll() // `boardService` 객체의 `findAll` 메서드를 호출하여 데이터베이스에서 모든 게시글 정보를 `List<BoardListDTO>` 형태로 가져옵니다.
        // `BoardListDTO`는 목록 조회 시 필요한 최소한의 데이터만 포함하는 DTO입니다.

        model.addAttribute(
            "boardList", boardDTOList
        ) // 가져온 게시글 목록(`boardDTOList`)을 `model` 객체에 "boardList"라는 이름으로 담습니다.
        // 이 데이터를 통해 뷰 템플릿(`list.html`)에서는 `boardList`라는 이름으로 게시글 목록에 접근하여 화면에 표시할 수 있습니다 (예: Thymeleaf의 `th:each="board : ${boardList}"`).
        System.out.println("boardDTOList = " + boardDTOList) // `boardDTOList`의 내용을 콘솔에 출력합니다. (디버깅 목적으로 사용)

        return "list" // 최종적으로 "list" 뷰 이름을 반환하여 `list.html` 템플릿을 렌더링합니다.
    }

    /**
     * 특정 고유 ID를 가진 게시글의 상세 내용을 조회하여 뷰에 전달하는 메서드입니다.
     * 이 메서드는 클라이언트로부터 HTTP GET 요청을 "/{id}" URL 경로로 받았을 때 실행됩니다.
     * @param id URL 경로(예: `http://localhost:8080/123`)에서 추출한 게시글의 고유 식별자(ID)입니다. `@PathVariable` 어노테이션을 통해 `Long` 타입으로 자동 변환되어 이 매개변수에 바인딩됩니다.
     * @param model Spring이 제공하는 `Model` 객체입니다. 조회된 게시글 상세 데이터를 뷰에 전달하는 데 사용됩니다.
     * @return "detail"이라는 문자열(뷰 이름)을 반환하며, Spring의 뷰 리졸버에 의해 `src/main/resources/templates/detail.html` 파일을 찾아 렌더링하도록 지시합니다.
     *
     * 주의: 현재 이 URL 경로("/{id}")는 `HomeController`의 `@GetMapping("/")` 매핑과 충돌할 가능성이 매우 높습니다.
     * 실제 서비스 개발 시에는 `HomeController`의 해당 매핑을 제거하거나, `/board/{id}`와 같이 더 명확하고 구체적인 URL 경로를 사용하는 것이 권장됩니다.
     */
    @GetMapping("/{id}") // HTTP GET 메서드 요청이 들어오고, URL 경로가 (예: "/1", "/123")와 같이 동적인 `id` 값을 포함할 때 이 `findById` 함수가 호출되도록 Spring에 매핑합니다.
    fun findById(
        @PathVariable("id") id: Long,
        model: Model,
    ): String { // `findById` 함수를 정의하며, `@PathVariable` 어노테이션을 사용하여 URL 경로의 `{id}` 값을 `Long` 타입의 `id` 변수에 자동으로 바인딩합니다. `Model` 객체를 매개변수로 받습니다.
        println("게시글 상세 조회 요청 - ID: $id") // 게시글 상세 조회 요청이 들어왔음을 콘솔에 출력합니다. (디버깅 목적)

        // 게시글 조회수 처리 (서비스 계층으로 위임)
        boardService.updateHits(id) // `boardService`의 `updateHits` 메서드를 호출하여 해당 `id`를 가진 게시글의 조회수를 1 증가시킵니다.

        // 게시글 상세 내용 가져오기 (서비스 계층으로 위임)
        val boardDTO: BoardDTO? =
            boardService.findById(id) // `boardService`의 `findById` 메서드를 호출하여 `id`에 해당하는 게시글의 상세 정보를 `BoardDTO` 객체로 가져옵니다.
        // `BoardDTO?`는 `boardDTO`가 `null`일 수도 있음을 나타내는 Kotlin의 널러블 타입입니다. (예: 해당 ID의 게시글이 데이터베이스에 없을 경우 `null` 반환)

        model.addAttribute("board", boardDTO) // 가져온 `boardDTO` 객체(`null`일 수도 있음)를 `model`에 "board"라는 이름으로 담아 뷰에 전달합니다.
        // 뷰 템플릿(`detail.html`)에서는 "board"라는 이름으로 이 데이터를 사용하여 게시글 내용을 표시할 수 있습니다.
        println("boardDTO = " + boardDTO) // `boardDTO`의 내용을 콘솔에 출력합니다. (디버깅 목적)

        return "detail" // 최종적으로 "detail" 뷰 이름을 반환하여 `detail.html` 템플릿을 렌더링합니다.
    }

    /**
     * 게시글 수정 폼(`update.html`)을 사용자에게 보여주기 위해 특정 게시글의 상세 정보를 조회하는 메서드입니다.
     * 이 메서드는 클라이언트로부터 HTTP GET 요청을 "/update/{id}" URL 경로로 받았을 때 실행됩니다.
     * @param id URL 경로(예: `/update/123`)에서 추출한 수정할 게시글의 고유 ID입니다. `Long` 타입으로 자동 변환됩니다.
     * @param model 뷰에 데이터를 전달하기 위한 `Model` 객체입니다. 조회된 게시글 정보를 수정 폼에 미리 채워 넣는 데 사용됩니다.
     * @return "update"라는 문자열(뷰 이름)을 반환하며, `src/main/resources/templates/update.html` 파일을 찾아 렌더링하도록 지시합니다.
     */
    @GetMapping("/update/{id}") // HTTP GET 메서드 요청이 들어오고, URL 경로가 "/update/{id}"와 같이 동적인 ID를 포함할 때 이 `update` 함수가 호출되도록 Spring에 매핑합니다.
    fun update(
        @PathVariable("id") id: Long,
        model: Model,
    ): String { // `update` 함수를 정의하며, `@PathVariable`을 사용하여 URL의 `{id}` 값을 `Long` 타입의 `id` 변수에 바인딩합니다. `Model` 객체를 매개변수로 받습니다.
        val boardDTO: BoardDTO? =
            boardService.findById(id) // `boardService`의 `findById` 메서드를 호출하여 `id`에 해당하는 게시글 상세 정보를 가져옵니다.

        model.addAttribute("board", boardDTO) // 가져온 `boardDTO` 객체를 `model`에 "board"라는 이름으로 담아 뷰에 전달합니다.
        return "update" // "update" 뷰 이름을 반환하여 `update.html` 템플릿을 렌더링합니다.
    }

    /**
     * 게시글 수정 폼에서 사용자가 제출한 수정된 데이터를 받아 데이터베이스에 업데이트하는 메서드입니다.
     * 이 메서드는 클라이언트로부터 HTTP POST 요청을 "/update/{id}" URL 경로로 받았을 때 실행됩니다.
     * @param id URL 경로(예: `/update/123`)에서 추출한 게시글의 고유 ID입니다. 이 ID는 업데이트할 게시글을 식별하는 데 사용됩니다.
     * @param boardDTO 수정 폼에서 전송된 게시글의 수정된 정보(제목, 내용, 비밀번호 등)가 담긴 `BoardDTO` 객체입니다. 이 객체에는 게시글의 `id`도 포함되어 있습니다.
     * @param model 뷰에 데이터를 전달하기 위한 `Model` 객체입니다. 수정 후 상세 페이지에 최신 정보를 전달하기 위함입니다.
     * @return 수정된 게시글의 상세 페이지("detail")를 렌더링하도록 반환합니다.
     * 일반적으로는 수정 후 해당 게시글의 상세 페이지로 리다이렉트(예: `redirect:/${boardDTO.id}`)하는 것이 더 좋은 관행(PRG 패턴)입니다.
     */
    @PostMapping("/update/{id}") // HTTP POST 메서드 요청이 들어오고, URL 경로가 "/update/{id}"와 같이 동적인 ID를 포함할 때 이 `update` 함수가 호출되도록 Spring에 매핑합니다.
    fun update(
        @PathVariable("id") id: Long,
        boardDTO: BoardDTO,
        model: Model,
    ): String { // `update` 함수를 정의하며, `@PathVariable`로 URL의 `{id}` 값을 `Long` 타입의 `id` 변수에 바인딩하고, 폼 데이터를 `BoardDTO` 객체에 바인딩합니다. `Model` 객체를 매개변수로 받습니다.
        boardService.update(boardDTO) // `boardService`의 `update` 메서드를 호출하여 `boardDTO`에 담긴 정보로 데이터베이스의 게시글을 업데이트합니다.
        val dto: BoardDTO? =
            boardService.findById(boardDTO.id) // 업데이트된 게시글의 최신 정보를 데이터베이스에서 다시 가져옵니다. (`boardDTO.id`는 `BoardDTO` 내의 ID 필드입니다.)
        model.addAttribute("board", dto) // 가져온 최신 게시글 정보를 `model`에 "board"라는 이름으로 담아 뷰에 전달합니다.
        return "detail" // "detail" 뷰 이름을 반환하여 `detail.html` 템플릿을 렌더링합니다.
    }
}
