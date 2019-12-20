package problem.bigsmall;
import gugudan.InputCheck;

public class BigSmall02 {

	public static void main(String[] args) {

//		Scanner sc = new Scanner(System.in);
//		
//		System.out.print("번호1>>");
//		int num1 = sc.nextInt();
//		System.out.print("번호2>>");
//		int num2 = sc.nextInt();
//		System.out.print("번호3>>");
//		int num3 = sc.nextInt();
		
		InputCheck inputCheckInt = InputCheck.getInstance();
		System.out.println("번호1>>");
		int num1 = inputCheckInt.inputCheckInt();		
		while (num1 == -1) {		
			System.out.println("번호1>>");
			num1 = inputCheckInt.inputCheckInt();			
		}

		System.out.println("번호2>>");
		int num2 = inputCheckInt.inputCheckInt();		
		while (num2 == -1) {		
			System.out.println("번호2>>");
			num2 = inputCheckInt.inputCheckInt();			
		}
		
		System.out.println("번호3>>");
		int num3 = inputCheckInt.inputCheckInt();		
		while (num3 == -1) {		
			System.out.println("번호3>>");
			num3 = inputCheckInt.inputCheckInt();			
		}
				
		System.out.println(num1 + ", " + num2 + ", " + num3);
		
		int temp = 0;
//		if (num1 > num2) { 
//			if (num2 > num3){
//				
//			}else{
//				temp = num3;
//				num3 = num2;
//				num2 = temp;
//				
//				// num2가 바뀌면 num1과 다시 비교
//				if(num1 > num2) {
//					
//				} else {
//					temp = num2;
//					num2 = num1;
//					num1 = temp;
//				}
//			}				
//		}else {
//			temp = num2;
//			num2 = num1;
//			num1 = temp;
//			
//			if (num2 > num3){
//				
//			}else{
//				temp = num3;
//				num3 = num2;
//				num2 = temp;
//				
//				// num2가 바뀌면 num1과 다시 비교
//				if(num1 > num2) {
//					
//				} else {
//					temp = num2;
//					num2 = num1;
//					num1 = temp;
//				}
//			}			
//		}
		
		if(num1 < num2) {			
			temp = num2;
			num2 = num1;
			num1 = temp;
		}
		
		if(num1 < num3) {			
			temp = num1;
			num1 = num3;
			num3 = temp;
		}
		
		if(num2 < num3) {			
			temp = num2;
			num2 = num3;
			num3 = temp;
		}
				
		System.out.println(num1 + "> " + num2 + "> " + num3);
		
	}

}
