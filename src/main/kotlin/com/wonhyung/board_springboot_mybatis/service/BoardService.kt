package com.wonhyung.board_springboot_mybatis.service

import com.wonhyung.board_springboot_mybatis.dto.BoardDTO // 게시글 상세 DTO 클래스를 임포트합니다.
import com.wonhyung.board_springboot_mybatis.dto.BoardListDTO // 게시글 목록 DTO 클래스를 임포트합니다.
import com.wonhyung.board_springboot_mybatis.repository.BoardRepository // 데이터베이스 접근을 담당하는 BoardRepository 클래스를 임포트합니다.
import org.springframework.stereotype.Service // 이 클래스가 Spring 서비스 컴포넌트임을 나타내는 어노테이션을 임포트합니다.

@Service // @Service 어노테이션은 이 클래스가 Spring의 서비스 계층 컴포넌트임을 Spring 프레임워크에 알립니다.
// 서비스 계층은 애플리케이션의 핵심 비즈니스 로직을 처리하며, 주로 여러 리포지토리의 메서드를 조합하거나 트랜잭션을 관리하는 역할을 합니다.
class BoardService(private val boardRepository: BoardRepository) { // 주 생성자를 통한 의존성 주입. `BoardRepository` 인스턴스를 주입받습니다.
    // 서비스를 통해 데이터베이스 작업(Repository 계층)을 수행합니다.

    /**
     * 새로운 게시글 데이터를 데이터베이스에 저장하는 비즈니스 로직 메서드입니다.
     * 이 메서드는 컨트롤러로부터 게시글 정보를 받아 Repository로 전달합니다.
     * @param boardDTO 저장할 게시글 정보가 담긴 `BoardDTO` 객체입니다.
     */
    fun save(boardDTO: BoardDTO) {
        // 여기서는 단순히 `boardRepository`의 `save` 메서드를 호출하여 데이터베이스에 저장을 위임합니다.
        // 하지만 실제 복잡한 애플리케이션에서는 게시글 유효성 검사, 관련 데이터 가공,
        // 다른 서비스 호출(예: 파일 저장 서비스), 트랜잭션 관리 등의 추가적인 비즈니스 로직이 이 메서드 내부에 들어갈 수 있습니다.
        boardRepository.save(boardDTO)
    }

    /**
     * 데이터베이스에 저장된 모든 게시글 목록을 조회하는 비즈니스 로직 메서드입니다.
     * 이 메서드는 게시글 목록 화면에 필요한 간략화된 정보만 조회하여 반환합니다.
     * @return 조회된 `BoardListDTO` 객체들을 담은 `List`를 반환합니다.
     */
    fun findAll(): List<BoardListDTO> { // 반환 타입이 `List<BoardListDTO>`로 변경되었습니다.
        // `boardRepository`를 통해 데이터베이스에서 모든 게시글 목록을 조회하고, 그 결과를 컨트롤러 계층으로 반환합니다.
        return boardRepository.findAll()
    }

    /**
     * 특정 ID를 가진 게시글의 조회수(boardHits)를 1 증가시키는 비즈니스 로직 메서드입니다.
     * 이 메서드는 컨트롤러에서 게시글 상세 조회 요청 시 호출됩니다.
     * @param id 조회수를 증가시킬 게시글의 고유 ID(`Long` 타입)입니다.
     */
    fun updateHits(id: Long) {
        // `boardRepository`의 `updateHits` 메서드를 호출하여 실제 데이터베이스의 조회수 업데이트를 위임합니다.
        // 이 단계에서는 단순히 Repository를 호출하지만, 나중에 특정 조건에서만 조회수를 증가시키거나
        // 조회수 중복 증가를 방지(예: 사용자 세션 기반)하는 등의 비즈니스 규칙이 추가될 수 있습니다.
        boardRepository.updateHits(id)
    }

    /**
     * 특정 ID를 가진 게시글의 상세 정보를 조회하는 비즈니스 로직 메서드입니다.
     * @param id 조회할 게시글의 고유 ID(`Long?` 타입)입니다.
     * @return 조회된 `BoardDTO` 객체를 반환합니다. 만약 해당 ID의 게시글을 찾을 수 없을 경우 `null`을 반환합니다.
     */
    fun findById(id: Long?): BoardDTO? { // 파라미터 `id`는 `Long?` (널러블) 타입으로, 반환 타입은 `BoardDTO?` (널러블)입니다.
        // `boardRepository`를 통해 데이터베이스에서 특정 게시글을 조회하고, 그 결과를 컨트롤러 계층으로 반환합니다.
        return boardRepository.findById(id)
    }

    /**
     * 게시글 데이터를 업데이트하는 비즈니스 로직 메서드입니다.
     * 이 메서드는 컨트롤러로부터 수정된 게시글 정보를 받아 Repository로 전달하여 업데이트를 수행합니다.
     * @param boardDTO 업데이트할 게시글 정보(고유 ID 포함)가 담긴 `BoardDTO` 객체입니다.
     */
    fun update(boardDTO: BoardDTO) {
        // `boardRepository`의 `update` 메서드를 호출하여 데이터베이스의 게시글을 업데이트하도록 위임합니다.
        // 이 단계에서는 단순히 Repository를 호출하지만, 실제 애플리케이션에서는 업데이트 전 유효성 검사,
        // 권한 확인, 다른 관련 데이터 처리 등 추가적인 비즈니스 로직이 필요할 수 있습니다.
        boardRepository.update(boardDTO)
    }
}