package problem.bubble;

import java.util.Scanner;

public class Bubble {
	int[] intBubble = new int[5];

	public void viewData() {
		for (int i = 0; i < intBubble.length; i++) {
			System.out.println(intBubble[i]);			
		}		
	}
	
	public boolean duplicateNum(int inputCheck) {	// 중복값 확인 : 중복값이 있으면 false, 없으면 true 반환
		// 중복값확인결과를 반환할 변수 선언
		boolean flag = true;
		
		// 5번 반복하며 중복여부 확인
		for (int j = 0; j < intBubble.length; j++) {				
			if (inputCheck == intBubble[j]) {
				System.out.println("▒▒ " + inputCheck + "은(는) 이미 입력하신 값입니다.");
				flag = false;	// 중복이 있으면 false로 바꿈
			}					
		}
		return flag;	// 확인결과 반환
	}
	
		
	public void inputNum() {
		Scanner sc = new Scanner(System.in);		
		int inputCheck = 0;		

		// 입력값 입력 반복문(5회)
		for (int i = 0; i < intBubble.length; i++) {
			while (true) {
				System.out.println("▒▒ 숫자" + (i + 1) + "번>>");
				inputCheck = sc.nextInt();
				
				// 중복값 확인 메서드 호출 : 중복이 없으면 break;												
				if(duplicateNum(inputCheck)) {
					break;
				}
			}		
			// 입력값 배열i번지에 입력
			intBubble[i] = inputCheck;
		}		
		// sc instnance 반환
		sc.close();
		// 입력값 배열 반환
		return;
	}
	
	// 정수형 배열을 매개변수로 받아 
	// 순차정렬을 완료한 정수형 배열을 반환하는 메소드
	public void sortNum() {
		int temp = 0;	// 바꾸기용
		for (int i = 0; i < intBubble.length; i++) {	// 배열전체의 각 i번지별로 반복
			for (int j = i; j < intBubble.length; j++) {	// 현재 i번지와 i번지 이후 번지들을 모두 비교
				if(intBubble[i] > intBubble[j]) {	// 현재 i번지가 j번지 보다 크면 큰값을 뒤로보냄 
					temp = intBubble[j];			// j번지를 temp로 보냄
					intBubble[j] = intBubble[i];	// i번지를 j번지로 보냄
					intBubble[i] = temp; 		// i번지에 temp를 넣음
				}
			}
		}			
		// 정렬이 완료된 배열을 반환
		return;
	}

	// 정수형 배열을 매개변수로 받아 
	// 버블정렬을 완료한 정수형 배열을 반환하는 메소드
	public void bubbleSort() {
		int temp = 0;	// 바꾸기용
		for (int i = 0; i < intBubble.length; i++) {	// 배열전체의 각 i번지별로 반복
			for (int j = 0; j < intBubble.length-1-i; j++) {	// 현재 i번지와 i번지 이후 번지들을 모두 비교
				if(intBubble[j] > intBubble[j+1]) {	// 현재 i번지가 j번지 보다 크면 큰값을 뒤로보냄 
					temp = intBubble[j+1];			// j번지를 temp로 보냄
					intBubble[j+1] = intBubble[j];	// i번지를 j번지로 보냄
					intBubble[j] = temp; 		// i번지에 temp를 넣음
				}
			}
		}			
		// 정렬이 완료된 배열을 반환		
		return;
	}
}
