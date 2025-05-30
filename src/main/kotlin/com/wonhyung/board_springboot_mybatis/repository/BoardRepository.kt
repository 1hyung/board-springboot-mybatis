package com.wonhyung.board_springboot_mybatis.repository

import com.wonhyung.board_springboot_mybatis.dto.BoardDTO
import org.mybatis.spring.SqlSessionTemplate
import org.springframework.stereotype.Repository

@Repository // 1. Spring의 Repository 컴포넌트임을 나타냅니다.
class BoardRepository(private val sql: SqlSessionTemplate) { // 2. 주 생성자를 통한 의존성 주입

    // 3. 게시글 데이터를 데이터베이스에 저장하는 메서드
    fun save(boardDTO: BoardDTO) {
        // 'Board.save'는 MyBatis XML Mapper 파일(예: BoardMapper.xml)에 정의된 SQL 문의 ID를 나타냅니다.
        // boardDTO 객체가 해당 SQL 문의 파라미터로 전달됩니다.
        sql.insert("Board.save", boardDTO)
    }
}