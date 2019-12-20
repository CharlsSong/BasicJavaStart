package problem.dotorybook;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import problem.common.DBManager;

public class BookDAO {
	Connection conn = DBManager.getConnection();
	PreparedStatement pstmt;
	ResultSet rs;
	ArrayList<BookDTO> list = new ArrayList<BookDTO>();

	public void bookInsert(BookDTO bDto) {
		try {
			String sql = "INSERT INTO tbl_book(bno,bname,price,company,writer) "
					+"VALUES(seq_book.NEXTVAL,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bDto.getBname());
			pstmt.setInt(2, bDto.getPrice());
			pstmt.setString(3, bDto.getCompany());
			pstmt.setString(4, bDto.getWriter());
			int result = pstmt.executeUpdate();
			if (result == 1) {
				System.out.println("도서가 등록되었습니다.");
			} else {
				System.out.println("도서가 등록에 실패했습니다. 관리자에게 문의하세요.");
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
	}

	public void bookUpdate(BookDTO bDto) {
		try {
				String sql = "UPDATE tbl_book "
							+"SET "
							+"bname = ?, "
							+"price = ?, "
							+"company = ?, "
							+"writer = ? "
							+"WHERE bno = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, bDto.getBname());
				pstmt.setInt(2, bDto.getPrice());
				pstmt.setString(3, bDto.getCompany());
				pstmt.setString(4, bDto.getWriter());
				pstmt.setString(5, bDto.getBno());
				
				int result = pstmt.executeUpdate();
				if (result == 1) {
					System.out.println("도서번호 "+bDto.getBno()+"번 수정되었습니다.");
					
				} else {
					System.out.println("도서 수정에 실패했습니다. 관리자에게 문의하세요");
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
	}

	public void bookDelete(String bno) {
		try {
			String sql = "DELETE FROM tbl_book WHERE bno = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bno);
			
			int result = pstmt.executeUpdate();
			if(result == 1) {
				System.out.println("도서번호 "+bno+"번 삭제했습니다.");
			} else {
				System.out.println("도서 삭제에 실패했습니다. 관리자에게 문의하세요.");
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
	}

	public void bookSelect() {
		try {			
			String sql = "SELECT * FROM tbl_book ";

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String bno = rs.getString("bno");
				String bname = rs.getString("bname");
				int price = rs.getInt("price");
				String company = rs.getString("company");
				String writer = rs.getString("writer");
				Date regdate = rs.getDate("regdate");

				BookDTO bDto = new BookDTO(bno, bname, price, company, writer, regdate);
				list.add(bDto);
			}

			System.out.println("bon\t bname\t price\t company\t	writer\t regdate");
			for (BookDTO line : list) {
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
	}

	public void bookSearch(String bname) {
		try {			
			String sql = "SELECT * FROM tbl_book WHERE bname like ? ";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+bname+"%");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String bno = rs.getString("bno");
				bname = rs.getString("bname");
				int price = rs.getInt("price");
				String company = rs.getString("company");
				String writer = rs.getString("writer");
				Date regdate = rs.getDate("regdate");

				BookDTO bDto = new BookDTO(bno, bname, price, company, writer, regdate);
				list.add(bDto);
			}

			System.out.println("bon\t bname\t price\t company\t	writer\t regdate");
			for (BookDTO line : list) {
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
	}

	public void madeBy() {
		System.out.println("▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧");
		System.out.println("▨▧ Name: Dotory Book Program");
		System.out.println("▨▧ Version: 1.7");
		System.out.println("▨▧ Use: JAVA, ORACLE");
		System.out.println("▨▧ Date: 2019.12.17");
		System.out.println("▨▧ made by Kcumero");
		System.out.println("▨▧ kcumero@hanmail.net");
	}

}
