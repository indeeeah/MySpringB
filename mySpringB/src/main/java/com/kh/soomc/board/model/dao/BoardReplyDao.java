package com.kh.soomc.board.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.soomc.board.model.domain.BoardReply;

@Repository("brDao")
public class BoardReplyDao {
	@Autowired
	private SqlSession sqlSession;

	// 게시글에 해당하는 댓글 조회
	public List<BoardReply> selectList(String board_num) {
		return sqlSession.selectList("BoardReply.selectBoardReplyAll", board_num);
	}

	// 단일 댓글 조회
	public BoardReply selectOne(String comment_id) {
		return sqlSession.selectOne("BoardReply.selectBoardReply", comment_id);
	}

	// 댓글 입력
	public int insertBoardReply(BoardReply br) {
		return sqlSession.insert("BoardReply.insertBoardReply", br);
	}

	// 댓글 수정
	public int updateBoardReply(BoardReply br) {
		System.out.println(br);
		return sqlSession.update("BoardReply.updateBoardReply", br);
	}

	// 댓글 삭제
	public int deleteBoardReply(BoardReply br) {
		System.out.println(br);
		return sqlSession.delete("BoardReply.deleteBoardReply", br);
	}
}
