package problem.DDEnter;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import jdpc.DBManager;

public class MemberDAO {
	Connection conn = DBManager.getConnection();
	PreparedStatement pstmt;
	ResultSet rs;
	ArrayList<MemberDTO> list = new ArrayList<MemberDTO>();	//  SQL 결과 값을 받기위한 ArrayList
	
	// 1. 아티스트등록
	public void memInsert(MemberDTO mDto) {
		String sql = "INSERT INTO tbl_enter(ano, aname, major, groupyn, groupnm, sal) " 
				   + "VALUES(SEQ_ENTER.NEXTVAL,?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mDto.getAname());
			pstmt.setString(2, mDto.getMajor());
			pstmt.setString(3, mDto.getGroupyn());
			pstmt.setString(4, mDto.getGroupnm());
			pstmt.setInt(5, mDto.getSal());
			
			int result = pstmt.executeUpdate();
			if (result > 0) {
				System.out.println("▦▦ " + mDto.getAname() + " 아티스트와 계약하였습니다.");
			} else {
				System.out.println("▦▦ 등록에 실패하였습니다. 관리자에게 문의해주세요.");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		} finally {
			try {
				conn.close();
				pstmt.close();
				rs.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}			
		}
		
	};

	// 2. 아티스트수정
	public void memUpdate(MemberDTO mDto) {
		String sql = "UPDATE tbl_enter "
				   + "SET aname = ?, "
				   + "    major = ?, "
				   + "    groupyn = ?, "
				   + "    groupnm = ?, "
				   + "    sal = ? "
				   + "WHERE ano = ?";
		try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, mDto.getAname());
				pstmt.setString(2, mDto.getMajor());
				pstmt.setString(3, mDto.getGroupyn());
				pstmt.setString(4, mDto.getGroupnm());
				pstmt.setInt(5, mDto.getSal());
				pstmt.setString(6, mDto.getAno());	// SQL문 완성
				
				int result = pstmt.executeUpdate(); // SQL문 실행
				if (result > 0) {
					System.out.println("▦▦ " + mDto.getAname() + " 아티스트의 정보를 수정하였습니다.");
				} else {
					System.out.println("▦▦ 수정에 실패하였습니다. 관리자에게 문의해주세요.");
				}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		} finally {
			try {	// conn 자원 반납을 위한 예외 처리
				conn.close();
				pstmt.close();
				rs.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}			
		}
	};

	// 3. 아티스트삭제
	public void memDelete(String ano) {
		// 1.드라이버로드, 2.DB연결
		String sql = "DELETE FROM tbl_enter " + "WHERE ano = ?";
		try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, ano);
	
				int result = pstmt.executeUpdate();
				if (result > 0) {
					System.out.println("▦▦ " + ano + " 아티스트와 계약을 해지하였습니다.");
				} else {
					System.out.println("▦▦ 삭제에 실패하였습니다. 관리자에게 문의해주세요.");
				}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				pstmt.close();
				rs.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}

	};

	// 4. 아티스트조회
	public void memSelect() {
		try {
				conn = DBManager.getConnection();
				String sql = "SELECT * FROM tbl_enter";
				pstmt = conn.prepareStatement(sql);
				
				// 4.SQL실행
				// ResultSet = SELECT문 결과를 담음
				rs = pstmt.executeQuery(); // SELECT일 때만 사용;
				
				while(rs.next()) {	// rs의 현재 라인에 데이터가 있으며 true
					String ano = rs.getString("ano");
					String aname = rs.getString("aname");
					String major = rs.getString("major");
					String groupyn = rs.getString("groupyn");
					String groupnm = rs.getString("groupnm");
					int sal = rs.getInt("sal");
					Date regdate = rs.getDate("regdate");
					MemberDTO mDto = new MemberDTO(ano, aname, major, groupyn, groupnm, sal, regdate);
					list.add(mDto);
				}
				// ResultSet은 DB관련객체
				// JAVA전용 ArrayList에 ResultSet에 데이터를 옮겨담는 작업이 필요하다.
				System.out.println("ano	aname	major	groupyn	groupnm	sal	regdate");
				for (MemberDTO line : list) {
//					System.out.print(line.getAno()+"\t");
//					System.out.print(line.getAname()+"\t\t");
//					System.out.print(line.getMajor()+"\t");
//					System.out.print(line.getGroupyn()+"\t");
//					System.out.print(line.getGroupnm()+"\t");
//					System.out.print(line.getSal()+"\t");
//					System.out.print(line.getRegdate()+"\t");
					System.out.println(line.toString());
					
				}
				
				list.clear();		// ArrayList 초기화
				
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		} finally {
			try {
				conn.close();
				pstmt.close();
				rs.close();				
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
				
			}
		}
	};

	// 5. 아티스트검색
	public void memSearch(String aname) {
		try {
			conn = DBManager.getConnection();
			String sql = "SELECT * FROM tbl_enter WHERE aname LIKE ?";
//			String sql = "SELECT * FROM tbl_enter WHERE aname LIKE '%'||?||'%'";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+aname+"%");
//			pstmt.setString(1, aname);
						
			// 4.SQL실행
			// ResultSet = SELECT문 결과를 담음
			rs = pstmt.executeQuery(); // SELECT일 때만 사용;
			
			while(rs.next()) {	// rs의 현재 라인에 데이터가 있으며 true
				String ano = rs.getString("ano");
				aname = rs.getString("aname");
				String major = rs.getString("major");
				String groupyn = rs.getString("groupyn");
				String groupnm = rs.getString("groupnm");
				int sal = rs.getInt("sal");
				Date regdate = rs.getDate("regdate");
				MemberDTO mDto = new MemberDTO(ano, aname, major, groupyn, groupnm, sal, regdate);
				list.add(mDto);
			}
			// ResultSet은 DB관련객체
			// JAVA전용 ArrayList에 ResultSet에 데이터를 옮겨담는 작업이 필요하다.
			System.out.println("ano	aname	major	groupyn	groupnm		sal	regdate");
			for (MemberDTO line : list) {
//				System.out.print(line.getAno()+"\t");
//				System.out.print(line.getAname()+"\t\t");
//				System.out.print(line.getMajor()+"\t");
//				System.out.print(line.getGroupyn()+"\t");
//				System.out.print(line.getGroupnm()+"\t");
//				System.out.print(line.getSal()+"\t");
//				System.out.print(line.getRegdate()+"\t");
				System.out.println(line.toString());
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		} finally {
			try {
				conn.close();
				pstmt.close();
				rs.close();				
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
				
			}
		}		
	};

}
