package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.vo.BoardBookVO;
import kr.or.ddit.vo.PagingVO;

public interface IBoardBookService {
	public long totalRecordCount();

	public List<BoardBookVO> listBoardBook(PagingVO paging);

	public BoardBookVO selectBoardBook(Long bo_no);

	public ServiceResult insertBoardBook(BoardBookVO board);

	public ServiceResult deleteBoardBook(BoardBookVO bo_no);

	public ServiceResult updateBoardBook(BoardBookVO board);

}
