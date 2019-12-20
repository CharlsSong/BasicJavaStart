package problem.DDBoard;

import java.util.ArrayList;

public class DTOTestMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		BoardDTO bDto = new BoardDTO();
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();

		for (int i = 0; i < 10; i++) {
			BoardDTO bDto = new BoardDTO();
			bDto.setBno(i);
			list.add(bDto);
			System.out.println(bDto.getBno());
			System.out.println(list.get(i).getBno());
			System.out.println(list.toString());
			
		}
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getBno());
		}
		for (BoardDTO boardDTO : list) {
			System.out.println(boardDTO.getBno());
		}
	}

}
