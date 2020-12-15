package com.kh.soomc.board.model.service;

import java.util.List;

import com.kh.soomc.board.model.domain.Board;

public interface BoardService {
//	public int listCount();
//	
//	public int insertBoard(Board b);
//	
//	public List<Board> selectList();
	
	//같은 패키지 안 이므로 public 쓸 필요 없음

	int totlaCount();

	Board selectBoard(int chk, String board_num);

	List<Board> selectList(int startPage, int limit);

	List<Board> selectSearch(String keyword);
	
	void insertBoard(Board b);

	Board updateBoard(Board b);

	void deleteBoard(String board_num);
}
