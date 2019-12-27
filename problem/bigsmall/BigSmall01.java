package problem.bigsmall;

import problem.gugudan.InputCheck;

public class BigSmall01 {

	public static void main(String[] args) {
		// 사용자가 2개의 값을 입력
		// -- 입력 --
		// 번호1 >> 5
		// 번호2 >> 7
		//
		// --조건--
		// 번호1이 번호2보다 항상 큰값을 가지도록 교환
		
		InputCheck inputCheckInt = InputCheck.getInstance();
		System.out.println("번호1 >> ");
		int num1 = inputCheckInt.inputCheckInt();
		while( num1 == -1) {
			System.out.println("번호1 >> ");
			num1 = inputCheckInt.inputCheckInt();
		}
		
		System.out.println("번호2 >> ");
		int num2 = inputCheckInt.inputCheckInt();
		while( num2 == -1) {
			System.out.println("번호2 >> ");
			num2 = inputCheckInt.inputCheckInt();
		}
		
		System.out.println(num1 + ", " + num2);
		
		if (num1 < num2) {
				
		} else {
			int temp = num1;
			num1 = num2;
			num2 = temp;
		}
				
		System.out.println(num1 + ", " + num2);
		
	}

}
