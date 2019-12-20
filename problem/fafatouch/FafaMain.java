package problem.fafatouch;

import java.util.Scanner;

public class FafaMain {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		// 메인메뉴를 저장하는 5칸짜리 배열 생성
		String[][] mainMenu = new String[5][2];
		// [5][2]
		// [5]는 메뉴
		// [2]는 메뉴이름/메뉴가격
		mainMenu[0][0] = "파파버거";
		mainMenu[1][0] = "맥치킨버거";
		mainMenu[2][0] = "와퍼치즈버거";
		mainMenu[3][0] = "싸이불고기버거";
		mainMenu[4][0] = "빅맥버거";

		mainMenu[0][1] = "4500";
		mainMenu[1][1] = "5500";
		mainMenu[2][1] = "6000";
		mainMenu[3][1] = "4700";
		mainMenu[4][1] = "5500";
		// {"파파버거","맥치킨버거","와퍼치즈버거","싸이불고기버거","빅맥버거"};
		// 사이드메뉴를 저장하는 5칸짜리 배열 생성
		String[][] sideMenu = new String[5][2];

		sideMenu[0][0] = "감자튀김";
		sideMenu[1][0] = "양념감자";
		sideMenu[2][0] = "치즈스틱";
		sideMenu[3][0] = "맥너겟";
		sideMenu[4][0] = "스위트콘";

		sideMenu[0][1] = "1500";
		sideMenu[1][1] = "2000";
		sideMenu[2][1] = "2000";
		sideMenu[3][1] = "2500";
		sideMenu[4][1] = "1700";
		// {"감자튀김","양념감자","치즈스틱","맥너겟","스위트콘"};
		// 음료메뉴를 저장하는 5칸짜리 배열 생성
		String[][] drinkMenu = new String[5][2];

		drinkMenu[0][0] = "코카콜라";
		drinkMenu[1][0] = "스프라이트";
		drinkMenu[2][0] = "마운틴듀";
		drinkMenu[3][0] = "오렌지쥬스";
		drinkMenu[4][0] = "초코쉐이크";

		drinkMenu[0][1] = "2000";
		drinkMenu[1][1] = "2000";
		drinkMenu[2][1] = "2000";
		drinkMenu[3][1] = "3000";
		drinkMenu[4][1] = "2500";
		// {"코카콜라","스프라이트","마운틴듀","오렌지쥬스","초코쉐이크"};

		while (true) {
			System.out.println("▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
			System.out.println("▒▒▒ 파파터치 주문프로그램");
			System.out.println("▒▒▒ ==메인메뉴==");
			System.out.println("▒▒▒ 1.파파버거");
			System.out.println("▒▒▒ 2.맥치킨버거");
			System.out.println("▒▒▒ 3.와퍼치즈버거");
			System.out.println("▒▒▒ 4.싸이불고기버거");
			System.out.println("▒▒▒ 5.빅맥버거");
			System.out.print("▒▒▒ 번호>> ");
			// 사용자가 주문한 메인메뉴 번호!
			int mainNum = sc.nextInt();

			System.out.println("▒▒▒ ==사이드메뉴==");
			System.out.println("▒▒▒ 1.감자튀김");
			System.out.println("▒▒▒ 2.양념감자");
			System.out.println("▒▒▒ 3.치즈스틱");
			System.out.println("▒▒▒ 4.맥너겟");
			System.out.println("▒▒▒ 5.스위트콘");
			System.out.print("▒▒▒ 번호>> ");
			// 사용자가 주문한 메인메뉴 번호!
			int sideNum = sc.nextInt();

			System.out.println("▒▒▒ ==음료==");
			System.out.println("▒▒▒ 1.코카콜라");
			System.out.println("▒▒▒ 2.스프라이트");
			System.out.println("▒▒▒ 3.마운틴듀");
			System.out.println("▒▒▒ 4.오렌지쥬스");
			System.out.println("▒▒▒ 5.초코쉐이크");
			System.out.print("▒▒▒ 번호>> ");
			// 사용자가 주문한 메인메뉴 번호!
			int drinkNum = sc.nextInt();

			// 출력!!
			// Integer.getInteger()
			// Integer intChgStr = new Integer();
			int total = Integer.parseInt(mainMenu[mainNum - 1][1]) + Integer.parseInt(sideMenu[sideNum - 1][1])
					+ Integer.parseInt(drinkMenu[drinkNum - 1][1]);

			System.out.println("▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
			System.out.println("▒▒▒ 고객님께서 주문하신 메뉴는");
			System.out.println("▒▒▒ 1) " + mainMenu[mainNum - 1][0] + " : " + mainMenu[mainNum - 1][1]);
			System.out.println("▒▒▒ 2) " + sideMenu[sideNum - 1][0] + " : " + sideMenu[sideNum - 1][1]);
			System.out.println("▒▒▒ 3) " + drinkMenu[drinkNum - 1][0] + " : " + drinkMenu[drinkNum - 1][1]);
			System.out.println("▒▒▒ 입니다.");
			System.out.println("▒▒▒ 합계 : " + total + " 원");
			System.out.print("주문하시겠습니까?(y/n)");
			// 엔터키는 2가지 기능을 가지고 있음
			// 1.입력기능
			// 2.한줄개행(\n)
			// JAVA에서 엔터를 입력하면 1번과 2번이 실행 됨.
			// sc.nextInt()는 정수값만 받기 때문에 한줄개행
			// 코드를 무시하지만 sc.nextLine()은 문자열을
			// 입력받아 한줄개행코드를 입력으로 받음...
			// sc.nextLine()을 사용해서 한줄개행입력을
			// 대신 받아주는부분이 필요함!!

			sc.nextLine(); // 개행값처리용
			while (true) {
				String order = sc.nextLine();
				System.out.println(order);
				order.toLowerCase();			// 문자열전부 소문자처리
				// order.toUpperCase();			// 문자열전부 대문자처리
				int money = 0;
				if (order.equals("y")) {
					while (true) {
						// 결재금액 입력
						System.out.println("▒▒ 결제금액>> ");
						// 사용자 지불금액
						money = sc.nextInt();
						if (total > money) {
							System.out.println("금액이 부족합니다.");
							System.out
									.println("합계 : " + total + "원 - 지불금액 : " + money + "원 = " + (total - money) + "원");
							continue;
						} else {
							if (total < money) {
								System.out.println("거스름 돈 " + (money - total) + "원 입니다.");
							} else {
								System.out.println("정확히 " + total + "원 받았습니다.");
							}
							// 지불금액 == 결제금액
							// 지불금액 > 결제금액(거스름돈 주고)
							System.out.println("결제완료! 고객님 맛있게 드세요:)");
							break;
						}
					}
					break;
				} else if (order.equals("n")) {
					// 시스템에 처음으로 돌아가세요!
					// main(args);
					continue;
				} else {
					System.out.println("▒▒  y or n을 입력해주세요.");
					System.out.println("▒▒▒ 합계 : " + total + " 원");
					System.out.print("주문하시겠습니까?(y/n)");					
				}
			}
		}
		// sc.close(); // SCanner 자원 반납
	}

}
