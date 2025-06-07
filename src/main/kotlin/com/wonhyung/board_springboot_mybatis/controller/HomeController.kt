package com.wonhyung.board_springboot_mybatis.controller

import org.springframework.stereotype.Controller // 이 클래스가 Spring MVC의 컨트롤러임을 나타내는 어노테이션을 임포트합니다.
import org.springframework.web.bind.annotation.GetMapping // HTTP GET 요청을 특정 URL 경로에 매핑하는 어노테이션을 임포트합니다.

@Controller // 이 클래스가 Spring MVC의 컨트롤러 역할을 수행함을 Spring 프레임워크에 알립니다.
// 이 컨트롤러는 주로 웹 애플리케이션의 홈 페이지 또는 기본 경로 요청을 처리하는 역할을 합니다.
class HomeController {

    /**
     * 애플리케이션의 기본 경로("/")로의 요청을 처리하여 홈 페이지(`index.html`)를 보여주는 메서드입니다.
     * @return "index"라는 문자열(뷰 이름)을 반환하며, Spring의 뷰 리졸버에 의해 `src/main/resources/templates/index.html` 파일을 찾아 렌더링하도록 지시합니다.
     */
    @GetMapping("/") // HTTP GET 메서드 요청이 들어오고, URL 경로가 "/" (루트 경로)일 때 이 `index` 함수가 호출되도록 Spring에 매핑합니다.
    fun index(): String { // `index`라는 이름의 함수를 정의하며, 문자열(`String`) 타입을 반환합니다.
        println("HomeController.index") // "HomeController.index"라는 메시지를 콘솔에 출력합니다. (디버깅 목적)
        return "index" // 뷰 이름 "index"를 반환하여 `index.html` 템플릿을 렌더링합니다.
    }
}