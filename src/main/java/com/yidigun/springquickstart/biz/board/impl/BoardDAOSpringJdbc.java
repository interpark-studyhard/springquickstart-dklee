package com.yidigun.springquickstart.biz.board.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.yidigun.springquickstart.biz.board.BoardDAO;
import com.yidigun.springquickstart.biz.board.BoardVO;
import com.yidigun.springquickstart.biz.common.EntityNotFoundException;

@Repository("boardDAO2")
public class BoardDAOSpringJdbc extends JdbcDaoSupport implements BoardDAO {
	
	private static final String BOARD_INSERT = "insert into board (seq, title, writer, content) values ((select nvl(max(seq), 0)+1, from board), ? ? ?)";
	private static final String BOARD_UPDATE = "update board set title = ?, content = ? where seq = ?";
	private static final String BOARD_DELETE = "delete board where seq = ?";
	private static final String BOARD_GET = "select * from board where seq = ?";
	private static final String BOARD_LIST = "select * from board order by seq desc";
	
	private RowMapper<BoardVO> boardRowMapper = new RowMapper<BoardVO>() {
		@Override
		public BoardVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			BoardVO row = new BoardVO();
			
			row.setSeq(rs.getInt("seq"));
			row.setTitle(rs.getString("title"));
			row.setWriter(rs.getString("writer"));
			row.setContent(rs.getString("content"));
			row.setRegDate(rs.getDate("regdate"));
			row.setCnt(rs.getInt("cnt"));
			return row;
		}
	};
	
	public static class BoardRowMapper implements RowMapper<BoardVO> {

		@Override
		public BoardVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			BoardVO row = new BoardVO();
			
			row.setSeq(rs.getInt("seq"));
			row.setTitle(rs.getString("title"));
			row.setWriter(rs.getString("writer"));
			row.setContent(rs.getString("content"));
			row.setRegDate(rs.getDate("regdate"));
			row.setCnt(rs.getInt("cnt"));
			return row;
		}
	}

	public List<BoardVO> getBoardList(BoardVO param) {
		return getJdbcTemplate().query(BOARD_LIST, boardRowMapper);
	}

	public BoardVO getBoard(BoardVO param) {
		return getJdbcTemplate().queryForObject(BOARD_GET, boardRowMapper, param.getSeq());
	}

	public void updateBoard(BoardVO param) throws EntityNotFoundException {
		int rows = getJdbcTemplate().update(BOARD_UPDATE, param.getTitle(), param.getContent(), param.getSeq());
		if (rows <= 0)
			throw new EntityNotFoundException("There is nothing to update.");
	}

	public void insertBoard(BoardVO param) {
		int rows = getJdbcTemplate().update(BOARD_INSERT, param.getTitle(), param.getWriter(), param.getContent());
		if (rows <= 0)
			throw new RuntimeException("Insert failed.");
	}

	public void deleteBoard(BoardVO param) throws EntityNotFoundException {
		int rows = getJdbcTemplate().update(BOARD_DELETE, param.getSeq());
		if (rows <= 0)
			throw new EntityNotFoundException("There is nothing to delete.");
	}
}
