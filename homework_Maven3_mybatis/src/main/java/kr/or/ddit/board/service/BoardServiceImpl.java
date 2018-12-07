package kr.or.ddit.board.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.apache.commons.io.FileUtils;
import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.CommonException;
import kr.or.ddit.ServiceResult;
import kr.or.ddit.board.BoardException;
import kr.or.ddit.board.dao.BoardDAOImpl;
import kr.or.ddit.board.dao.IBoardDAO;
import kr.or.ddit.board.dao.IPdsDAO;
import kr.or.ddit.board.dao.PdsDAOImpl;
import kr.or.ddit.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.PdsVO;

public class BoardServiceImpl implements IBoardService {
	IBoardDAO boardDAO = new BoardDAOImpl();
	IPdsDAO pdsDAO = new PdsDAOImpl();

	@Override
	public ServiceResult createBoard(BoardVO board) {
		try (SqlSession session = CustomSqlSessionFactoryBuilder.getSqlSessionFactory().openSession(false);) {
			ServiceResult result = ServiceResult.FAILED;
			int cnt = boardDAO.insertBoard(board, session);
			int count = 1;
			String saveUrl = "d:/boardFiles";
			File saveFolder = new File(saveUrl);// 폴더 생성해줌
			if (!saveFolder.exists())
				saveFolder.mkdirs();

			if (cnt > 0) {
				if (board.getPdsList()!=null) {
					count += pdsDAO.insertPdsList(board, session);
					for (PdsVO pds : board.getPdsList()) {

						try (InputStream in = pds.getItem().getInputStream();) {
							FileUtils.copyInputStreamToFile(in, new File(saveFolder, pds.getPds_savename()));
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					if (count == board.getPdsList().size()) {
						
						result = ServiceResult.OK;
						session.commit();
						
					}
				}else {
					result = ServiceResult.OK;
					
				}
			}

			return result;
		}
	}

	@Override
	public long retriveBoardCount(PagingInfoVO<BoardVO> pagingVO) {
		return boardDAO.selectTotalRecord(pagingVO);
	}

	@Override
	public List<BoardVO> retriveBoardList(PagingInfoVO<BoardVO> pagingVO) {

		return boardDAO.selectBoardList(pagingVO);
	}

	@Override
	public BoardVO retriveBoard(long bo_no) {
		BoardVO board = boardDAO.selectBoard(bo_no);
		if (board == null) {
			throw new CommonException("Board 에러..");
		}
		boardDAO.incrementHit(bo_no);
		board.setBo_hit(board.getBo_hit() + 1);

		return board;
	}

	@Override
	public ServiceResult modifyBoard(BoardVO board) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResult removeBoard(BoardVO board) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PdsVO downLoadPds(long pds_no) {
		PdsVO pd= pdsDAO.selectPds(pds_no);
		if(pd==null) {
			throw new BoardException();
		}
		return pd;
	}

}
