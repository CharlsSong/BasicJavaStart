package problem.DDEnter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

import jdpc.DBManager;

public class DDEnter {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 6번 입력될때까지 계속 반복
		while (true) {
			System.out.println("▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦");
			System.out.println("▦▦ 더블디 엔터 관리 시스템 Ver 1.0");
			System.out.println("▦▦ 1.아티스트 등록");
			System.out.println("▦▦ 2.아티스트 수정");
			System.out.println("▦▦ 3.아티스트 해지");
			System.out.println("▦▦ 4.아티스트 조회");
			System.out.println("▦▦ 5.아티스트 검색");
			System.out.println("▦▦ 6.프로그램 종료");
			System.out.println("▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦");

			// 사용자가 1~6까지 입력하기 전까지는 계속반복
			int code = 0;
			while (true) {
				System.out.println("▦▦ 번호>>");
				code = sc.nextInt();

				if (code >= 1 && code <= 6) {
					break;
				} else {
					System.err.println("▦▦ 1~6까지의 번호만 선택하세요.");
					continue;
				}
			}
			// DB접근용 DAO
			MemberDAO mDao = new MemberDAO();
			// 사용자가 입력한 값이 1~6인경우
			if (code == 1) {
				System.out.println("▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦");
				System.out.println("▦▦ 계약할 아티스트정보를 입력하세요");
				System.out.println("▦▦ 이름>>");				
				sc.nextLine();
				String aname = sc.nextLine();
				
				System.out.println("▦▦ 분야>>");
				String major = sc.nextLine();
				System.out.println("▦▦ 그룹유무(y/n)>>");
				String groupyn = sc.nextLine();				
				System.out.println("▦▦ 그룹이름>>");
				String groupnm = sc.nextLine();				
				System.out.println("▦▦ 연봉>>");
				int sal = sc.nextInt();
				// DB입출력값을 관리하기 위한 DTO
				MemberDTO mDto = new MemberDTO(aname, major, groupyn, groupnm, sal);
				
				mDao.memInsert(mDto);
			} else if (code == 2) { // 소속아티스트 수정
				System.out.println("▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦");
				System.out.println("▦▦ 수정할 아티스트번호를 입력하세요");
				System.out.println("▦▦ 번호>>");
				sc.nextLine();
				String ano = sc.nextLine();
				// 이름, 전공, 그룹유무, 그룹이름, 연봉
				System.out.println("▦▦ 이름>>");
				String aname = sc.nextLine();
				System.out.println("▦▦ 분야>>");
				String major = sc.nextLine();
				System.out.println("▦▦ 그룹유무(y/n)>>");
				String groupyn = sc.nextLine();				
				System.out.println("▦▦ 그룹이름>>");
				String groupnm = sc.nextLine();				
				System.out.println("▦▦ 연봉>>");
				int sal = sc.nextInt();
				
				MemberDTO mDto = new MemberDTO(ano, aname, major, groupyn, groupnm, sal);
				mDao.memUpdate(mDto);
			} else if (code == 3) {	// 소속아티스트 삭제
				System.out.println("▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦");
				System.out.println("▦▦ 삭제할 아티스트번호를 입력하세요");
				System.out.println("▦▦ 번호>>");
				sc.nextLine();
				String ano = sc.nextLine();

				mDao.memDelete(ano);
			} else if (code == 4) {
				System.out.println("▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦");
				System.out.println("▦▦ 회사에 소속된 아티스트 명단입니다.");
				
				mDao.memSelect();
			} else if (code == 5) {
				System.out.println("▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦▦");
				System.out.println("▦▦ 검색할 아티스트의 이름을 입력하세요.");
				System.out.println("▦▦ 이름>>");
				sc.nextLine();	// 개행값 처리
				String aname = sc.nextLine();

				mDao.memSearch(aname);
			} else {
				System.out.println("▦▦ <프로그램종료>");
				System.exit(0);
			}

		}

	}
}
