package com.wonhyung.board_springboot_mybatis.repository

import com.wonhyung.board_springboot_mybatis.dto.BoardDTO // 게시글 상세 DTO 클래스를 임포트합니다.
import com.wonhyung.board_springboot_mybatis.dto.BoardListDTO // 게시글 목록 DTO 클래스를 임포트합니다.
import org.mybatis.spring.SqlSessionTemplate // MyBatis와 Spring의 연동을 담당하며 SQL 쿼리 실행에 사용되는 `SqlSessionTemplate` 클래스를 임포트합니다.
import org.springframework.stereotype.Repository // 이 클래스가 Spring의 Repository 컴포넌트임을 나타내는 어노테이션을 임포트합니다.

@Repository // @Repository 어노테이션은 이 클래스가 데이터베이스 접근(Data Access Layer, DAO) 계층의 컴포넌트임을 Spring 프레임워크에 알립니다.
// Repository는 주로 데이터베이스와의 직접적인 통신을 담당하며, CRUD(Create, Read, Update, Delete) 작업을 수행합니다.
class BoardRepository(private val sql: SqlSessionTemplate) { // 주 생성자를 통한 의존성 주입. `SqlSessionTemplate` 인스턴스를 주입받습니다.
    // 이 `sql` 객체(`SqlSessionTemplate` 타입)를 통해 MyBatis 매퍼 파일에 정의된 SQL 쿼리들을 실행할 수 있습니다.
    // Spring이 `SqlSession`의 생명주기 및 트랜잭션 관리를 담당하므로, 개발자는 SQL 쿼리 실행에만 집중할 수 있습니다.

    /**
     * 새로운 게시글 데이터를 데이터베이스의 `board_table`에 저장하는 메서드입니다.
     * @param boardDTO 데이터베이스에 저장할 게시글 정보가 담긴 `BoardDTO` 객체입니다.
     */
    fun save(boardDTO: BoardDTO) {
        // `sql.insert()` 메서드를 호출하여 데이터를 삽입하는 SQL 쿼리를 실행합니다.
        // "Board.save"는 `board-mapper.xml` 파일의 `<mapper namespace="Board">` 아래에 정의된 `<insert id="save">` SQL 문의 고유 ID를 나타냅니다.
        // `boardDTO` 객체가 해당 SQL 문의 파라미터로 전달되며, MyBatis는 DTO 필드와 SQL 쿼리의 `#{필드명}`을 자동으로 매핑하여 값을 바인딩합니다.
        sql.insert("Board.save", boardDTO)
    }

    /**
     * 데이터베이스의 `board_table`에서 모든 게시글 정보를 조회하여 리스트 형태로 반환하는 메서드입니다.
     * 이 메서드는 게시글 목록 화면에 필요한 간략화된 정보만 조회합니다.
     * @return 조회된 `BoardListDTO` 객체들을 담은 `List`를 반환합니다. 만약 게시글이 없을 경우 빈 리스트를 반환합니다.
     */
    fun findAll(): List<BoardListDTO> { // 반환 타입이 `List<BoardListDTO>`로 변경되었습니다.
        // `sql.selectList()` 메서드를 호출하여 여러 개의 레코드(게시글)를 조회하는 SQL 쿼리를 실행합니다.
        // "Board.findAll"은 `board-mapper.xml`에 정의된 `<select id="findAll">` SQL 문의 고유 ID입니다.
        // 이 쿼리는 데이터베이스의 모든 게시글 데이터를 가져와 `List<BoardListDTO>` 타입으로 자동으로 매핑합니다.
        return sql.selectList("Board.findAll")
    }

    /**
     * 데이터베이스의 `board_table`에서 특정 ID를 가진 게시글의 조회수(boardHits)를 1 증가시키는 메서드입니다.
     * @param id 조회수를 증가시킬 게시글의 고유 ID(`Long` 타입)입니다.
     */
    fun updateHits(id: Long) {
        // `sql.update()` 메서드를 호출하여 데이터베이스의 데이터를 갱신하는 SQL 쿼리를 실행합니다.
        // "Board.updateHits"는 `board-mapper.xml`에 정의된 `<update id="updateHits">` SQL 문의 고유 ID입니다.
        // `id`는 이 SQL 문의 파라미터로 전달될 ID 값입니다. MyBatis는 이 값을 쿼리의 `#{id}`에 매핑합니다.
        sql.update("Board.updateHits", id)
    }

    /**
     * 데이터베이스의 `board_table`에서 특정 ID를 가진 게시글의 상세 정보를 조회하여 반환하는 메서드입니다.
     * @param id 조회할 게시글의 고유 ID(`Long?` 타입)입니다.
     * @return 조회된 `BoardDTO` 객체를 반환합니다. 만약 해당 ID의 게시글을 찾을 수 없을 경우 `null`을 반환합니다.
     */
    fun findById(id: Long?): BoardDTO? { // 파라미터 `id`는 `Long?` (널러블) 타입으로, 반환 타입은 `BoardDTO?` (널러블)입니다.
        // `sql.selectOne()` 메서드를 호출하여 단일 레코드(게시글)를 조회하는 SQL 쿼리를 실행합니다.
        // "Board.findById"는 `board-mapper.xml`에 정의된 `<select id="findById">` SQL 문의 고유 ID입니다.
        // `id`는 이 SQL 문의 파라미터로 전달될 값입니다.
        // `selectOne`은 조회 결과가 없으면 `null`을 반환하므로, 반환 타입은 `BoardDTO?`로 명시됩니다.
        return sql.selectOne("Board.findById", id)
    }

    /**
     * 데이터베이스의 `board_table`에서 특정 게시글 데이터를 업데이트하는 메서드입니다.
     * @param boardDTO 업데이트할 게시글 정보(고유 ID 포함)가 담긴 `BoardDTO` 객체입니다.
     */
    fun update(boardDTO: BoardDTO) {
        // `sql.update()` 메서드를 호출하여 데이터베이스의 데이터를 갱신하는 SQL 쿼리를 실행합니다.
        // "Board.update"는 `board-mapper.xml`에 정의된 `<update id="update">` SQL 문의 고유 ID입니다.
        // `boardDTO`는 이 SQL 문의 파라미터로 전달될 DTO 객체입니다. MyBatis는 이 객체의 필드와 SQL 쿼리의 `#{필드명}`을 매핑합니다.
        sql.update("Board.update", boardDTO)
    }
}