package kr.or.ddit.board.dao;

import java.util.List;

import kr.or.ddit.vo.BoardBookVO;
import kr.or.ddit.vo.PagingVO;

public interface IBoardBookDAO {
	
	public long totalRecordCount();
	
	public List<BoardBookVO> listBoardBook(PagingVO paging);
	
	public BoardBookVO selectBoardBook(Long bo_no);

	public int insertBoardBook(BoardBookVO board);
	
	public int deleteBoardBook(Long bo_no);
	
	public int updateBoardBook(BoardBookVO board);
	
	
	
	
}
