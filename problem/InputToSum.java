package problem;

import java.util.Scanner;

public class InputToSum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
		System.out.println("▒▒ 숫자입력 >>");
		int intNum = sc.nextInt();
		// String strNum = Integer.toString(intNum);
		
		int sum = 0;
		
//		System.out.println(strNum.substring(0, 1));
//		System.out.println(strNum.substring(1, 2));
//		System.out.println(strNum.substring(2, 3));
		
		// Java
//		for (int i = 0; i < strNum.length(); i++) {
//			sum += Integer.parseInt(strNum.substring(i, i+1)); 
//		}
		
		// C
//		for (int i = 0; i < 10; i++) {
//		
//			sum += intNum%10;
//			intNum = intNum/10;
//			System.out.println(sum);
//		}
		
		// Java + C
		int length = (int)(Math.log10(intNum)+1);
		for (int i = 0; i < length; i++) {
			sum += intNum%10;
			intNum = intNum/10;
			System.out.println(sum);			
		}			
	}

}
