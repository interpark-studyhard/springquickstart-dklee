package com.yidigun.springquickstart.biz.board;

import java.util.List;

import com.yidigun.springquickstart.biz.common.EntityNotFoundException;

public interface BoardDAO {

	List<BoardVO> getBoardList(BoardVO param);

	BoardVO getBoard(BoardVO param);

	void updateBoard(BoardVO param) throws EntityNotFoundException;

	void insertBoard(BoardVO param);

	void deleteBoard(BoardVO param) throws EntityNotFoundException;

}