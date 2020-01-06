package problem.marcket;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import mybatis.SqlMapConfig;

public class ProductDAO {
	SqlSessionFactory sqlSessionFactory = SqlMapConfig.getSqlSession(); 
	SqlSession sqlSession;
	SaleDAO sDao = new SaleDAO();
	int result;
	List<ProductDTO> list;
	Boolean flag;
	
	
//	List<ProductDTO> listTemp; // 일일 매출현황용 리스트 

	// 1. 제품 판매시 해당 제품의 수량 감소 및 로그유지
	// 수량 감소 :  select
	// 로그 유지 : list<ProcuctDTO> listTemp; 제품번호, 제품명, 제조사, 단가, 변동량
	// 1. 현재 편의점에 등록된 재고가 0 이상인 상품을 모두 조회해서 출력한다.
	// 2. 사용자가 구매한다.(1번에 한상품만 수량 여러개 구매가능)
	//	  구매할 제품번호와 수량을 입력받는다.
	// 3. 입력 받은 값으로 tbp_product에서 cnt를 판매한 수만큼 뺀다
	// 4. tbl_sale 테이블 생성
//	sno number PK,
//	sname varchar2(100) not null,
//	cnt number not null,
//	tprice number not null,
//	regdate Date Default sysdate
	
	// 5. tbl_sale에 판매기록을 저장한다.
	// sno 시퀀스값, sname은 코카콜라, cnt는 3, tprice 수량x단가로 저장
	
	// 제품판매리스트출력 : 제고가 0보다 큰 제품만 조회
	public List<ProductDTO> salePdtList() {
		sqlSession = sqlSessionFactory.openSession();
		try {
			list = sqlSession.selectList("pdt.salePdtList");
			
			if (list.size() <= 0) {
				System.out.println("▣▣ 판매 제품 조회에 실패하였습니다. 관리자에게 문의하세요.");
			} else {
				viewPdtList(list);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			sqlSession.close();
			
		}
		
		return list;
	}
	
//	public Boolean cntCheck(int num, int cnt) {
//		// 1. 제고와 판매량 비교
//		if(cnt > list.get(num-1).getCnt()) {
//			System.out.println("▣▣ " + list.get(num-1).getPname() + " 제품 제고가 부족하여 판매할 수 없습니다.");
//			flag = true;
//		} else {
//			flag = false;
//		}
//	
//		return flag;
//	}
	
	public void salePdt(int num, int cnt) {
//		sqlSession = sqlSessionFactory.openSession();
//		try {
//			System.out.println(num + ", " + cnt +", " + list.get(num-1).getPrice());
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//			
//		} finally {
//			sqlSession.close();
//			
//		}
		
		sqlSession = sqlSessionFactory.openSession();
		try {
			
//			ProductDTO pDto = new ProductDTO(); 
//			pDto = sqlSession.selectOne("pdt.searchPdt", pname);
//	
		if(cnt > list.get(num-1).getCnt()) {
			System.out.println("▣▣ " + list.get(num-1).getPname() + " 제품 제고가 부족하여 판매할 수 없습니다.");
			return;
		}
			
			int tprice = cnt*list.get(num-1).getPrice();	// 판매금액계산

			
			// 2. 판매테이블 Insert처리
			
			HashMap<String,Object> sMap = new HashMap<>();
			sMap.put("sname", list.get(num-1).getPname());
			sMap.put("cnt", cnt);
			sMap.put("tprice", tprice);
			
			if(sDao.insertSale(sMap)) {		// 판매등록 실패시 
				return;
			} 
									
			// 3. 상품테이블 Update 처리
			HashMap<String,Object> map = new HashMap<>();
			map.put("pname", list.get(num-1).getPname());
			map.put("cnt", cnt);
			map.put("flag", "sub");
			result = sqlSession.update("pdt.cntChange", map);
			if (result > 0) {
				System.out.println("▣▣ " + list.get(num-1).getPname() + " 제품 " + cnt + "개 판매되었습니다.");			
				sqlSession.commit();	// tbl_product에 대한 commit수행  
//				sDao.Commit();			// tbl_sale에 대한 commit수행(수행되지 않는다.....~.~;)
			} else {
				System.out.println("▣▣ " + list.get(num-1).getPname() + " 제품 판매실패하였습니다.관리자에게 문의하세요.");
				return;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		} finally {
			sqlSession.close();
			
		}
	}

	// rollback 문제를 피하기 위해 묶어서 처리
//	public void salePdt(String pname, int cnt) {
//		sqlSession = sqlSessionFactory.openSession();
//		try {
//			
//			ProductDTO pDto = new ProductDTO(); 
//			pDto = sqlSession.selectOne("pdt.searchPdt", pname);
//			
//			// 1. 제고와 판매량 비교
//			if(cnt > pDto.getCnt()) {
//				System.out.println("▣▣ " + pDto.getPname() + " 제품 제고가 부족하여 판매할 수 없습니다.");
//				return;
//			}
//			
//			int tprice = cnt*pDto.getPrice();	// 판매금액계산
//			
//			// 2. 판매테이블 Insert처리
//			HashMap<String,Object> sMap = new HashMap<>();
//			sMap.put("sname", pDto.getPname());
//			sMap.put("cnt", cnt);
//			sMap.put("tprice", tprice);
//			
//			result = sqlSession.update("sale.insertSale", sMap);
//			if (result > 0) {
//				System.out.println("▣▣ " + pDto.getPname() + " 제품 " + cnt + "개 판매금액 " + tprice + "원 등록되었습니다.");
//			} else {
//				System.out.println("▣▣ " + pDto.getPname() + " 제품 판매금액 등록에 실패하였습니다.관리자에게 문의하세요.");
//				return;
//			}
//			
//			// 3. 상품테이블 Update 처리
//			HashMap<String,Object> map = new HashMap<>();
//			map.put("pname", pname);
//			map.put("cnt", cnt);
//			map.put("flag", "sub");
//			result = sqlSession.update("pdt.cntChange", map);
//			if (result > 0) {
//				System.out.println("▣▣ " + pname + " 제품 " + cnt + "개 판매되었습니다.");
//				sqlSession.commit();	// tbl_product와  tbl_sale에 대한 commit수행
//			} else {
//				System.out.println("▣▣ " + pname + " 제품 판매실패하였습니다.관리자에게 문의하세요.");
//				return;
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//			
//		} finally {
//			sqlSession.close();
//			
//		}
//	}
	
	
	// 2. 제품 등록&추가 기능 작동시 기존에 등록된 제품인지 최초입고제품인지 판별하는 기능
	public boolean pdtAlready(String pname) {
		sqlSession = sqlSessionFactory.openSession();
		try {
			result = sqlSession.selectOne("pdt.already", pname);
			if (result > 0) {
				System.out.println("▣▣ 등록된 제품명입니다.");
				flag = true;
			} else {
				System.out.println("▣▣ 등록되지 않은 제품명입니다.");
				flag = false;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		} finally {
			sqlSession.close();
			
		}
		return flag;
	}
	
	// 기존 제품 제고량 증가 
	public void cntPlusPdt(String pname, int cnt) {
		sqlSession = sqlSessionFactory.openSession(true);
		try {
			HashMap<String,Object> map = new HashMap<>();
			map.put("pname", pname);
			map.put("cnt", cnt);
			map.put("flag", "plus");
			result = sqlSession.update("pdt.cntChange", map);
			if (result > 0) {
				System.out.println("▣▣ " + pname + " 제품 " + cnt + "개 입고되었습니다.");
			} else {
				System.out.println("▣▣ " + pname + " 제품 입고실패하였습니다.관리자에게 문의하세요.");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		} finally {
			sqlSession.close();
			
		}
	}
	
	// 새로운 제품 등록
	public void insertPdt(ProductDTO pDto) {
		sqlSession = sqlSessionFactory.openSession(true);
		try {
			result = sqlSession.insert("pdt.insert", pDto);
			if (result > 0) {
				System.out.println("▣▣ " + pDto.getPname() + " 제품 등록 및 " + pDto.getCnt() + "개 입고 되었습니다.");
			} else {
				System.out.println("▣▣ " + pDto.getPname() + " 제품 등록 및 입고실패하였습니다.관리자에게 문의하세요.");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		} finally {
			sqlSession.close();
			
		}
	}
	
	// 제품 수정
	public void updatePdt(ProductDTO pDto) {
		sqlSession = sqlSessionFactory.openSession(true);
		try {		
			result = sqlSession.update("pdt.updatePdt", pDto);
			if (result > 0) {
				System.out.println("▣▣ " + pDto.getPname() + " 제품 정보가 수정되었습니다.");
			} else {
				System.out.println("▣▣ " + pDto.getPname() + " 제품 정보 수정에 실패하였습니다. 관리자에게 문의하세요.");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		} finally {
			sqlSession.close();
			
		}
	}
	
	// 제품 삭제
	public void deletePdt(String pname) {
		sqlSession = sqlSessionFactory.openSession(true);
		try {		
			result = sqlSession.update("pdt.deletePdt", pname);
			if (result > 0) {
				System.out.println("▣▣ " + pname + " 제품이 삭제되었습니다.");
			} else {
				System.out.println("▣▣ " + pname + " 제품 삭제에 실패하였습니다. 관리자에게 문의하세요.");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		} finally {
			sqlSession.close();
			
		}
	}
	
	// 제품 조회
	public void selectPdt() {
		sqlSession = sqlSessionFactory.openSession();
		try {
			list = sqlSession.selectList("pdt.selectPdt");
			if (list.size() <= 0) {
				System.out.println("▣▣ 제품 조회에 실패하였습니다. 관리자에게 문의하세요.");
			} else {
				viewPdtList(list);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			sqlSession.close();
			
		}
	}
	
	// 제품 검색
	public void searchPdt(String pname) {
		sqlSession = sqlSessionFactory.openSession();
		try {
			ProductDTO pDto = new  ProductDTO();
			pDto = sqlSession.selectOne("pdt.searchPdt", pname);
			
			if (pDto.getPno() <= 1000) {
				System.out.println("▣▣ 제품 조회에 실패하였습니다. 관리자에게 문의하세요.");
			} else {
				viewPdtDetail(pDto);								
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			sqlSession.close();
			
		}
	}

	// 7. 일일 매출현황 출력
	public void totalSale() {
		sDao.totalSale();
	}
	
	// 조회결과 viewer
	public void viewPdtList(List<ProductDTO> list) {
		int i = 1;
		System.out.println("▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
		System.out.println("▣▣ 선택\t제품번호\t제품명\t제조사\t입고단가\t제고\t입고일 ▣▣");
		System.out.println("▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
		for (ProductDTO line : list) {
			System.out.println("▣▣ " + i + "\t" + line.toString());
			i++;
		}		
		System.out.println("▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
	}
	
	// 제품 상세조회
	public void viewPdtDetail(ProductDTO pDto) {
		System.out.println("▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
		System.out.println("▣▣ 제품번호: "+ pDto.getPno());
		System.out.println("▣▣ 제품명: "+ pDto.getPname());
		System.out.println("▣▣ 제조사: "+ pDto.getCompany());
		System.out.println("▣▣ 제품단가: "+ pDto.getPrice());
		System.out.println("▣▣ 입고수량: "+ pDto.getCnt());
		System.out.println("▣▣ 입고일: "+ pDto.getRegdate());
		System.out.println("▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣");
	}
	
	
}
