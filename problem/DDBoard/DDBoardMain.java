package problem.DDBoard;

import java.util.Scanner;

public class DDBoardMain {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		BoardDAO bDao = new BoardDAO();
		int code = 0; 	// 사용자가 선택한 프로그램 번호

		while(true) {
			System.out.println("▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧");
			System.out.println("▨▧ 더블디 게시판");
			
			bDao.boardSelect();					// 전체 게시글 표시
			
			System.out.println("▨▧ 1. 게시글 등록");
			System.out.println("▨▧ 2. 게시글 수정");
			System.out.println("▨▧ 3. 게시글 삭제");
			System.out.println("▨▧ 4. 게시글 조회");
			System.out.println("▨▧ 5. 게시글 검색");
			System.out.println("▨▧ 6. 게시글 정렬");
			System.out.println("▨▧ 7. 상세 게시글");
			System.out.println("▨▧ 8. 만든이");
			System.out.println("▨▧ 9. 프로그램종료");
			System.out.println("▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧");
			
			while(true) {
				System.out.println("▨▧ 번호>>");
				code = sc.nextInt();
				sc.nextLine(); // 개행처리
				
				if(code >= 1 && code <=9) {
					break;
				} else {
					System.out.println("1~9까지 숫자만 입력하세요.");
					continue;
				}				
			}
			
			if(code == 1) {			// 1. 게시글 등록
				System.out.println("▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧");
				System.out.println("▨▧ 더블디 게시판");
				System.out.println("▨▧ 1. 게시글 등록");
				System.out.println("▨▧ 게시글 제목>>");
				String title = sc.nextLine();
				System.out.println("▨▧ 게시글 내용");
				String content = sc.nextLine();
				System.out.println("▨▧ 게시글 작성자");
				String writer = sc.nextLine();
				
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
				System.out.println("▨▧ 게시글 작성자");
				String writer = sc.nextLine();
				
				BoardDTO bDto = new BoardDTO(bno, title, content, writer);
				bDao.boardUpdate(bDto);
			} else if(code == 3) {	// 3. 게시글 삭제
				System.out.println("▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧");
				System.out.println("▨▧ 더블디 게시판");
				System.out.println("▨▧ 3. 게시글 삭제");
				System.out.println("▨▧ 삭제할 게시글 번호>>");
				int bno = sc.nextInt(); 
				sc.nextLine();
				bDao.boardDelete(bno);
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
				
				int result = bDao.viewCntPlus(bno);
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
				
			} else {				// 9. 프로그램 종료
				System.out.println("▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧");
				System.out.println("▨▧ [프로그램 종료]");
				System.exit(0);
			}
				
		}
		
		
		
	}
}
