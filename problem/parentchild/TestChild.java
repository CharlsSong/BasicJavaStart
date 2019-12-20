
package problem.parentchild;

public class TestChild extends TestParent{
	
	public void takePresents() {		
		super.givePresents();		
		System.out.println("크리스마스 선물 고마워요");
	}
	
	@Override
	public void givePresents() {
		// TODO Auto-generated method stub
		//super.givePresents();
	}
	
}
