package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.board.BoardException;
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
		BoardBookVO boardBookVO=dao.selectBoardBook(bo_no);
		if(boardBookVO==null) {
			throw new BoardException();
		}
		return boardBookVO;
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
	public ServiceResult deleteBoardBook(BoardBookVO bo_no) {
		ServiceResult res=ServiceResult.INVALIDPASSWORD;
		BoardBookVO boardBookVO= selectBoardBook(bo_no.getBo_no());
		if(boardBookVO.getBo_pass().equals(bo_no.getBo_pass())) {
			int cnt=dao.deleteBoardBook(bo_no.getBo_no());
			if(cnt>0) {
				res=ServiceResult.OK;
			}else {
				res=ServiceResult.FAILED;
			}
		}
		return res;
	}

	@Override
	public ServiceResult updateBoardBook(BoardBookVO board) {
		ServiceResult res=ServiceResult.INVALIDPASSWORD;
		BoardBookVO boardBookVO= selectBoardBook(board.getBo_no());
		if(boardBookVO.getBo_pass().equals(board.getBo_pass())) {
			int cnt=dao.updateBoardBook(board);
			if(cnt>0) {
				res=ServiceResult.OK;
			}else {
				res=ServiceResult.FAILED;
			}
		}
		return res;
	}

}
