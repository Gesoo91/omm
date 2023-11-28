package com.omm.lunch.dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.omm.lunch.board.Board;
import com.omm.lunch.db.Dao;
import com.omm.lunch.dto.Dto;

public class DaoBoard extends Dao{
	/* (1/5)삭제 */
	public void del(String no) {
		super.connect();	//conect()라고 해도 됨.	//[고정1,2,3]
//		connect();
		String sql = String.format("delete from %s where l_no=%s"
				,Board.BOARD_MAIN, no);
		super.update(sql);
		super.close();	//[고정4,5]
	}
	/* (2/5)쓰기 */
	public void write(Dto d) {
		super.connect();	//[고정1,2,3]
		String sql = String.format(
				"insert into %s (l_title,l_id,l_content) values ('%s','%s','%s')"
				,Board.BOARD_MAIN, d.title,d.id,d.text);
		super.update(sql);
		super.close();	//[고정4,5]
	}
	/* (3/5)글 읽기 */
	public Dto read(String no) {
		super.connect();	//[고정1,2,3]
		Dto post = null;
		try {
			//여기에 코딩하시오:
			//sql 되는거 예문 아래에 복붙해두고 비교해서 작성하시고. (실무에선 혼남. 지울것)
//			select * from ps_board_free where b_no=4;
			String sql = String.format(
					"select * from %s where l_no=%s"
					,Board.BOARD_MAIN, no);
			System.out.println("sql:"+sql);//todo
			ResultSet rs = st.executeQuery(sql);
			rs.next();
			post = new Dto(
					rs.getString("l_no"),
					rs.getString("l_title"),
					rs.getString("l_content"),
					rs.getString("l_id"),
					rs.getString("l_time"),
					rs.getString("l_hit"),
					rs.getString("l_REPLY_COUNT"),
					rs.getString("l_REPLY_ORI")
					);
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.close();	//[고정4,5]
		return post;
	}	
	/* (4/5)글 리스트 */
	/* (4/5)글 리스트 */
	public ArrayList<Dto> listbackup(String page) {
		super.connect();	//[고정1,2,3]
		ArrayList<Dto> posts = new ArrayList<>();
		try {

			int startIndex = ((Integer.parseInt(page))-1)*Board.LIST_AMOUNT;
			
			//여기에 코딩하시오:
			//sql 되는거 예문 아래에 복붙해두고 비교해서 작성하시고. (실무에선 혼남. 지울것)
//			select * from ps_board_free where b_no=4;
//			select * from board order by b_no desc limit 20,5;
			String sql = String.format(
					"select * from %s limit %s,%s"
					,Board.BOARD_MAIN,startIndex,Board.LIST_AMOUNT);
			System.out.println("sql:"+sql);
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {				
				posts.add(new Dto(
						rs.getString("l_no"),
						rs.getString("l_title"),
						rs.getString("l_content"),
						rs.getString("l_id"),
						rs.getString("l_time"),
						rs.getString("l_hit"),
						rs.getString("l_reply_count"),
						rs.getString("l_reply_ori")
						));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.close();	//[고정4,5]
		return posts;
	}
	public ArrayList<Dto> list() {
		super.connect();	//[고정1,2,3]
		ArrayList<Dto> posts = new ArrayList<>();
		try {
			String sql = String.format(
					"select * from %s"
					,Board.BOARD_MAIN);
			System.out.println("sql:"+sql);
			System.out.print("posts size: "+posts.size());
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {				
				posts.add(new Dto(
						rs.getString("l_no"),
						rs.getString("l_title"),
						rs.getString("l_text"),
						rs.getString("l_datetime"),
						rs.getString("l_hit"),
						rs.getString("l_reply_ori"),
						rs.getString("l_reply_count"),
						rs.getString("l_id"),
						rs.getString("l_category")
						));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.close();	//[고정4,5]
		return posts;
	}
	/* (5/5)수정 */
	public void edit(Dto d,String no) {
		super.connect();	//[고정1,2,3]
		String sql = String.format(
				"update %s set l_title='%s',l_content='%s' where l_no=%s"
				,Board.BOARD_MAIN, d.title,d.text,no);
		super.update(sql);
		super.close();	//[고정4,5]
	}	
	/* 총 글 수 구하기 */
	public int getPostCount() {
		int count = 0;
		super.connect();	//[고정1,2,3]
		try {
			String sql = String.format(
					"select count(*) from %s"
					,Board.BOARD_MAIN);
			System.out.println("sql:"+sql);//todo
			ResultSet rs = st.executeQuery(sql);
			rs.next();
			count = rs.getInt("count(*)");
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.close();	//[고정4,5]
		return count;
	}
	public int getPostCountBest() {
		int count = 0;
		super.connect();	//[고정1,2,3]
		try {
			String sql = String.format(
					"select count(*) from %s"
					,Board.BOARD_BEST);
			System.out.println("sql:"+sql);//todo
			ResultSet rs = st.executeQuery(sql);
			rs.next();
			count = rs.getInt("count(*)");
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.close();	//[고정4,5]
		return count;
	}
	/* 총 글 수 구하기 */
	public int getSearchPostCount(String word) {
		int count = 0;
		super.connect();	//[고정1,2,3]
		try {
			String sql = String.format(
					"select count(*) from %s where l_title like '%%%s%%'"
					,Board.BOARD_MAIN,word);
			System.out.println("sql:"+sql);//todo
			ResultSet rs = st.executeQuery(sql);
			rs.next();
			count = rs.getInt("count(*)");
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.close();	//[고정4,5]
		return count;
	}	
	/* 글 리스트<검색> */
	public ArrayList<Dto> listSearch(String word,String page) {
		super.connect();	//[고정1,2,3]
		ArrayList<Dto> posts = new ArrayList<>();
		try {

			int startIndex = ((Integer.parseInt(page))-1)*3;
			
			//여기에 코딩하시오:
			//sql 되는거 예문 아래에 복붙해두고 비교해서 작성하시고. (실무에선 혼남. 지울것)
//			select * from ps_board_free where b_title like '%1%';
			String sql = String.format(
					"select * from %s where l_title like '%%%s%%' limit %s,3"
					,Board.BOARD_MAIN,word,startIndex);
			System.out.println("sql:"+sql);
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {				
				posts.add(new Dto(
						rs.getString("l_no"),
						rs.getString("l_title"),
						rs.getString("l_content"),
						rs.getString("l_id"),
						rs.getString("l_time"),
						rs.getString("l_hit"),
						rs.getString("l_reply_count"),
						rs.getString("l_reply_ori")
						));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.close();	//[고정4,5]
		return posts;
	}
	/* 총 페이지 수 구하기 */
	public int getTotalPageCount() {
		int totalPageCount = 0;
		int count = getPostCount();	//만든거 재활용.
		
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
		int count = getSearchPostCount(word);
		
		if(count % 5 == 0){		//case1. 나머지가 없이 딱 떨어지는 경우
			totalPageCount = count / 5;
		}else{					//case2. 나머지가 있어서 짜투리 페이지가 필요한 경우
			totalPageCount = count / 5 + 1;
		}
		return totalPageCount;
	}
	public ArrayList<Dto> listBest(String page) {
        super.connect();
        ArrayList<Dto> posts = new ArrayList<>();

        try {
            int startIndex = ((Integer.parseInt(page)) - 1) * Board.LIST_AMOUNT;
            
            // 수정된 부분
            String sql = String.format(
                    "select * from %s order by r_total_like desc limit %s,%s",
                    Board.BOARD_BEST, startIndex, Board.LIST_AMOUNT);

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                posts.add(new Dto(
                        rs.getString("r_no"),
                        rs.getString("r_menu"),
                        rs.getString("r_category"),
                        rs.getString("r_total_like")
                        // ... (다른 필요한 속성들 추가)
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            super.close();
        }

        return posts;
    }

	
}