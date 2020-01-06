package problem.marcket;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import mybatis.SqlMapConfig;

public class SaleDAO {	
	SqlSessionFactory sqlSessionFactory = SqlMapConfig.getSqlSession();
	SqlSession sqlSession;
	List<SaleDTO> list;
	int result;
	
	public Boolean insertSale(HashMap<String, Object> sMap) {
		sqlSession = sqlSessionFactory.openSession(true);
		try {
			result = sqlSession.update("sale.insertSale", sMap);
			if (result > 0) {
				System.out.println("▣▣ " + sMap.get("sname") + " 제품 " + sMap.get("cnt") + "개 판매금액 " + sMap.get("tprice") + "원 등록되었습니다.");
				
			} else {
				System.out.println("▣▣ " + sMap.get("sname") + " 제품 판매금액 등록에 실패하였습니다.관리자에게 문의하세요.");
				return true;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		} finally {
			sqlSession.close();
			
		}
		
		return false;
	}
	
	public void totalSale() {
		sqlSession = sqlSessionFactory.openSession();
		try {
			list = sqlSession.selectList("totalSale");
			if(list.size() > 0) {
				System.out.println("▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
				System.out.println("▣▣ 일일 매출현황 출력 ▣▣");
				viewSaleList(list);
			} else {
//				System.out.println("▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
				System.out.println("▣▣ 일일 매출현황 출력에 실패했습니다. 관리자에게 문의하세요. ");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		} finally {
			sqlSession.close();
			
		}
	}
	
	// 조회결과 viewer
	public void viewSaleList(List<SaleDTO> list) {
		System.out.println("▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
		System.out.println("▣▣ 제품명\t수량\t판매가소계\t판매일자▣▣");
		System.out.println("▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
		for (SaleDTO line : list) {
			System.out.println(line.toString());
		}		
		System.out.println("▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
	}
	
	// rollback 문제를 피하기 한 Commit() 정의(반영되지 않는다.)
//	public void Commit() {
//		sqlSession = sqlSessionFactory.openSession();
//		try {
//			sqlSession.commit();
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//			
//		} finally {
//			sqlSession.close();
//			
//		}
//		
//		
//	}
}
