package com.kh.soomc.board.model.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.soomc.board.model.domain.Board;

@Repository("bDao")
public class BoardDao {
	@Autowired
	private SqlSession sqlSession;
	
	public int listCount() {
		return sqlSession.selectOne("Board.listCount");
	}
	
	public Board selectOne(String board_num) {
		return sqlSession.selectOne("Board.selectOne", board_num);
	}
	
	public List<Board> searchList(String keyword){
		return sqlSession.selectList("Board.searchList", keyword);
	}
	
	public List<Board> selectList(int startPage, int limit) {
		int startRow = (startPage-1)*limit;
		RowBounds row = new RowBounds(startRow, limit);
		return sqlSession.selectList("Board.selectList", null, row);
	}
	
	public int insertBoard(Board b) {
		return sqlSession.insert("Board.insertBoard", b);
	}
	
	// expression = "execution(* com.kh.soomc..BoardDao.selectList()))"
	// expression = "execution(* com.kh.soomc..*Dao.*()))"
	// expression = "execution(* com.kh.soomc.board.model.dao.BoardDao.*()))"
	// expression = "execution(* com.kh.soomc.board.model.dao.BoardDao.*(String)))"
	// expression = "execution(* com.kh.soomc.board.model.dao.BoardDao.*(int, int)))"
	// expression = "execution(* com.kh.soomc.board.model.dao.BoardDao.*(Board,..)))"
	// expression = "execution(List<T> com.kh.soomc.board.model.dao.BoardDao.*(..)))"
	// expression = "execution(* com.kh.soomc..Board*.update*(..)))"
	
	public int addReadCount(String board_num) {
		return sqlSession.update("Board.addReadCount", board_num);
	}
	
	public int updateBoard(Board b) {
		return sqlSession.update("Board.updateBoard", b);
	}
	
	public int deleteBoard(String board_num) {
		return sqlSession.delete("Board.deleteBoard", board_num);
	}
	
}
