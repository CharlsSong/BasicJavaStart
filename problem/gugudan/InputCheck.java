package problem.gugudan;

import java.util.Scanner;

public class InputCheck {

	private static InputCheck instance = new InputCheck();
	private InputCheck() {}
	
	public static InputCheck getInstance() {
		if(instance == null) {
			instance = new InputCheck();			
		}
		return instance;
	}
	
	public int inputCheckInt() {

		try {

			Scanner sc2 = new Scanner(System.in);
			int score = sc2.nextInt();
			sc2.nextLine();
			
			if (score < 0) {
				System.err.println("0 보다 큰 정수만 입력하세요.");
				return -1;
			} else {

				return score;
			}

			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("정수만 입력하세요.");
			return -1;
		}
	}

	public int inputCheckInt(int start, int end) {

		try {

			Scanner sc2 = new Scanner(System.in);
			int score = sc2.nextInt();
			sc2.nextLine();

			if (score >= start && score <= end) {
				return score;
			} else {
				System.err.println(start + "~" + end + "까지 정수만 입력하세요.");
				return -1;
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("정수만 입력하세요.");
			return -1;
		}
	}

}
