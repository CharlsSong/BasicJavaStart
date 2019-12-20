package problem.calc;

import java.util.Scanner;

// 사칙연산이 가능한 계산기 프로그램
public class CalcMain {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Calculater calc = new Calculater();
		while (true) {
			System.out.println("▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
			System.out.println("▒▒ 더하고나누고 Ver1.0");
			System.out.print("▒▒ 숫자>>");
			int first = sc.nextInt();
			
			sc.nextLine(); // 개행처리
			
			System.out.print("▒▒ 연산자(+,-,x,/)>>");
			String oper = sc.nextLine();
			
			System.out.print("▒▒ 숫자>>");
			int second = sc.nextInt();
			
			// System.out.println(first+","+oper+","+second);
			int result = 0;
			
			if (oper.equals("+")) {
				result = calc.sum(first, second);
			} else if (oper.equals("-")) {
				result = calc.sub(first, second);
			} else if (oper.equals("x")) {
				result = calc.mul(first, second);
			} else if (oper.equals("/")) {
				result = calc.div(first, second);
			} else if  (oper.equals("s")){
				System.out.println("▒▒ 사칙연산을 종료합니다.");
				break;
			} else {
				System.out.println("▒▒ 연산자가 잘못 입력되었습니다.");
				continue;
			}
			
			System.out.println("▒▒ 결과>> " + first + " " + oper + " " + second + " = " + result);
			
		}
	}
}
