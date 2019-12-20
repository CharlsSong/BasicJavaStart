package problem.gugudan;

public class GuGuPrint {

	public void guguDan(int dansu) {

		System.out.println("구구단" + dansu + "단 출력");
		System.out.println("===============");
		int result;
		for (int i = 1; i <= 9; i++) {
			result = dansu * i;	// 변수선언, 초기화X
			System.out.println(dansu + " x " + i + " = " + result);
		}
		
	}
	
}
