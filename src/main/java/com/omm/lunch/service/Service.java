package com.omm.lunch.service;
import java.util.ArrayList;

import com.omm.lunch.dao.DaoBoard;
import com.omm.lunch.dto.Dto;
public class Service {
	DaoBoard dao;
	public Service () {
		dao = new DaoBoard();
	}
	public void del (String no) {
		dao.del(no);
	}
	public void write(Dto d) {
		dao.write(d);
	}
	public Dto read(String no) {
	 return dao.read(no); //read는 조회된 데이터를 dto객체로 생성해서 반환해야 하므로 return을 써서 호출한다. retrun post와 같은 개념.
	}
	public ArrayList<Dto> list() {
		return dao.list();
	}
	public void edit(Dto d,String no) {
		dao.edit(d,no);
	}
	
	//dao 관련없는 함수들 이동
	/* 총 페이지 수 구하기 */
	public int getTotalPageCount() {
		int totalPageCount = 0;
		int count = dao.getPostCount();	//만든거 재활용.
		
		if(count % 5 == 0){		//case1. 나머지가 없이 딱 떨어지는 경우
			totalPageCount = count / 5;
		}else{					//case2. 나머지가 있어서 짜투리 페이지가 필요한 경우
			totalPageCount = count / 5 + 1;
		}
		return totalPageCount;
	}	
	/* 총 페이지 수 구하기<검색> */
	public int getSearchTotalPageCount(String word) {
		int totalPageCount = 0;
		int count = dao.getSearchPostCount(word);
		
		if(count % 5 == 0){		//case1. 나머지가 없이 딱 떨어지는 경우
			totalPageCount = count / 5;
		}else{					//case2. 나머지가 있어서 짜투리 페이지가 필요한 경우
			totalPageCount = count / 5 + 1;
		}
		return totalPageCount;
	}	
}


