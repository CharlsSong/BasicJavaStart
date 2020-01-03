package problem.DDBoard;

import java.util.Scanner;

import problem.member.MemberDAO;
import problem.member.MemberDTO;

public class DDBoardMain {
	public static String session = "NO";	// 로그인 유무(YES:LOGIN OK)
	public static String userid = "";		// 로그인 유저의 ID값
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		BoardDAO bDao = new BoardDAO(); // 게시글 관련기능
		int result = 0;
		
		
		// 로그인
		if (session.equals("NO")) {
			login();
		}

		int code = 0; 	// 사용자가 선택한 프로그램 번호

		while(true) {
			System.out.println("▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧");
			System.out.println("▨▧ 더블디 게시판");
			
			bDao.boardSelect();					// 전체 게시글 표시
			
			//System.out.println("▨▧ 0. 로그인");
			if (session.equals("YES")) {
				System.out.println("▨▧ 0. 로그아웃");
			} else {
				System.out.println("▨▧ 0. 로그인");
			}
			System.out.println("▨▧ 1. 게시글 등록");
			System.out.println("▨▧ 2. 게시글 수정");
			System.out.println("▨▧ 3. 게시글 삭제");
			System.out.println("▨▧ 4. 게시글 조회");
			System.out.println("▨▧ 5. 게시글 검색");
			System.out.println("▨▧ 6. 게시글 정렬");
			System.out.println("▨▧ 7. 상세 게시글");
			System.out.println("▨▧ 8. 만든이");
			System.out.println("▨▧ 9. 프로그램종료");
			if (session.equals("YES")) {
				System.out.println("▨▧ " + DDBoardMain.userid + "님 방문을 환영합니다.");
			}
			System.out.println("▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧");
			
			while(true) {
				System.out.println("▨▧ 번호>>");
				code = sc.nextInt();
				sc.nextLine(); // 개행처리
				
				if(code >= 0 && code <= 9) {
					break;
				} else {
					System.out.println("0~9까지 숫자만 입력하세요.");
					continue;
				}				
			}
			
			if(code == 1) {			// 1. 게시글 등록
				if(DDBoardMain.session.equals("NO")) {
					System.out.println("▨▧ 로그인이 필요한 기능입니다.");
					continue;
				}
				
				System.out.println("▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧");
				System.out.println("▨▧ 더블디 게시판");
				System.out.println("▨▧ 1. 게시글 등록");
				System.out.println("▨▧ 게시글 제목>>");
				String title = sc.nextLine();
				System.out.println("▨▧ 게시글 내용");
				String content = sc.nextLine();
//				System.out.println("▨▧ 게시글 작성자");
//				String writer = sc.nextLine();
				String writer = DDBoardMain.userid;
				
				BoardDTO bDto = new BoardDTO(title, content, writer);
				bDao.boardInsert(bDto);
				
			} else if(code == 2) {	// 2. 게시글 수정
				System.out.println("▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧");
				System.out.println("▨▧ 더블디 게시판");
				System.out.println("▨▧ 2. 게시글 수정");
				System.out.println("▨▧ 수정할 게시글 번호>>");
				int bno = sc.nextInt(); 
				sc.nextLine();
				
				System.out.println("▨▧ 게시글 제목>>");
				String title = sc.nextLine();
				System.out.println("▨▧ 게시글 내용");
				String content = sc.nextLine();
//				System.out.println("▨▧ 게시글 작성자");
//				String writer = sc.nextLine();
				String writer = DDBoardMain.userid;
				
//				System.out.println(title + ", " + content + ", " + writer);
				BoardDTO bDto = new BoardDTO(bno, title, content, writer);
				bDao.boardUpdate(bDto);
			} else if(code == 3) {	// 3. 게시글 삭제
				System.out.println("▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧");
				System.out.println("▨▧ 더블디 게시판");
				System.out.println("▨▧ 3. 게시글 삭제");
				System.out.println("▨▧ 삭제할 게시글 번호>>");
				int bno = sc.nextInt(); 
				sc.nextLine();
				String writer = DDBoardMain.userid;
				bDao.boardDelete(bno, writer);
			} else if(code == 4) {	// 4. 게시글 조회
				System.out.println("▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧");
				System.out.println("▨▧ 더블디 게시판");
				System.out.println("▨▧ 4. 게시글 조회");
				
				bDao.boardSelect();
			} else if(code == 5) {	// 5. 게시글 검색
				System.out.println("▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧");
				System.out.println("▨▧ 더블디 게시판");
				System.out.println("▨▧ 5. 게시글 검색");
				System.out.println("▨▧ 검색>>");
				String search = sc.nextLine();
				
				bDao.boardSearch(search);
			} else if(code == 6) {	// 6. 게시글 정렬
				System.out.println("▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧");
				System.out.println("▨▧ 더블디 게시판");
				System.out.println("▨▧ 6. 게시글 정렬");				
				int column = 0;
				while(true) {
					System.out.println("▨▧ 정렬값(1:게시글 번호, 2:등록일자, 3:조회수)>>");
					column = sc.nextInt(); 
					sc.nextLine();
					if(column >=1 && column <=3) {
						break;
					} else {
						System.out.println("1~3의 숫자만 입력하세요.");
						continue;
					}
				}
				
				int order = 0;
				while(true) {
					System.out.println("▨▧ 정렬순선(1:오름차순, 2:내림차순)>>");
					order = sc.nextInt(); 
					sc.nextLine();
					if(order >=1 && order <=2) {
						break;
					} else {
						System.out.println("1~2의 숫자만 입력하세요.");
						continue;
					}
				}
				
				bDao.boardSort(column, order);
			} else if(code == 7) {	// 7. 상세 게시글				
				System.out.println("▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧");
				System.out.println("▨▧ 더블디 게시판");
				System.out.println("▨▧ 7. 상세 게시글");
				System.out.println("▨▧ 보고싶은 게시글 번호를 입력하세요");
				System.out.println("▨▧ 게시글 번호>>");
				int bno = sc.nextInt();
				sc.nextLine();
				
				result = bDao.viewCntPlus(bno);
				if(result < 0) {
					continue ;					
				}
								
				bDao.boardView(bno);
			} else if(code == 8) {	// 8. 만든이
				System.out.println("▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧");
				System.out.println("▨▧ Name: DD Board Program");
				System.out.println("▨▧ Version: 1.7");
				System.out.println("▨▧ Use: JAVA, ORACLE");
				System.out.println("▨▧ Date: 2019.12.17");
				System.out.println("▨▧ made by Kcumero");
				System.out.println("▨▧ kcumero@hanmail.net");
				
			} else if(code == 0) {	// 0. 로그인 로그아웃 
				System.out.println("▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧");
				System.out.println("▨▧ 더블디 게시판");

				
				if (DDBoardMain.session.equals("YES")) {
					System.out.println("▨▧ 0. 로그아웃");
					DDBoardMain.session = "NO";
					System.out.println("▨▧  ID : "  + DDBoardMain.userid + " 로그아웃 되었습니다.");
					DDBoardMain.userid = "";
					
				} else {
					System.out.println("▨▧ 로그인이 필요한 기능입니다. 로그인 해주세요.");					
					login();
				}
				
			} else {				// 9. 프로그램 종료
				System.out.println("▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧");
				System.out.println("▨▧ [프로그램 종료] ▨▧");
				System.out.println("▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧");
				System.exit(0);
			}
					
		}
		
	}

	// 로그인
	public static void login() {
		Scanner sc1 = new Scanner(System.in);
		MemberDAO mDao = new MemberDAO();	//회원관련 기능		
		int result = 0;
		
		while(true) {
			
			System.out.println("▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧");
			System.out.println("▨▧ 더블디 게시판");
			System.out.println("▨▧ 0. 로그인");
			System.out.println("▨▧ ID >>");
			String id = sc1.nextLine();
			System.out.println("▨▧ PW >>");
			String pw = sc1.nextLine();
			
			int flag = mDao.login(id, pw);
			
			String yesNo ="";
			
			// 로그인 실패시 사용자등록
			if(flag == 0) {
				while(true) {
					System.out.println("▨▧ 사용자등록 하시겠습니까?(y/n) >>");
					yesNo = sc1.nextLine();
					if(yesNo.equals("y") || yesNo.equals("n") ) {
						break;
					} else {
						System.out.println("▨▧ y 또는 n 만 입력하세요.");
						continue;
					}
				}
				
				// 사용자 등록 
				if(yesNo.equals("y")) {	//사용자 등록할때
					
					// ID중복체크
					while(true) {
						System.out.println("▨▧ 더블디 게시판");
						System.out.println("▨▧ 0. 사용자 등록");
						System.out.println("▨▧ ID >>");
						id = sc1.nextLine();
						
						int resultId = mDao.checkId(id);
						if (resultId > 0) {
							continue;
						} else {
							break;
						}							
					}
				
					System.out.println("▨▧ pw >>");
					pw = sc1.nextLine();
					
					System.out.println("▨▧ 이름 >>");
					String name = sc1.nextLine();
					
					System.out.println("▨▧ 전화번호(010-1111-1111) >>");
					String phone = sc1.nextLine();
					
					MemberDTO mDto = new MemberDTO(id, pw, name, phone);
					mDao.insertMember(mDto);					
					
				} else { // 사용자 등록을 하지 않을 때
					
					DDBoardMain.session = "NO";
					DDBoardMain.userid = "";
				}
				continue;
			} else {	// 로그인 성공 시 			
				break;
			}
		}

	}
	
}
