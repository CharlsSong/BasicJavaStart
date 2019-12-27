package problem.bank;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;

import problem.common.DBManager;

public class BankDAO {
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	ArrayList<BankDTO> list = new ArrayList<BankDTO>();

	public void insertBank(String bname, String pw) {
		try {
			conn = DBManager.getConnection();
			String sql = "INSERT INTO tbl_bank(bno, bname, pw) "
						+"VALUES(seq_bank.NEXTVAL, ?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bname);
			pstmt.setString(2, pw);
			
			int result = pstmt.executeUpdate();
			if (result == 1) {
				System.out.println("■■ " + bname + "님 신규 계좌가 개설되었습니다.");
			} else {
				System.out.println("■■ 신규 계좌 개설에 실패하였습니다. 관리자에게 문의하세요.");
			}
			System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
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
	
	public void updateBank(int bno, int money) {
		try {
			conn = DBManager.getConnection();
			String sql = "UPDATE tbl_bank "
						+"SET money = money + ? "
						+"WHERE bno =? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, money);
			pstmt.setInt(2, bno);
			
			int result = pstmt.executeUpdate();
			if (result == 1) {
				System.out.println("■■ " + bno + "계좌에 " + money + "원 입금되었습니다.");
			} else {
				System.out.println("■■ 입금에 실패하였습니다. 관리자에게 문의하세요.");
			}
			System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
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
	
	public void updateBank(int bno, String pw) {
		try {
			conn = DBManager.getConnection();
			Scanner sc = new Scanner(System.in);
			
			// 잔고조회
			String sql = "SELECT * FROM tbl_bank "
						+"WHERE bno = ? AND pw = ? ";
			pstmt = conn.prepareStatement(sql);			
			pstmt.setInt(1, bno);
			pstmt.setString(2, pw);
			
			int deposit = 0;
			String bname = "";
			rs = pstmt.executeQuery();
			while(rs.next()) {
				deposit = rs.getInt("money");
				bname = rs.getString("bname");
			}
			
			// 출금액과 잔고 비교
			int money = 0;
			if (deposit > 0) {
				while (true) {
					System.out.println("■■ 계좌 잔고>> " + deposit + "원");
					System.out.println("■■ 출금할 금액>>");
					money = sc.nextInt();
					sc.nextLine();
					
					if (money >= 1 && money <= 10000000) {						
						if (deposit >= money) {
							break;
						} else {	// 잔고가 부족하여 출금할수 없는 경우						
							System.out.println("■■ 잔고가 부족하여 출금할 수 없습니다.");
							continue;
						}						
					} else {
						System.out.println("■■ 1회 출금 최대 금액은 10,000,000원 까지 입니다.");
						continue;
					}
				}				
			} else {  // 잔고가 없는 경우와 조회결과값이 없는 경우 처리
				if (bname == "") {
					System.out.println("■■ 계좌 정보가 일치하지 않습니다. 관리자에게 문의하십시요.");
				} else {
					System.out.println("■■ 잔고가 없어 출금할 수 없습니다.");
				}
				return;
			}
			
			// 출금처리 
//			if (deposit >= money) {

			sql = "UPDATE tbl_bank "
					+"SET money = money - ? "
					+"WHERE bno = ? AND pw = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, money);
			pstmt.setInt(2, bno);
			pstmt.setString(3, pw);
			
			int result = pstmt.executeUpdate();
			if (result == 1) {
				System.out.println("■■ " + bno + "계좌에서 " + money + "원 출금되었습니다.");
			} else {
				System.out.println("■■ 출금에 실패하였습니다. 관리자에게 문의하세요.");
			}
			System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
//			} else {
//				System.out.println("■■ 잔고가 부족하여 출금할 수 없습니다.");
//				System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
//			}
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
	public void selectBank() {
		try {
			conn = DBManager.getConnection();
			String sql = "SELECT * FROM tbl_bank ";
			pstmt = conn.prepareStatement(sql);
			
			list.clear();
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int bno = rs.getInt("bno");
				String bname = rs.getString("bname");
				String pw = rs.getString("pw");
				int money = rs.getInt("money");
				Date regdate = rs.getDate("regdate");
				
				BankDTO bDto = new BankDTO(bno, bname, pw, money, regdate);
				list.add(bDto);
			}
			
			viewPrint(list);
			
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
	public void searchBank(String bname) {
		try {
			conn = DBManager.getConnection();
			String sql = "SELECT * FROM tbl_bank WHERE bname LIKE '%'||?||'%' ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bname);
			
			list.clear();
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int bno = rs.getInt("bno");
				bname = rs.getString("bname");
				String pw = rs.getString("pw");
				int money = rs.getInt("money");
				Date regdate = rs.getDate("regdate");
				
				BankDTO bDto = new BankDTO(bno, bname, pw, money, regdate);
				list.add(bDto);
			}
			
			viewPrint(list);
					
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

	public void viewPrint(ArrayList<BankDTO> list) {
		
		System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
		System.out.println("계좌번호 예금주\t비밀번호\t잔고\t개설일자\t");
		System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
		for (BankDTO bankDTO : list) {
			System.out.println(bankDTO.toString());
		}
		System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
	}
}


