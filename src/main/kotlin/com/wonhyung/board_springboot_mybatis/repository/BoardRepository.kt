package com.wonhyung.board_springboot_mybatis.repository

import com.wonhyung.board_springboot_mybatis.dto.BoardDTO
import com.wonhyung.board_springboot_mybatis.dto.BoardListDTO
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

    // 데이터베이스에서 모든 게시글 정보를 조회하여 반환하는 메서드
    fun findAll(): List<BoardListDTO> {
        // MyBatis의 SqlSessionTemplate을 통해 'Board.findAll' 쿼리를 실행
        return sql.selectList("Board.findAll")
    }

    /**
    데이터베이스에서 특정 ID를 가진 게시글의 조회수를 1 증가시키는 메서드입니다.
    MyBatis 매퍼 파일에 정의된 'Board.updateHits' 쿼리를 실행합니다.

    @param id 조회수를 증가시킬 게시글의 고유 ID
     */
    fun updateHits(id: Long) {
        sql.update("Board.updateHits", id)
        // sql.update(): SqlSessionTemplate의 update 메소드를 호출하여 데이터를 갱신합니다.
        // "Board.updateHits": 호출할 MyBatis XML Mapper 파일에 정의된 SQL 문의 ID를 나타냅니다.
        // id: 해당 SQL 문의 파라미터로 전달될 ID 값입니다. MyBatis는 이 값을 쿼리의 #{id}에 매핑합니다.
    }

    fun findById(id: Long): BoardDTO? {
        return sql.selectOne("Board.findById", id)
    }
}