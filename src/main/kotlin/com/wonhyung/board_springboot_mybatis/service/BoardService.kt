package com.wonhyung.board_springboot_mybatis.service

import com.wonhyung.board_springboot_mybatis.dto.BoardDTO
import com.wonhyung.board_springboot_mybatis.dto.BoardListDTO
import com.wonhyung.board_springboot_mybatis.repository.BoardRepository
import org.springframework.stereotype.Service

@Service // 1. Spring 서비스 컴포넌트임을 나타냅니다.
class BoardService(private val boardRepository: BoardRepository) { // 2. 주 생성자를 통한 의존성 주입

    // 3. 게시글 데이터를 저장하는 비즈니스 로직 메서드
    fun save(boardDTO: BoardDTO) {
        // 여기서는 단순히 Repository를 호출하지만, 실제로는 추가적인 유효성 검사,
        // 데이터 가공, 트랜잭션 관리 등의 비즈니스 로직이 들어갈 수 있습니다.
        boardRepository.save(boardDTO)
    }

    // 모든 게시글 목록을 조회하는 비즈니스 로직 메서드
    fun findAll(): List<BoardListDTO> {
        // Repository 계층에서 모든 게시글 목록을 조회하여 반환
        return boardRepository.findAll()
    }

    /**
    특정 ID를 가진 게시글의 조회수를 1 증가시키는 비즈니스 로직 메서드입니다.
    이 메서드는 컨트롤러에서 게시글 상세 조회 요청이 올 때 호출됩니다.

    @param id 조회수를 증가시킬 게시글의 고유 ID
     */
    fun updateHits(id: Long) {
        boardRepository.updateHits(id) // BoardRepository의 updateHits 메서드를 호출하여 실제 DB 업데이트를 위임합니다.
        // 여기서는 별다른 비즈니스 로직 없이 바로 Repository를 호출하지만,
        // 실제로는 조회수 중복 증가 방지(예: 세션 사용), 특정 조건에서의 조회수 증가 등
        // 추가적인 비즈니스 규칙이 적용될 수 있습니다.
    }

    fun findById(id: Long): BoardDTO? {
        return boardRepository.findById(id)
    }

    // (참고용 - 다른 save 함수 예시)
    // fun save(boardDTO: BoardDTO): Long {
    //     // boardRepository.save()가 Long을 반환하도록 설정되어 있을 경우
    //     return boardRepository.save(boardDTO)
    // }

    // 다른 서비스 메서드들이 여기에 위치합니다.
}