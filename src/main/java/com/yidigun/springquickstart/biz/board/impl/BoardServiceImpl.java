package com.yidigun.springquickstart.biz.board.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yidigun.springquickstart.biz.board.BoardService;
import com.yidigun.springquickstart.biz.board.BoardVO;
import com.yidigun.springquickstart.biz.common.EntityNotFoundException;

@Service("boardService")
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardDAO boardDAO;

	@Override
	public List<BoardVO> getBoardList(BoardVO param) {
		return boardDAO.getBoardList(param);
	}

	@Override
	public BoardVO getBoard(BoardVO param) {
		return boardDAO.getBoard(param);
	}

	@Override
	public void updateBoard(BoardVO param) throws EntityNotFoundException {
		boardDAO.updateBoard(param);		
	}

	@Override
	public void insertBoard(BoardVO param) {
		try {
			boardDAO.insertBoard(param);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public void deleteBoard(BoardVO param) throws EntityNotFoundException {
		boardDAO.deleteBoard(param);
	}

}
