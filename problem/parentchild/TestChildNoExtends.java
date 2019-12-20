package problem.parentchild;

public class TestChildNoExtends {
	
	TestParent testParent = new TestParent();
	
	public void takePresents() {		
		testParent.givePresents();
		System.out.println("크리스마스 선물 고마워요");
		
	}
	
}
