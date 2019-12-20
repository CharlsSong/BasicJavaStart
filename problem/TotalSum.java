package problem;

public class TotalSum {
	// 1 ~ 100까지 더해서 출력하시오.	
	public static void main(String[] args) {
		// 1~100까지 더해서 출력하세요		
		int total = 0;
		for (int i = 1; i <= 100; i++) {			
			total += i;
			System.out.println((total-i) +" + "+ i + " = " + total);
		}
		System.out.println("=====================");
		System.out.println("1~100까지 합계 : " + total);
		System.out.println("=====================");		
		
		// 1~100까지 수중에 짝수만 더해서 출력하세요		
		total = 0;		
		for (int i = 1; i <= 100; i++) {			
			if (i % 2 == 0) {
				total += i;
				System.out.println((total-i) +" + "+ i + " = " + total);
			}
		}
		System.out.println("==========================");
		System.out.println("1~100까지 짝수 합계 : " + total);
		System.out.println("==========================");
		
		// 1~100까지 수중에 짝수끼리 홀수끼리 더해서 출력하세요		
		int totalOdd = 0;
		int totalEven = 0;
		for (int i = 1; i <= 100; i++) {			
			if (i % 2 == 0) {
				totalEven += i;
				System.out.println("짝수 : " + (totalEven-i) +" + "+ i + " = " + totalEven);
			} else {
				totalOdd += i;
				System.out.println("홀수 : " + (totalOdd-i) +" + "+ i + " = " + totalOdd);
			}
		}
		System.out.println("======================================================");
		System.out.println("1~100까지 짝수 합계 : " + totalEven +", 1~100까지 홀수 합계 : " + totalOdd);
		System.out.println("======================================================");
	}
	
}
