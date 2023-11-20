package com.omm.lunch.board;

public class Board {
	static public final int LIST_AMOUNT = 10;	//하나의 리스트에 보일 글 수 /한페이지에 있는 글 갯수
	static public final int PAGE_LINK_AMOUNT = 5;	//페이지 링크 한 블럭에 보일 페이지 링크 갯수(<이전> 1,2,3<다음> 이런식.
	
	public static final String BOARD_MAIN = "omm_board";	//*에다가 테이블 이름 넣으면됨.
	
	
	int totalPage = 0;
}
