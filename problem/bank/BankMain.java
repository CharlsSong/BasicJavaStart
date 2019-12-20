package problem.bank;

import java.util.Scanner;

public class BankMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		BankDAO bDao = new BankDAO();
		int code = 0;
		while (true) {
			System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
			System.out.println("■■ 허쉬은행 Ver1.0");
			System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
			System.out.println("■■ 1. 계좌 개설");
			System.out.println("■■ 2. 입금");
			System.out.println("■■ 3. 출금");
			System.out.println("■■ 4. 계좌 조회");
			System.out.println("■■ 5. 사용자 검색");
			System.out.println("■■ 6. 프로그램 종료");
			while (true) {
				System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
				System.out.println("■■ 번호>>");
				code = sc.nextInt();
				sc.nextLine();

				if (code >= 1 && code <= 6) {
					break;
				} else {
					System.out.println("1~6까지 숫자만 입력하세요.");
					continue;
				}
			}

			if (code == 1) { // 1. 계좌 개설
				System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
				System.out.println("■■ 허쉬은행 Ver1.0");
				System.out.println("■■ 1. 계좌 개설");
				System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
				System.out.println("■■ 예금주>>");
				String bname = sc.nextLine();
				System.out.println("■■ 비밀번호>>");
				String pw = sc.nextLine();
				bDao.insertBank(bname, pw);
			} else if (code == 2) { // 2. 입금
				System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
				System.out.println("■■ 허쉬은행 Ver1.0");
				System.out.println("■■ 2. 입금");
				System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
				System.out.println("■■ 계좌 번호>>");
				int bno = sc.nextInt();
				sc.nextLine();

				int money = 0;
				while (true) {
					System.out.println("■■ 입금할 금액>>");
					money = sc.nextInt();
					sc.nextLine();

					if (money >= 1 && money <= 10000000) {
						break;
					} else {
						System.out.println("1회 입금 최대 금액은 10,000,000원 까지 입니다.");
						continue;
					}
				}

				bDao.updateBank(bno, money);

			} else if (code == 3) { // 3. 출금
				System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
				System.out.println("■■ 허쉬은행 Ver1.03");
				System.out.println("■■ 3. 출금");
				System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
				System.out.println("■■ 계좌 번호>>");
				int bno = sc.nextInt();
				sc.nextLine();
				System.out.println("■■ 비밀 번호>>");
				String pw = sc.nextLine();
				
				//System.out.println(bno + ", " + pw + ", " + money);
				
				bDao.updateBank(bno, pw);

			} else if (code == 4) { // 4. 계좌 조회
				System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
				System.out.println("■■ 허쉬은행 Ver1.0");
				System.out.println("■■ 4. 계좌 조회");

				bDao.selectBank();
			} else if (code == 5) { // 5. 사용자 검색
				System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
				System.out.println("■■ 허쉬은행 Ver1.0");
				System.out.println("■■ 5. 사용자 검색");
				System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
				System.out.println("■■ 사용자 이름>>");
				String bname = sc.nextLine();

				bDao.searchBank(bname);
			} else { // 6. 프로그램종료
				System.out.println("■■ 프로그램 종료");
				System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
				System.exit(0);
			}

		}
		// 1. 프로그램 전체반복
		// 2. 1~6까지 번호만 허용(나머지는 무한반복 다시입력)
		// 3. 계좌 개설 만들기(INSERT)
		// 4. 계좌 조회 만들기(SELECT, 전체조회)
		// 5. 사용자 검색 만들기(이름으로)
		// 6. 프로그램 종료기능 만들기

	}

}
