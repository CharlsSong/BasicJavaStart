package problem.bubble;

import java.util.Scanner;

public class BubbleSortMain {
	public static void main(String[] args) {
		Bubble bubble = new Bubble();
		//int[] inputNum = new int[5];
		System.out.println("▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
		System.out.println("▒▒ BubbleSort Ver1.0");
		
		// 정수형 배열[5]에 정수값 입력
		bubble.inputNum();
		System.out.println("▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
		System.out.println("▒▒ 입력결과");
		// 정수형 배열[5]에 입력된 결과 출력
		bubble.viewData();
		System.out.println("▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
		// 정수형 배열[5]에 정렬 수행
		//bubble.sortNum();	// 순차정렬
		bubble.bubbleSort();// 버블정렬
		System.out.println("▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
		System.out.println("▒▒ 정렬결과");
		// 정수형 배열[5] 입력된 결과 출력
		bubble.viewData();
		System.out.println("▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
	}
}
