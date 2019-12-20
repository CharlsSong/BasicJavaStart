package problem.parentchild;

public class TestParentChild {

	public static void main(String[] args) {
		
		TestParent testParent = new TestParent();
		TestChild testChild = new TestChild();
		TestChildNoExtends testChildNoExtends = new TestChildNoExtends();
		
		testParent.givePresents();		
		
		testChild.givePresents();	// this가 상속받은 메서드
		testChild.takePresents();	// this가 정의한 메서드
		
		testChildNoExtends.testParent.givePresents();	// this에 생성된 인스턴스의 메서드		
		testChildNoExtends.takePresents();;				// this가 정의한 메서드
	}
}
