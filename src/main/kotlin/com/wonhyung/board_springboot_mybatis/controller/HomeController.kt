package com.wonhyung.board_springboot_mybatis.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HomeController {

    @GetMapping("/")
    fun index(): String {
        println("HomeController.index")
        return "index"
    }
}