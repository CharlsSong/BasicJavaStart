package mybatis;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlMapConfig {
	private static SqlSessionFactory sqlSessionFactory;
	
	static {		// 정적 블럭 : 클래스 로딩시 1회만 실행되는 코드
		String resource = "mybatis/Configuration.xml";
		try {
					// myBatis의 Resources.getResourceAsReader() method 수행
			Reader reader = Resources.getResourceAsReader(resource);
			
			if(sqlSessionFactory == null) {
				sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader); // build 패턴 : OutSourcing 방식의 객체생성 : 객체생성구조가 복잡할때 사용
			}
		} catch (IOException e) {
			// TODO: handle exception
		}
	}
	
	public static SqlSessionFactory getSqlSession() {
		return sqlSessionFactory;
	}
}
