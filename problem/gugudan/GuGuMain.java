package problem.gugudan;

import java.util.Scanner;

public class GuGuMain {

	public static void main(String[] args) {
		
		// JAVA는 프로그램 실행시 무조건 
		// Main 메서드부터 시작!!!
		
		// 구구단 2단 출력
		// 2 x 1 = 2
		// 2 x 2 = 4
		// 2 x 3 = 6
		// 2 x 4 = 8
		// 2 x 5 = 10
		// 2 x 6 = 12
		// 2 x 7 = 14
		// 2 x 8 = 16
		// 2 x 9 = 18
		
		// 1. 사용자에게 단수를 입력받는 부분
		// 2. 구구단을 출력하는 부분
		
		// Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("▒▒▒▒▒ 구구단 출력 ▒▒▒▒▒");
			System.out.println("▒▒▒▒▒ 단수 입력(0: 프로그램종료) >> ");
			
			// int dansu = sc.nextInt();
			InputCheck inputCheckInt = InputCheck.getInstance();
			int dansu = 0;
			while(true) {
				dansu = inputCheckInt.inputCheckInt(0,9);
				
				if (dansu != -1) {
					break;
				} else {
					continue;
				}
				
			}
			
			GuGuPrint guguPrint = new GuGuPrint();
			guguPrint.guguDan(dansu);
			
			if (dansu == 0) {
				System.out.println("▒▒▒▒▒ 프로그램종료 ▒▒▒▒▒");
				System.exit(0);
			} else {
				continue;
			}
			
		}
		
//		System.out.println("구구단" + dansu + "단 출력");
//		System.out.println("===============");
//		int result;
//		for (int i = 1; i <= 9; i++) {
//			result = dansu * i;	// 변수선언, 초기화X
//			System.out.println(dansu + " x " + i + " = " + result);
//		}
//		
//		System.out.println("===============");
//		System.out.println("구구단 2단 출력");
//		
//		for (int i = 1; i <= 9; i++) {
//			System.out.println("2 x " + i + " = " + (2*i));
//		}
//		
//		System.out.println("===============");
//		System.out.println("***************");		
//		System.out.println("구구단 출력");
//		
//		for (int i = 1; i <= 9; i++) {
//			System.out.println("===============");
//			System.out.println("구구단 " + i + "단 출력");
//			for (int j = 1; j <= 9; j++) {
//				System.out.println(i +" x " + j + " = " + (i*j));
//			}			
//		}
		
	}

}
