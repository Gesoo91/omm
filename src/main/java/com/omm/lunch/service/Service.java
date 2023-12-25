package com.omm.lunch.service;
import java.util.ArrayList;

import com.omm.lunch.dao.DaoBoard;
import com.omm.lunch.dao.DaoBoard.RouletteResult;
import com.omm.lunch.dto.BoardDto;
import com.omm.lunch.dto.RouletteDto;
import com.omm.lunch.board.BoardListProcessor;
public class Service {
	DaoBoard dao;
	public Service () {
		dao = new DaoBoard();
	}
	public void del (String category, String no) {
		dao.del(category, no);
	}
	public void write(BoardDto d) {
		dao.write(d);
	}
	public BoardDto read(String category, String no) {
	 return dao.read(category, no); //read는 조회된 데이터를 dto객체로 생성해서 반환해야 하므로 return을 써서 호출한다. retrun post와 같은 개념.
	}
	public BoardListProcessor list(String category, String currentPage, String word) {
		if(currentPage==null) {
			currentPage="1";
		}
		BoardListProcessor blp = new BoardListProcessor(dao,category,currentPage,word);
		System.out.println("Category value: " + category); // 디버그용 출력
		return blp;
	}
	public void edit(BoardDto d,String no) {
		dao.edit(d,no);
	}
	public ArrayList<RouletteDto> listBest(String page, String orderByColumn) {
		if(page==null) {
			page="1";
		}
		return dao.listBest(page, orderByColumn);
	}
	public void addMenu(RouletteDto menu) {
		dao.addMenu(menu);
	}
	public RouletteResult roulette () {
		return dao.performRoulette();
	}
//	public ArrayList<RouletteDto> subroulette() {
//		return dao.subroulette();
//	}
}


