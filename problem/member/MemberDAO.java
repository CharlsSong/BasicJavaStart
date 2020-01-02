package problem.member;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import mybatis.SqlMapConfig;
import problem.DDBoard.DDBoardMain;

public class MemberDAO {
	SqlSessionFactory sqlSessionFactory = SqlMapConfig.getSqlSession();
	SqlSession sqlSession;
	
	List<MemberDTO> list;
	int result;
		
	// tbl_member 로그인
	public int login(String id, String pw) {
		sqlSession = sqlSessionFactory.openSession();
		try {
			HashMap<String, String> map = new HashMap<>();
			map.put("id", id);
			map.put("pw", pw);
			result = sqlSession.selectOne("member.login", map);
			if (result > 0) {
				System.out.println("▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧");
				System.out.println("▨▧ 반갑습니다. " + id + "님 로그인에 되었습니다.  ▨▧");
				System.out.println("▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧");
				DDBoardMain.session = "YES";
				DDBoardMain.userid = id;						
				result = 1;
			} else {
				System.out.println("▨▧ ID 또는 PW가 틀렸습니다. 확인해주세요.");
				result = 0;
			}			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		} finally {
			sqlSession.close();
			
		}
		return result;
	}
	
	// tbl_member 로그아웃
	public void logout() {
		
	}
	
	// tbl_member insert	
	public void insertMember(MemberDTO mDto) {
		sqlSession = sqlSessionFactory.openSession(true);
		try {
			result = sqlSession.insert("member.insertMember", mDto);
			if (result > 0) {
				System.out.println("▨▧ 사용자 ID: " + mDto.getId() + " 등록에 성공하였습니다.");
			} else {
				System.out.println("▨▧ 사용자 등록실패에 하였습니다. 관리자에게 문의하세요.");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		} finally {
			sqlSession.close();
			
		}
	}
	
	// tbl_member id 중복체크
	public int checkId(String id) {
		sqlSession = sqlSessionFactory.openSession();
		try {
			HashMap<String, String> map = new HashMap<>();
			map.put("id", id);			
			result = sqlSession.selectOne("member.checkId", map);
			if (result > 0) {
				System.out.println("▨▧ " + id + "는 사용중인 ID 입니다. 새로운 ID를 입력하세요.");
				result = 1;
			} else {
				System.out.println("▨▧ " + id + "는 사용 가능한 ID입니다.");
				result = 0;
			}			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		} finally {
			sqlSession.close();
		}
		
		return result;
	}
}
