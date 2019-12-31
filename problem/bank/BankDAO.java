package problem.bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import mybatis.SqlMapConfig;

public class BankDAO {
	// MyBatis 세팅값 호출
	// Session을 생성하는 공장을 만드는 과정
	SqlSessionFactory sqlSessionFactory = SqlMapConfig.getSqlSession();
	
	// mapper에 접근하기 위한  SqlSession
	SqlSession sqlSession;
	
	List<BankDTO> list2;
	int result;
	
	public void insertBank(String bname, String pw) {
		sqlSession = sqlSessionFactory.openSession(true); // true : Auto Commit 설정
		
		try {

			BankDTO bDto = new BankDTO(bname, pw);
			result = sqlSession.insert("insertBank", bDto);
			// sqlSession.commit();
			if(result > 0) {
				System.out.println("■■ " + bname + "님 신규계좌를 개설하였습니다.");
			} else {
				System.out.println("■■ 계좌개설에 실패하였습니다. 관리자에게 문의하세요.");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			sqlSession.close();
			
		}
	}
	
	// 2. 입금
	public void updateBank(int bno, int money){
		sqlSession = sqlSessionFactory.openSession(true); // true : Auto Commit 설정
		
		try {
			HashMap<String, Integer> map = new HashMap<>();	// 모든 객체는 Object class로 받을 수 있다.
			map.put("bno", bno);
			map.put("money", money);
			map.put("flag", 1);
//			BankDTO bDto = new BankDTO(bno, money);
//			result = sqlSession.update("updateBankIn", map);
			result = sqlSession.update("changeMoney", map);
			
//			int balance = balanceMoney(bno);
			 
			// sqlSession.commit();
			if(result > 0) {
				System.out.println("■■ " + bno + "계좌에 " + money + "원 입금되었습니다.");
				System.out.println("■■ " + bno + "계좌잔액 : " + balanceMoney(bno) + "원 입니다.");
			} else {
				System.out.println("■■ 입금에 실패하였습니다. 관리자에게 문의하세요.");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			sqlSession.close();
			
		}
	}
	
	// 3. 출금
	public void updateBank(int bno, String pw, int money){
		sqlSession = sqlSessionFactory.openSession(true); // true : Auto Commit 설정
//		Scanner sc = new Scanner(System.in);
//		
		try {
//			if(checkUser(bno, pw)){
//				System.out.println("■■ 통과 >>>>>>>>>>>>>>>");
//			} else {
//				System.out.println("■■ 존재하지 않는 계좌번호이거나 암호가 틀렸습니다. 관리자에게 문의하세요.");
//				return;
//			} 
//			
//			 int balance = balanceMoney(bno);			 
//			// 출금액과 잔고 비교
//			int money = 0;
//			if (balance > 0) {
//				while (true) {
//					System.out.println("■■ 계좌 잔고>> " + balance + "원");
//					System.out.println("■■ 출금할 금액>>");
//					money = sc.nextInt();
//					sc.nextLine();
//					
//					if (money >= 1 && money <= 10000000) {						
//						if (balance >= money) {
//							break;
//						} else {	// 잔고가 부족하여 출금할수 없는 경우						
//							System.out.println("■■ 출금액보다 잔액이 부족합니다.");
//							continue;
//						}						
//					} else {
//						System.out.println("■■ 1회 출금 최대 금액은 10,000,000원 까지 입니다.");
//						continue;
//					}
//				}				
//			} else {  // 잔고가 없는 경우와 조회결과값이 없는 경우 처리				
//				System.out.println("■■ 잔액이 없어 출금할 수 없습니다.");				
//				return;
//			}
//			 
			//BankDTO bDto = new BankDTO(bno, pw, money);
			HashMap<String, Object> map = new HashMap<>();
			map.put("bno", bno);
			map.put("pw", pw);
			map.put("money", money);
			map.put("flag", 0);
			
//			System.out.println(map.get("bno")+", "+map.get("pw")+", "+map.get("money"));
//			result = sqlSession.update("updateBankOut", map);
			result = sqlSession.update("changeMoney", map);
			
//			balance = balanceMoney(bno);
			
			// sqlSession.commit();
			if(result > 0) {
				System.out.println("■■ " + bno + "계좌에서 " + money + "원 출금되었습니다.");
				System.out.println("■■ " + bno + "계좌잔액 >> " + balanceMoney(bno) + "원 입니다.");
			} else {
				System.out.println("■■ 출금에 실패하였습니다. 관리자에게 문의하세요.");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			sqlSession.close();
		
		}
	}
	
	// 4.전체 고객조회
	public void selectBank() {
		sqlSession = sqlSessionFactory.openSession();
		try {
			list2 = sqlSession.selectList("selBank"); // selectList():여러건, selectOne():단건
			
			for (BankDTO line : list2) {
				System.out.println(line.toString());
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			sqlSession.close();
			
		}
	}
	
	// 5. 계좌조회
	public void selectAccount(int bno, String pw) {
		sqlSession = sqlSessionFactory.openSession();
		try {
			
			BankDTO bDto = new BankDTO(bno, pw);		
			bDto = sqlSession.selectOne("selectAccount", bDto); // 1줄 결과는 DTO로 받으면 된다.
//			
			// selectOne() => DTO	: 실패할 경우 null 
			// selectList() => LIST or DTO
			
			if(bDto == null) {
				System.out.println("■■ 존재하지 않는 계좌번호이거나 패스워드가 클립니다.");
			} else {
				System.out.println("■■ " + bno + " 계좌잔액 >> " + bDto.getMoney() + "원 입니다.");
				System.out.println(bDto.toString());
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			sqlSession.close();
			
		}
	}
	
	// 7. 계좌 해지
	public void deleteAccount(int bno, String pw) {
		sqlSession = sqlSessionFactory.openSession(true);
		try {
//			BankDTO bDto = new BankDTO(bno, pw);
			// HashMap를 이용한 매개변수 전달
			HashMap<String, Object> map = new HashMap<>();	// 모든 객체는 Object class로 받을 수 있다.
			map.put("bno", bno);
			map.put("pw", pw);
			result = sqlSession.delete("deleteAccount", map);
			
			if(result > 0) {
				System.out.println("■■ " + bno + "계좌를 해지하였습니다.");
			} else {
				System.out.println("■■ 계좌해지에 실패하였습니다. 관리자에게 문의해주세요.");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			sqlSession.close();
			
		}
	}
	
	// 계좌잔액조회
	public int balanceMoney(int bno) {
		sqlSession = sqlSessionFactory.openSession();
		int money = 0;
		try {
			
			money = sqlSession.selectOne("balanceMoney", bno);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		return money;
	}
	
	public boolean checkUser(int bno, String pw) {
		boolean flag = false;
		sqlSession = sqlSessionFactory.openSession();
		try {
			HashMap<String, Object> map = new HashMap<>();
			map.put("bno", bno);
			map.put("pw", pw);
			
			result = sqlSession.selectOne("checkUser",map);
			if(result == 1) flag=true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			sqlSession.close();
			
		}
		
		return flag;
	}
	
	// 출금
	public void minusMoney(int bno, int money) {		
		sqlSession = sqlSessionFactory.openSession(true);
		
		HashMap<String, Integer> map = new HashMap<>();
		map.put("bno", bno);
		map.put("money", money);
		
		try {
			result = sqlSession.update("minusMoney", map);
			if(result > 0) {
				System.out.println("■■ " + bno + "출금성공하였습니다.");
				System.out.println("■■ 계좌잔액 >> " + balanceMoney(bno) + "원 입니다.");
			} else {
				System.out.println("■■ 출금에 실패하였습니다. 관리자에게 문의해주세요.");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			sqlSession.close();
			
		}
	}
//	Connection conn;
//	PreparedStatement pstmt;
//	ResultSet rs;
//	ArrayList<BankDTO> list = new ArrayList<BankDTO>();
//
//	public void insertBank(String bname, String pw) {
//		try {
//			conn = DBManager.getConnection();
//			String sql = "INSERT INTO tbl_bank(bno, bname, pw) "
//						+"VALUES(seq_bank.NEXTVAL, ?,?)";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, bname);
//			pstmt.setString(2, pw);
//			
//			int result = pstmt.executeUpdate();
//			if (result == 1) {
//				System.out.println("■■ " + bname + "님 신규 계좌가 개설되었습니다.");
//			} else {
//				System.out.println("■■ 신규 계좌 개설에 실패하였습니다. 관리자에게 문의하세요.");
//			}
//			System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
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
//	public void updateBank(int bno, int money) {
//		try {
//			conn = DBManager.getConnection();
//			String sql = "UPDATE tbl_bank "
//						+"SET money = money + ? "
//						+"WHERE bno =? ";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, money);
//			pstmt.setInt(2, bno);
//			
//			int result = pstmt.executeUpdate();
//			if (result == 1) {
//				System.out.println("■■ " + bno + "계좌에 " + money + "원 입금되었습니다.");
//			} else {
//				System.out.println("■■ 입금에 실패하였습니다. 관리자에게 문의하세요.");
//			}
//			System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
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
//	public void updateBank(int bno, String pw) {
//		try {
//			conn = DBManager.getConnection();
//			Scanner sc = new Scanner(System.in);
//			
//			// 잔고조회
//			String sql = "SELECT * FROM tbl_bank "
//						+"WHERE bno = ? AND pw = ? ";
//			pstmt = conn.prepareStatement(sql);			
//			pstmt.setInt(1, bno);
//			pstmt.setString(2, pw);
//			
//			int deposit = 0;
//			String bname = "";
//			rs = pstmt.executeQuery();
//			while(rs.next()) {
//				deposit = rs.getInt("money");
//				bname = rs.getString("bname");
//			}
//			
//			// 출금액과 잔고 비교
//			int money = 0;
//			if (deposit > 0) {
//				while (true) {
//					System.out.println("■■ 계좌 잔고>> " + deposit + "원");
//					System.out.println("■■ 출금할 금액>>");
//					money = sc.nextInt();
//					sc.nextLine();
//					
//					if (money >= 1 && money <= 10000000) {						
//						if (deposit >= money) {
//							break;
//						} else {	// 잔고가 부족하여 출금할수 없는 경우						
//							System.out.println("■■ 잔고가 부족하여 출금할 수 없습니다.");
//							continue;
//						}						
//					} else {
//						System.out.println("■■ 1회 출금 최대 금액은 10,000,000원 까지 입니다.");
//						continue;
//					}
//				}				
//			} else {  // 잔고가 없는 경우와 조회결과값이 없는 경우 처리
//				if (bname == "") {
//					System.out.println("■■ 계좌 정보가 일치하지 않습니다. 관리자에게 문의하십시요.");
//				} else {
//					System.out.println("■■ 잔고가 없어 출금할 수 없습니다.");
//				}
//				return;
//			}
//			
//			// 출금처리 
////			if (deposit >= money) {
//
//			sql = "UPDATE tbl_bank "
//					+"SET money = money - ? "
//					+"WHERE bno = ? AND pw = ? ";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, money);
//			pstmt.setInt(2, bno);
//			pstmt.setString(3, pw);
//			
//			int result = pstmt.executeUpdate();
//			if (result == 1) {
//				System.out.println("■■ " + bno + "계좌에서 " + money + "원 출금되었습니다.");
//			} else {
//				System.out.println("■■ 출금에 실패하였습니다. 관리자에게 문의하세요.");
//			}
//			System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
////			} else {
////				System.out.println("■■ 잔고가 부족하여 출금할 수 없습니다.");
////				System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
////			}
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
//	public void selectBank() {
//		try {
//			conn = DBManager.getConnection();
//			String sql = "SELECT * FROM tbl_bank ";
//			pstmt = conn.prepareStatement(sql);
//			
//			list.clear();
//			rs = pstmt.executeQuery();
//			while(rs.next()) {
//				int bno = rs.getInt("bno");
//				String bname = rs.getString("bname");
//				String pw = rs.getString("pw");
//				int money = rs.getInt("money");
//				Date regdate = rs.getDate("regdate");
//				
//				BankDTO bDto = new BankDTO(bno, bname, pw, money, regdate);
//				list.add(bDto);
//			}
//			
//			viewPrint(list);
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
//	public void searchBank(String bname) {
//		try {
//			conn = DBManager.getConnection();
//			String sql = "SELECT * FROM tbl_bank WHERE bname LIKE '%'||?||'%' ";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, bname);
//			
//			list.clear();
//			rs = pstmt.executeQuery();
//			while(rs.next()) {
//				int bno = rs.getInt("bno");
//				bname = rs.getString("bname");
//				String pw = rs.getString("pw");
//				int money = rs.getInt("money");
//				Date regdate = rs.getDate("regdate");
//				
//				BankDTO bDto = new BankDTO(bno, bname, pw, money, regdate);
//				list.add(bDto);
//			}
//			
//			viewPrint(list);
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


