package problem.marcket;

import java.util.Scanner;

public class MarcketMain {
	// 내부저장소(관리자 계정 ID와 PW 선언)
	String id = "admin";
	String pw = "1234";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		ProductDAO pDao = new ProductDAO();
		SaleDAO sDao = new SaleDAO();
		MarcketMain mm = new MarcketMain();
		
		// 프로그램 시작
		String userid = "";
		String userpw = "";
		int code = 0;
		Boolean flag = false;
		// 로그인 체크
//		do {
//			System.out.println("▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
//			System.out.println("▣▣ Marcket System Ver1.0 ▣▣");
//			System.out.println("▣▣ [MSG] Please login in to use.");
//			System.out.println("▣▣ ID>> ");
//			userid = sc.nextLine();
//			System.out.println("▣▣ PW>> ");
//			userpw = sc.nextLine();
//		} while(mm.login(userid, userpw));
		
		while(true) {
			
			// 로그인 성공 업무 시작
			while(true) {
				System.out.println("▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
				System.out.println("▣▣ Marcket System Ver1.0 ▣▣");
				System.out.println("▣▣ 1. 제품 판매");
				System.out.println("▣▣ 2. 제품 등록&추가");
				System.out.println("▣▣ 3. 제품 수정");
				System.out.println("▣▣ 4. 제품 삭제");
				System.out.println("▣▣ 5. 제품 조회");
				System.out.println("▣▣ 6. 제품 검색");
				System.out.println("▣▣ 7. 일일 매출현황");
				System.out.println("▣▣ 8. 프로그램 종료");
				System.out.println("▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
				System.out.println("▣▣ Code>> ");
				code = sc.nextInt();
				sc.nextLine();
				
				if(code >= 1 && code <= 8) {
					break;
				} else {
					System.out.println("▣▣ 1~8까지 숫자만 입력하세요. ");
					continue;
				}
			}
			
			// 1~8중 code값 입력
			if(code == 1) { 	// 1. 제품 판매
				
				// 판매 제품 리스트 출력
				System.out.println("▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
				System.out.println("▣▣ 판매 제품 리스트 ");
				pDao.salePdtList();
				
				// 판매정보 입력
				String pname = "";
				do {
					System.out.println("▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
					System.out.println("▣▣ 1. 제품 판매 ");
					System.out.println("▣▣ 제품명: ");
					pname = sc.nextLine();
				} while(!pDao.pdtAlready(pname));
				
				System.out.println("▣▣ 제품 매출입력 ▣▣ ");
				System.out.println("▣▣ 매출수량: ");
				int cnt = sc.nextInt();
				sc.nextLine();
								
				pDao.salePdt(pname, cnt);
			} else if(code == 2) { 	// 2. 제품 등록&추가
				System.out.println("▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
				System.out.println("▣▣ 2. 제품 등록&추가 ");
				System.out.println("▣▣ 제품명:  ");
				String pname = sc.nextLine();
				if(pDao.pdtAlready(pname)) {
					// 기존에 등록된 제품이므로 추가(UPDATE)기능
					// 입고수량 입력받아서 UPDATE
					System.out.println("▣▣ 제품을 입고합니다.");
					System.out.println("▣▣ 입고수량:  ");
					int cnt = sc.nextInt();
					sc.nextLine();
					pDao.cntPlusPdt(pname, cnt);
				} else {
					// 최초 등록되는 제품이므로 등록(INSERT)기능
					// 제조회사, 가격, 입고수량 입력받아서 INSERT
					System.out.println("▣▣ 제품을 등록합니다.");
					System.out.println("▣▣ 제조회사:  ");
					String company = sc.nextLine();
					System.out.println("▣▣ 제품단가:  ");
					int price = sc.nextInt();
					sc.nextLine();
					System.out.println("▣▣ 입고수량:  ");
					int cnt = sc.nextInt();
					sc.nextLine();
					ProductDTO pDto = new ProductDTO(pname, company, price, cnt);
					pDao.insertPdt(pDto);
				}
				
			} else if(code == 3) { 	// 3. 제품 수정
				String pname = "";
				do {
					System.out.println("▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
					System.out.println("▣▣ 3. 제품 수정 ");
					System.out.println("▣▣ 제품명:  ");
					pname = sc.nextLine();
				} while(!pDao.pdtAlready(pname));
				
				System.out.println("▣▣ 제품을 등록합니다.");
				System.out.println("▣▣ 제조회사:  ");
				String company = sc.nextLine();
				System.out.println("▣▣ 제품단가:  ");
				int price = sc.nextInt();
				sc.nextLine();
				System.out.println("▣▣ 입고수량:  ");
				int cnt = sc.nextInt();
				sc.nextLine();
				ProductDTO pDto = new ProductDTO(pname, company, price, cnt);
				pDao.updatePdt(pDto);
				
			} else if(code == 4) { 	// 4. 제품 삭제
				String pname = "";
				do {
					System.out.println("▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
					System.out.println("▣▣ 4. 제품 삭제 ");
					System.out.println("▣▣ 제품명:  ");
					pname = sc.nextLine();
				} while(!pDao.pdtAlready(pname));
				
				String yesNo = "";
				while(true) {
					System.out.println("▣▣ 삭제하시겠습니까?(y/n)  ");
					yesNo = sc.nextLine();
					if(yesNo.equals("y")||yesNo.equals("n")) {
						break;
					} else {
						System.out.println("▣▣ y 또는 n 만 입력하세요. ");
						continue;
					}
				}
				
				if (yesNo.equals("y")) {
					pDao.deletePdt(pname);
				} else {
					System.out.println("▣▣ 제품명 : " + pname + " 삭제를 취소하였습니다.");
				}
				
			} else if(code == 5) { 	// 5. 제품 조회
				System.out.println("▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
				System.out.println("▣▣ 5. 제품 조회 ");
				pDao.selectPdt();
			} else if(code == 6) { 	// 6. 제품 검색
				String pname = "";
				do {
					System.out.println("▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
					System.out.println("▣▣ 6. 제품 검색 ");
					System.out.println("▣▣ 제품명:  ");
					pname = sc.nextLine();
				} while(!pDao.pdtAlready(pname));
				
				pDao.searchPdt(pname);
			} else if(code == 7) { 	// 7. 일일 매출현황
				// 판매된 제품명, 수량, 가격 누적저장
				System.out.println("▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
				System.out.println("▣▣ 7. 일일 매출현황 ");
			
				sDao.totalSale();
			} else { 	// 8. 프로그램 종료
				System.out.println("▣▣ [MSG] Program Exit.");
				System.exit(0);
			}
			
		} // 메뉴반복 종료 
		
	}

	public boolean login(String userid, String userpw) {
		Boolean flag = true; // 로그인 판별 (true:실패, false:성공)
		
		 if(userid.equals(id) && userpw.equals(pw)) { //로그인 성공
			 flag = false;
			 System.out.println("▣▣ Welcome admin, Have a nice day.");
		 } else {
			 System.out.println("▣▣ [MSG] login denied.");
		 }
		
		return flag;
		
	}
}
