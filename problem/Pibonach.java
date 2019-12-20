package problem;

import java.util.Scanner;

public class Pibonach {

	public static void main(String[] args) {
		// Depth값 정수 입력
		System.out.println("▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
		System.out.println("▒▒ 숫자 입력 >>");
		Scanner sc = new Scanner(System.in);
		int intNum = sc.nextInt();

		
		int intNum1 = 1;
		int intNum2 = 1;
		int intSum = intNum1 + intNum2;
		int intTemp = 0;
		String strOutput = intNum1 + "+" + intNum2;
		
		for (int i = 2; i < intNum; i++) {
			intTemp = intNum1 + intNum2;	
			intSum += intTemp;				
			intNum1 = intNum2;
			intNum2 = intTemp;
			strOutput += "+" + intTemp;
		}
		
		System.out.println("피보나치 수열 연산과정 : " + strOutput);
		System.out.println("피보나치 수열 연산결과 : " + intSum);
		
		
		// 피보나치용 배열 정의 및 0,1번지 1로 초기화
		int[] piboSum = new int[intNum];
		
		piboSum[0] = 1;
		piboSum[1] = 1;
		
		strOutput=""; // 연산과정 출력
		int piboOutput = 0; // 결과값 출력
		
		//피보나치 연산수행
		for (int i = 2; i < intNum; i++) {
			piboSum[i] = piboSum[i-2] + piboSum[i-1];			
		}
		
		// 결과값 생성
		for (int i = 0; i < piboSum.length; i++) {
			// 결과값 출력용
			piboOutput += piboSum[i];
			
			// 연산과정 출력용
			if(i == intNum -1) {	// 마지막 값 "+" 표시 제거용
				strOutput += piboSum[i];
			} else {
				strOutput += piboSum[i] + "+";
			}
		}
		
		//결과값 출력
		System.out.println("피보나치 수열 연산과정 : " + strOutput);
		System.out.println("피보나치 수열 연산결과 : " + piboOutput);
			
	}
}
