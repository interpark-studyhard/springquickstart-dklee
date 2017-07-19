package com.yidigun.springquickstart.biz.board;

import java.util.List;

public interface BoardService {

	List<BoardVO> getBoardList(BoardVO param);

	BoardVO getBoard(BoardVO param);

	void updateBoard(BoardVO param);

	void insertBoard(BoardVO param);

	void deleteBoard(BoardVO param);

}