/*
 * desc		: Array를 사용하여 주차공간을 만들고
 * 			  차량을 입고 또는 출고하는 주차타워 Class
 * writer	: CharlsSong
 * date		: 2019.12.03.
 * update	: 2019.12.04.
 * updater	: CharlsSong
 * remarks	: 1. searchCar() 메소드 추가 : 2019.12.04./
 */

package problem.ezparking;

public class Parking {

	int[] space = new int[5]; // 주차공간(5대), 전역변수 자동초기화
	int carCnt; // 현재 주차된 차량수, 전역변수 자동초기화
	
	public Parking() {			// 생성자(생략시 자동생성)
	}			
	
	// 1. 주차현황
	public void viewParking() {
		System.out.println("============================");	// 주차상태 표시
		for (int j = 0; j < space.length; j++) {
			System.out.println("▒▒ II " + (j + 1) + "층 : " + space[j] + "번호 차량 " + "II");
		}
		System.out.println("============================");
	}
	
	// 2. 주차타워 입고
	public void inParking(int car) {
		// 차량입고 비지니스로직
		// 1.주차공간이 여유가 있는지 체크!
		//  full: 2번 타워를 이용하세요~ 끝냄
		//	ok: 2번으로 넘어감
		// 2.차량번호 중복체크
		//	중복: 차량번호를 다시 입력해주세요~ 끝.
		//	ok: 3번으로 넘어감
		// 3.주차타워에 차량을 입고!
		//	ok: 입고완료!, 주차현황 출력!
		
		// 만차일때
		if(this.carCnt == this.space.length) {
			System.out.println("▒▒ 만차입니다. 2번 타워를 이용해 주세요.");
			//car = 0; // 기존입력 차량번호 Clear
			return;		// 메서드 실행종료!					
		}
		
		// 중복차량유무 체크!
		if (this.searchCar(car)) {	// 중복차량						
			this.viewParking();
			System.out.println("▒▒ " + car + "번호 차량은 이미 입고된 차량입니다. 차량번호를 다시 입력하세요.");
//			System.out.println((i + 1) + "층 차고의 " + car + "번호 차량은 이미 입고된 차량입니다. 감사합니다.");
			return;		// 메서드 실행종료!
		}
		//}
		
		for (int i = 0; i < this.space.length; i++) {	// 빈공간에 주차
			if (this.space[i] == 0) {
				this.space[i] = car;					// 주차		
				this.viewParking();
				System.out.println("▒▒ " + (i + 1) + "층 차고에 " + car + "번호 차량이 입고되었습니다. 감사합니다.");
				this.carCnt++;	// 주차대수 증가
				// car = 0;	// 차량번호 초기화
				return;		// 메서드 실행종료!
			}
		}
	//	return;		// 없어도 자동 return;
	}
	
	// 3. 주차타워 출고
	public void outParking(int car) {
		// 주차된 차량이 없을 때
		if (this.carCnt == 0) {
			System.out.println("▒▒ 주차장에 주차된 차량이 없습니다.관리자에게 확인해주십시요.");
			// car = 0;
			return;		// 메서드 실행종료!					
		}
		
		// 입고된 차량이 있을 때
		for (int i = 0; i < this.space.length; i++) {
			if (this.space[i] == car) {
				this.space[i] = 0;
				this.viewParking();
				System.out.println("▒▒ " + car + "번호 차량이 출차되었습니다. 감사합니다.");
				this.carCnt--;						
				return;		// 메서드 실행종료!
			} 
		}		
		// 출고차량이 없을 때
		System.out.println("▒▒ " + car + "번호 차량이 없습니다.관리자에게 확인해주십시요.");
		// car = 0;						
	//	return;		// 없어도 자동 return;
	}
	
	// 4. 검색: 현재차량번호 기존에 등록된 차량번호 중복체크
	public boolean searchCar(int car) {
		boolean flag = false;	// 중복유무 판별 (0:정상, 1:중복)
		for (int i = 0; i < this.space.length; i++) {	// 동일 차량번호 확인
			if (this.space[i] == car) {
				flag = true;		// 차량번호 중복!!				
			}
		}
		return flag; // 중복유무 리턴값 전달!
	}
}
