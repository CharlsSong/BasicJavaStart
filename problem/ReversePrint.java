package problem;

import java.util.Scanner;

import gugudan.InputCheck;

public class ReversePrint {
	// 조건
	// 1. 사용자가 임의의 정수를 입력
	// 2. 사용자가 입력한 정수를 역으로 1까지 출력
	// 출력 예제
	// 입력 >> 5
	// 5
	// 4
	// 3
	// 2
	// 1
	public static void main(String[] args) {
		
		// 키보드 입력한 정수값
		System.out.println("입력 >>");		
		InputCheck inputCheckInt = InputCheck.getInstance();
		int num = inputCheckInt.inputCheckInt();
		
		while (num == -1) {
			System.out.println("입력 >>");			
			num = inputCheckInt.inputCheckInt();
		}
		
		for (int i = 1; i <= num; i++) {
			System.out.println(i);
		}
		
		// 역순 출력
		for (int i = 1; i <= num; i++) {
			System.out.println(num-i+1);
		}
		
	}
}
