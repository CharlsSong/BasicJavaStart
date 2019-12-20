/*
 * desc		: Park Class를 이용하여 
 * 			  차량을 입고 또는 출고하는 주차타워 프로그램
 * writer	: CharlsSong
 * date		: 2019.12.03.
 * update	:
 * updater	:
 * remarks	:
 */
package problem.ezparking;

import java.util.Scanner;

public class EZParkingMain {

	public static void main(String[] args) {		
		Scanner sc = new Scanner(System.in);
		Parking park = new Parking();
		
		while (true) {
			System.out.println("▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
			System.out.println("▒▒ EZ Parking Ver1.0");
			System.out.println("▒▒ Car Number>> ");
			int car = sc.nextInt(); // 주타타워를 이용할 차량번호
			int code = 0; // 사용자가 선택한 번호!
			while (true) {
				System.out.println("▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
				System.out.println("▒▒ 1. 차량입고");
				System.out.println("▒▒ 2. 차량출고");
				System.out.println("▒▒ 3. 취소");
				System.out.println("▒▒ 4. 주차현황");
				System.out.println("▒▒ 0. 종료");
				System.out.println("▒▒ 선택>>");
				code = sc.nextInt(); // 입고, 출고, 취소를 선택한 번호
				
				if (code > 4 || code < 0) {
					System.out.println("▒▒ 올바른값을 입력하세요.");
					continue;
				} else { // code가 1,2,3인 경우
					break;
				}
			}

			if (code == 0) { // 종료
				System.out.println("▒▒ 종료합니다. 다음에 다시 이용해 주세요.");
				return;				
			} else if (code == 1) { // 차량입고
				park.inParking(car);				
			} else if (code == 2) { // 차량출고
				park.outParking(car);
			} else if (code == 3) { // 취소
				car = 0; // 기존입력 차량번호 Clear
				System.out.println("▒▒ 취소되었습니다. 다음에 이용해 주세요.");
				continue;
			} else {
				System.out.println("▒▒ 주차현황입니다. 감사합니다.");
				park.viewParking();								
			}
		}
	}
}