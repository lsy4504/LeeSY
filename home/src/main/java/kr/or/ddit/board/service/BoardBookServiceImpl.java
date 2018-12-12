package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.board.dao.BoardBookDAOImpl;
import kr.or.ddit.board.dao.IBoardBookDAO;
import kr.or.ddit.vo.BoardBookVO;
import kr.or.ddit.vo.PagingVO;

public class BoardBookServiceImpl implements IBoardBookService {
	IBoardBookDAO dao= new BoardBookDAOImpl();
	@Override
	public long totalRecordCount() {
		return dao.totalRecordCount();
	}

	@Override
	public List<BoardBookVO> listBoardBook(PagingVO paging) {
		return dao.listBoardBook(paging);
	}

	@Override
	public BoardBookVO selectBoardBook(Long bo_no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResult insertBoardBook(BoardBookVO board) {
		int cnt =dao.insertBoardBook(board);
		ServiceResult res= ServiceResult.FAILED;
		if(cnt>0) {
			res=ServiceResult.OK;
		}
		return res;
	}

	@Override
	public ServiceResult deleteBoardBook(Long bo_no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResult updateBoardBook(BoardBookVO board) {
		// TODO Auto-generated method stub
		return null;
	}

}
