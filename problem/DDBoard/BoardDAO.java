package problem.DDBoard;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import mybatis.SqlMapConfig;

public class BoardDAO {
	SqlSessionFactory sqlSessionFactory = SqlMapConfig.getSqlSession();
	SqlSession sqlSession;
	
	List<BoardDTO> list;
	int result;
	
	public void boardSelect() {		
		sqlSession = sqlSessionFactory.openSession(true);
		try {			
			list = sqlSession.selectList("board.boardSelect");
			printQuery(list);					
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			sqlSession.close();			
		}
	}
	
	public void boardInsert(BoardDTO bDto) {
		sqlSession = sqlSessionFactory.openSession(true);
		try {
			
			result = sqlSession.insert("board.boardInsert", bDto);
			if(result > 0) {
				System.out.println("▨▧ 게시글이 등록되었습니다.");
			} else {
				System.out.println("▨▧ 게시글 등록 실패");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			sqlSession.close();
			
		}
	}
	
	public void boardUpdate(BoardDTO bDto) {
		sqlSession = sqlSessionFactory.openSession(true);
		try {
			HashMap<String, Object> map = new HashMap<>();
			map.put("bno", bDto.getBno());
			map.put("writer", bDto.getWriter());
			
			result = sqlSession.selectOne("board.writerCheck", map);
			
			if (result == 0) {
				System.out.println("수정권한이 없는 게시물입니다.");
				return;
			} 
			
			result = sqlSession.update("board.boardUpdate", bDto);
			
			if(result > 0) {
				System.out.println("▨▧ " + bDto.getBno() + "번 게시글이 수정되었습니다.");
			} else {
				System.out.println("▨▧ 게시글 수정 실패");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			sqlSession.close();
			
		}
	}
	
	public void boardDelete(int bno, String writer) {
		sqlSession = sqlSessionFactory.openSession(true);
		try {
			HashMap<String, Object> map = new HashMap<>();
			map.put("bno", bno);
			map.put("writer", writer);
			
			result = sqlSession.selectOne("board.writerCheck", map);
			
			if (result == 0) {
				System.out.println("삭제권한이 없는 게시물입니다.");
				return;
			}
			
			result = sqlSession.delete("board.boardDelete", bno);
			if(result > 0) {
				System.out.println("▨▧ " + bno + "번 게시글이 삭제되었습니다.");
			} else {
				System.out.println("▨▧ 게시글 삭제 실패");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			sqlSession.close();
			
		}
	}
		
	public void boardSearch(String search) {
		sqlSession = sqlSessionFactory.openSession(true);
		try {
			list = sqlSession.selectList("board.boardSearch", "%"+search+"%");
			printQuery(list);					
			System.out.println("▩▩ \"" + search + "\"로 검색한 결과 " + list.size() + "건 검색되었습니다.");
			System.out.println("▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			sqlSession.close();			
		}
	}
	
	public void boardSort(int column, int order) {
		sqlSession = sqlSessionFactory.openSession(true);
		try {
			HashMap<String, Integer> map = new HashMap<>();
			map.put("column", column);
			map.put("order", order);			
			list = sqlSession.selectList("board.boardSort", map);
			printQuery(list);					
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			sqlSession.close();			
		}
	}
	
	public void boardView(int bno) {
		sqlSession = sqlSessionFactory.openSession(true);
		try {
			BoardDTO bDto = new BoardDTO();
			bDto = sqlSession.selectOne("board.boardView", bno);
			System.out.println("▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧");
			System.out.println("▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧");
			System.out.println("〓 번호: " + bDto.getBno() + " / 제목: " + bDto.getTitle() + " / 조회수: " + bDto.getViewcnt());
			System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("〓 내용: " + bDto.getContent());			
			System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("〓 작성자: " + bDto.getWriter() + " / 작성일자: " + bDto.getRegdate());
			System.out.println("▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			sqlSession.close();
			
		}
		
	}
	
	public int viewCntPlus(int bno) {
		sqlSession = sqlSessionFactory.openSession(true);		
		try {
			result = sqlSession.update("board.viewCntPlus", bno);
			if (result > 0) {
				System.out.println("▩▩ " + bno + "번 게시글 조회수증가 >>>>>");
			} else {
				System.out.println("▩▩  조회수 증가 실패. 관리자에게 문의하세요.");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			sqlSession.close();
			
		}
		return result;
	}
	
	// 조회된 결과를 출력하는 함수
	public void printQuery(List<BoardDTO> list) {
		System.out.println("▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩");
		System.out.println("〓 번호\t 제목\t\t 내용\t\t\t\t 작성자\t 조회수\t 작성일자");
		System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		for (BoardDTO line : list) {
			System.out.println(line.toString());
		}
		System.out.println("▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩");		
	}
	
//		BoardDTO bDto = new BoardDTO(title, content, writer);
//		bDao.boardInsert(bDto);
		
	
//	Connection conn;
//	PreparedStatement pstmt;
//	ResultSet rs;
//	ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
//
//	public void boardInsert(BoardDTO bDto) {
//
//		try {
//			conn = DBManager.getConnection();
//			String sql = "INSERT INTO tbl_board(bno,title,content,writer) "
//					+ "VALUES(seq_board.NEXTVAL,?,?,?) ";
//			
//			pstmt = conn.prepareStatement(sql);			
//			pstmt.setString(1, bDto.getTitle());
//			pstmt.setString(2, bDto.getContent());
//			pstmt.setString(3, bDto.getWriter());
//			
//			int result = pstmt.executeUpdate();
//			if (result == 1) {
//				System.out.println("▨▧ 게시글 " + bDto.getTitle() + " 등록에 성공했습니다." );
//			} else {
//				System.out.println("▨▧ 게시글 등록에 실패했습니다. 관리자에게 문의하세요." );
//			}
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//			
//		} finally {
//			try {
//				conn.close();
//				pstmt.close();
//				rs.close();
//			} catch (Exception e2) {
//				// TODO: handle exception
//				e2.printStackTrace();
//				
//			}
//		}
//	}
//		
//	
//	public void boardUpdate(BoardDTO bDto) {
//
//		try {
//			conn = DBManager.getConnection();
//			String sql = "Update tbl_board "
//					+"SET "
//					+"title = ? "
//					+"content = ? "
//					+"writer = ? "
//					+"WHERE bno = ? ";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, bDto.getTitle());
//			pstmt.setString(2, bDto.getContent());
//			pstmt.setString(3, bDto.getWriter());
//			pstmt.setInt(4, bDto.getBno());
//			
//			int result = pstmt.executeUpdate();
//			if (result == 1) {
//				System.out.println("▨▧ " + bDto.getBno() + "게시글 수정에 성공했습니다." );
//			} else {
//				System.out.println("▨▧ 게시글 수정에 실패했습니다. 관리자에게 문의하세요." );
//			}
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//			
//		} finally {
//			try {
//				conn.close();
//				pstmt.close();
//				rs.close();
//				
//			} catch (Exception e2) {
//				// TODO: handle exception
//				e2.printStackTrace();
//				
//			}
//		}
//	}
//	
//	public void boardDelete(int bno) {
//		
//		try {
//			conn = DBManager.getConnection();
//			String sql = "DELETE FROM tbl_board WHERE bno = ? ";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, bno);
//			
//			int result = pstmt.executeUpdate();
//			if (result == 1) {
//				System.out.println("▨▧ " + bno + "게시글 삭제에 성공했습니다." );
//			} else {
//				System.out.println("▨▧ 게시글 삭제에 실패했습니다. 관리자에게 문의하세요." );
//			}
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//			
//		} finally {
//			try {
//				conn.close();
//				pstmt.close();
//				rs.close();
//				
//			} catch (Exception e2) {
//				// TODO: handle exception
//				e2.printStackTrace();
//				
//			}
//		}
//	}
//		
//	// 4. 게시글 조회
//	public void boardSelect() {
//		
//		try {			
//			conn = DBManager.getConnection();
//			String sql = "SELECT * FROM tbl_board "
//						+"ORDER BY bno DESC ";
//			
//			pstmt = conn.prepareStatement(sql);
//			rs = pstmt.executeQuery();
//			
//			while(rs.next()) {
//				int bno = rs.getInt("bno");
//				String title = rs.getString("title");
//				String content = rs.getString("content");
//				String writer = rs.getString("writer");
//				int viewcnt = rs.getInt("viewcnt");
//				Date regdate = rs.getDate("regdate");
//				
//				BoardDTO bDto = new BoardDTO(bno, title, content, writer, viewcnt, regdate);
//				list.add(bDto);								
//			}
//					
//			printQuery(list);
//			
//			list.clear();		// 출력용 ArrayList<> 클리어			
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//			
//		} finally {
//			try {
//				conn.close();
//				pstmt.close();
//				rs.close();
//				
//			} catch (Exception e2) {
//				// TODO: handle exception
//				e2.printStackTrace();
//				
//			}
//		}
//		
//	}
//	
//	// 5. 게시글 검색
//	public void boardSearch(String search) {
//		
//		try {
//			conn = DBManager.getConnection();
//			String sql = "SELECT * FROM tbl_board "
//						+"WHERE title like '%'||?||'%' "
//						+"OR content like ? ";
////			String sql = "SELECT * FROM tbl_board WHERE title LIKE ? ";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, search);
//			pstmt.setString(2, "%"+search+"%");
////			pstmt.setString(1, "%"+search+"%");
//			rs = pstmt.executeQuery();
//			
//			while(rs.next()) {
//				int bno = rs.getInt("bno");
//				String title = rs.getString("title");
//				String content = rs.getString("content");
//				String writer = rs.getString("writer");
//				int viewcnt = rs.getInt("viewcnt");
//				Date regdate = rs.getDate("regdate");
//				
//				BoardDTO bDto = new BoardDTO(bno, title, content, writer, viewcnt, regdate);
//				list.add(bDto);				
//			}
//			printQuery(list);
//			System.out.println("▩▩ \"" + search + "\"로 검색한 결과 " + list.size() + "건 검색되었습니다.");
//			System.out.println("▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩");
//			
//			list.clear();		// 출력용 ArrayList<> 클리어			
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//			
//		} finally {
//			try {
//				conn.close();
//				pstmt.close();
//				rs.close();
//				
//			} catch (Exception e2) {
//				// TODO: handle exception
//				e2.printStackTrace();
//				
//			}
//		}
//	}
//	
//	// 6. 게시글 정렬
//	public void boardSort(int column, int order) {	
//		try {			
//			conn = DBManager.getConnection();
//			String sql = "";
//			if (column == 1) {				// 게시글 번호
//				sql = "SELECT * FROM tbl_board ORDER BY bno ";
//			} else if (column == 2) {		// 등록일자
//				sql = "SELECT * FROM tbl_board ORDER BY regdate ";
//			} else {						// 조회수
//				sql = "SELECT * FROM tbl_board ORDER BY viewcnt ";
//			}
//			
//			if (order == 1) {	// 오름차순
//				sql += "ASC";
//			} else {			// 내림차순
//				sql += "DESC";
//			}
//			
//			pstmt = conn.prepareStatement(sql);
//			rs = pstmt.executeQuery();
//			while(rs.next()) {
//				int bno = rs.getInt("bno");
//				String title = rs.getString("title");
//				String content = rs.getString("content");
//				String writer = rs.getString("writer");
//				int viewcnt = rs.getInt("viewcnt");
//				Date regdate = rs.getDate("regdate");
//				
//				BoardDTO bDto = new BoardDTO(bno, title, content, writer, viewcnt, regdate);
//				list.add(bDto);								
//			}
//					
//			printQuery(list);
//			
//			list.clear();		// 출력용 ArrayList<> 클리어			
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//			
//		} finally {
//			try {
//				conn.close();
//				pstmt.close();
//				rs.close();
//				
//			} catch (Exception e2) {
//				// TODO: handle exception
//				e2.printStackTrace();
//				
//			}
//		}
//		
//	}
//
//	// 7. 상세 게시글
//	public void boardView(int bno) {
//		// 상세 게시글 조회수 +1 증가
//		int result = viewCntPlus(bno);
//		if (result == 0) {
//			System.err.println("▨▧ 조회수 증가 실패, 관리자에게 문의하세요.");
//			return;
//		}
//		// 상세 게시글 출력
//		try {
//			conn = DBManager.getConnection();
//			String sql = "SELECT * " +
//						 "FROM tbl_board " +
//						 "WHERE bno = ? ";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, bno);
//			
//			rs = pstmt.executeQuery();
//			BoardDTO bDto = new BoardDTO();
//			while(rs.next()) {
//				bDto.setBno(rs.getInt("bno"));
//				bDto.setTitle(rs.getString("title"));
//				bDto.setContent(rs.getString("content"));
//				bDto.setWriter(rs.getString("writer"));
//				bDto.setViewcnt(rs.getInt("viewcnt"));				
//				bDto.setRegdate(rs.getDate("regdate"));
//			}
//			
//			System.out.println("▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧");
////			System.out.println("▨▧ 번호\t 제목\t 내용\t 작성자\t 작성일자");			
////			System.out.println(bDto.toString());
//			System.out.println("▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧");
//			System.out.println("〓 번호: " + bDto.getBno() + " / 제목: " + bDto.getTitle() + " / 조회수: " + bDto.getViewcnt());
//			System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
//			System.out.println("〓 내용: " + bDto.getContent());			
//			System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
//			System.out.println("〓 작성자: " + bDto.getWriter() + " / 작성일자: " + bDto.getRegdate());
//			System.out.println("▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧");
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		} finally {
//			try {
//				conn.close();
//				pstmt.close();
//				rs.close();
//			} catch (Exception e2) {
//				// TODO: handle exception
//				e2.printStackTrace();
//			}
//		}
//	}
//	
//	// 7. 상세 게시글
//	public int viewCntPlus(int bno) {
//		int result = 0;
//		try {
//			conn = DBManager.getConnection();
//			String sql = "UPDATE tbl_board SET viewcnt = viewcnt + 1 WHERE bno = ? ";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, bno);
//			result = pstmt.executeUpdate();
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//			
//		} finally {
//			try {
//				conn.close();
//				pstmt.close();
//				rs.close();
//			} catch (Exception e2) {
//				// TODO: handle exception
//				e2.printStackTrace();
//				
//			}
//		}
//		return result;
//	}
//	
//	// 조회된 결과를 출력하는 함수
//	public void printQuery(ArrayList<BoardDTO> list) {
//		System.out.println("▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩");
//		System.out.println("〓 번호\t 제목\t\t 내용\t\t\t\t 작성자\t 조회수\t 작성일자");
//		System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
//		for (BoardDTO line : list) {
//			System.out.println(line.toString());
//		}
//		System.out.println("▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩");
//	}
	
}
