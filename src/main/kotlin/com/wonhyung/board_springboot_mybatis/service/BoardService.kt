package com.wonhyung.board_springboot_mybatis.service

import com.wonhyung.board_springboot_mybatis.repository.BoardRepository
import org.springframework.stereotype.Service

@Service // 1. Spring 서비스 컴포넌트임을 나타냅니다.
class BoardService (private val boardRepository: BoardRepository) { // 2. 주 생성자를 통한 의존성 주입

    // 예시: 게시글을 저장하는 서비스 메서드
    // fun save(boardDTO: BoardDTO): Long {
    //     // boardRepository를 사용하여 boardDTO의 내용을 DB에 저장하는 로직
    //     // 예: return boardRepository.save(boardDTO)
    //     return 0L // 임시 반환 값
    // }

    // 다른 서비스 메서드들이 여기에 위치
}