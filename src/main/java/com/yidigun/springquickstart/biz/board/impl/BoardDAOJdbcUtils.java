package com.yidigun.springquickstart.biz.board.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.yidigun.springquickstart.biz.board.BoardDAO;
import com.yidigun.springquickstart.biz.board.BoardVO;
import com.yidigun.springquickstart.biz.common.AbstractDAO;
import com.yidigun.springquickstart.biz.common.EntityNotFoundException;

@Repository("boardDAO1")
public class BoardDAOJdbcUtils extends AbstractDAO<BoardVO> implements BoardDAO {
	
	private static final String BOARD_INSERT = "insert into board (seq, title, writer, content) values ((select nvl(max(seq), 0)+1, from board), ? ? ?)";
	private static final String BOARD_UPDATE = "update board set title = ?, content = ? where seq = ?";
	private static final String BOARD_DELETE = "delete board where seq = ?";
	private static final String BOARD_GET = "select * from board where seq = ?";
	private static final String BOARD_LIST = "select * from board order by seq desc";
	
	@Override
	protected BoardVO mapObject(ResultSet rs) throws SQLException {
		BoardVO row = new BoardVO();
		
		row.setSeq(rs.getInt("seq"));
		row.setTitle(rs.getString("title"));
		row.setWriter(rs.getString("writer"));
		row.setContent(rs.getString("content"));
		row.setRegDate(rs.getDate("regdate"));
		row.setCnt(rs.getInt("cnt"));
		return row;
	}

	public List<BoardVO> getBoardList(BoardVO param) {
		return getList(BOARD_LIST);
	}

	public BoardVO getBoard(BoardVO param) {
		return getOne(BOARD_GET, param.getSeq());
	}

	public void updateBoard(BoardVO param) throws EntityNotFoundException {
		int rows = executeStatement(BOARD_UPDATE, param.getTitle(), param.getContent(), param.getSeq());
		if (rows <= 0)
			throw new EntityNotFoundException("There is nothing to update.");
	}

	public void insertBoard(BoardVO param) {
		int rows = executeStatement(BOARD_INSERT, param.getTitle(), param.getWriter(), param.getContent());
		if (rows <= 0)
			throw new RuntimeException("Insert failed.");
	}

	public void deleteBoard(BoardVO param) throws EntityNotFoundException {
		int rows = executeStatement(BOARD_DELETE, param.getSeq());
		if (rows <= 0)
			throw new EntityNotFoundException("There is nothing to delete.");
	}
}
