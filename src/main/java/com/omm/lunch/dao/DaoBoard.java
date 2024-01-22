package com.omm.lunch.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;

import com.omm.lunch.board.Board;
import com.omm.lunch.db.Dao;
import com.omm.lunch.dto.BoardDto;
import com.omm.lunch.dto.RouletteDto;

public class DaoBoard extends Dao{
	/* (1/5)삭제 */
	public void del(String category, String no) {
		super.connect();	//conect()라고 해도 됨.	//[고정1,2,3]
//		connect();
		String sql = String.format("delete from %s where l_no=%s and l_category like '%s'"
				,Board.BOARD_MAIN
				,no
				,category);
		super.update(sql);
		super.close();	//[고정4,5]
	}
	/* (2/5)쓰기 */
	public void write(BoardDto d) {
		super.connect();	//[고정1,2,3]
		String sql = String.format(
				"insert into %s (l_category,l_title,l_user_id,l_text) values ('%s','%s','%s','%s')"
				,Board.BOARD_MAIN
				,d.category
				,d.title
				,d.id
				,d.text);
		System.out.println("쓰기sql:"+sql);
		super.update(sql);
		super.close();	//[고정4,5]
	}
	/* (3/5)글 읽기 */
	public BoardDto read(String category, String no) {
		super.connect();	//[고정1,2,3]
		BoardDto post = null;
		try {
			String sql = String.format(
					"select l_category,l_no,l_title,l_user_id,l_datetime,l_hit,l_text,l_reply_count,l_reply_ori from %s where l_no=%s and l_category like '%s'"
					,Board.BOARD_MAIN
					,no
					,category);
			System.out.println("sql:"+sql);//todo
			ResultSet rs = st.executeQuery(sql);
			rs.next();
			post = new BoardDto(
					rs.getString("l_no"),
					rs.getString("l_title"),
					rs.getString("l_text"),
					rs.getString("l_datetime"),
					rs.getString("l_hit"),
					rs.getString("l_reply_ori"),
					rs.getString("l_reply_count"),
					rs.getString("l_user_id"),
					rs.getString("l_category")
					);
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.close();	//[고정4,5]
		return post;
	}	
	/* (4/5)글 리스트 */
	/* (4/5)글 리스트 */
	public ArrayList<BoardDto> listbackup(String page) {
		super.connect();	//[고정1,2,3]
		ArrayList<BoardDto> posts = new ArrayList<>();
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
				posts.add(new BoardDto(
						rs.getString("l_no"),
						rs.getString("l_title"),
						rs.getString("l_text"),
						rs.getString("l_user_id"),
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
	public ArrayList<BoardDto> list2(String category, int startIndex) {
		super.connect();	//[고정1,2,3]
		ArrayList<BoardDto> posts = new ArrayList<>();
		try {
			String sql = String.format(
					"select * from %s where l_category like '%s' limit %d,%d"
					,Board.BOARD_MAIN
					,category
					,startIndex
					,Board.LIST_AMOUNT);
			System.out.println("sql:"+sql);
			System.out.println("startIndex:"+startIndex);
			System.out.print("list2에 posts size: "+posts.size());
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {				
				posts.add(new BoardDto(
						rs.getString("l_no"),
						rs.getString("l_title"),
						rs.getString("l_text"),
						rs.getString("l_datetime"),
						rs.getString("l_hit"),
						rs.getString("l_reply_ori"),
						rs.getString("l_reply_count"),
						rs.getString("l_user_id"),
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
	public void edit(BoardDto d,String no) {
		super.connect();	//[고정1,2,3]
		String sql = String.format(
				"update %s set l_title='%s',l_text='%s' where l_no=%s"
				,Board.BOARD_MAIN
				,d.title
				,d.text
				,no);
		super.update(sql);
		super.close();	//[고정4,5]
	}	
	/* 총 글 수 구하기 */
	public int getPostCount(String category) {
		int count = 0;
		super.connect();	//[고정1,2,3]
		try {
			String sql = String.format(
					"select count(*) from %s where l_category like '%s'"
					,Board.BOARD_MAIN, category);
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
	public int getSearchPostCount(String category, String word) {
		int count = 0;
		super.connect();	//[고정1,2,3]
		try {
			String sql = String.format(
					"select count(*) from %s where l_title like '%%%s%%' and l_category like '%s'"
					,Board.BOARD_MAIN
					,word
					,category);
			System.out.println("총글수sql:"+sql);//todo
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
	public ArrayList<BoardDto> list(String category, int startIndex, String word) {
		super.connect();	//[고정1,2,3]
		ArrayList<BoardDto> posts = new ArrayList<>();
		try {

			String sql = String.format(
					"select l_category,l_no,l_title,l_user_id,l_datetime,l_hit,l_text,l_reply_count,l_reply_ori from %s where l_title like '%%%s%%' and l_category like '%s' limit %s,%s"
					,Board.BOARD_MAIN,word,category,startIndex,Board.LIST_AMOUNT);
			System.out.println("sql:"+sql);
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {				
				posts.add(new BoardDto(
						rs.getString("l_no"),
						rs.getString("l_title"),
						rs.getString("l_text"),
						rs.getString("l_datetime"),
						rs.getString("l_hit"),
						rs.getString("l_reply_ori"),
						rs.getString("l_reply_count"),
						rs.getString("l_user_id"),
						rs.getString("l_category")
						));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.close();	//[고정4,5]
		return posts;
	}
	/* 베스트 게시판 리스트*/
	public ArrayList<RouletteDto> listBest(String page, String orderByColumn) {
        super.connect();
        ArrayList<RouletteDto> posts = new ArrayList<>();

        try {
            int startIndex = ((Integer.parseInt(page)) - 1) * Board.LIST_AMOUNT;
            
            // 수정된 부분
            String sql = String.format(
                    "select * from %s order by %s desc, r_total_like DESC limit %s,%s",
                    Board.BOARD_BEST, orderByColumn, startIndex, Board.LIST_AMOUNT);
            //orderByColumn 으로 각 페이지별 정렬 기준잡고, 그다음 total_like 순으로 한번더 정 
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                posts.add(new RouletteDto(
                        rs.getString("r_menu"),
                        rs.getString("r_category"),
                        rs.getString("r_total_like"),
                        rs.getString("r_weekly_like"),
                        rs.getString("r_month_like")
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
	
	/* 룰렛 메뉴 추가*/
	public void addMenu(RouletteDto menu) {
		super.connect();	//[고정1,2,3]
		String sql = String.format(
				"insert into %s (r_menu,r_category,r_user_id) values ('%s','%s','%s')"
				,Board.BOARD_BEST
				,menu.menu
				,menu.category
				,menu.id
				);
		System.out.println("쓰기sql:"+sql);
		super.update(sql);
		super.close();	//[고정4,5]
	}
	public class RouletteResult {
        private final RouletteDto rouletteResult;
        private final ArrayList<RouletteDto> subRouletteResults;

        public RouletteResult(RouletteDto rouletteResult, ArrayList<RouletteDto> subRouletteResults) {
            this.rouletteResult = rouletteResult;
            this.subRouletteResults = subRouletteResults;
        }

        public RouletteDto getRouletteResult() {
            return rouletteResult;
        }

        public ArrayList<RouletteDto> getSubRouletteResults() {
            return subRouletteResults;
        }

		public void setMessage(String string) {
			// TODO Auto-generated method stub
			
		}
    }
    public RouletteResult performRoulette() {
        super.connect();
        RouletteDto rouletteResult = null;
        ArrayList<RouletteDto> subRouletteResults = new ArrayList<>();

        try {
            int randomValue = generateRandom();

            // 룰렛 실행
            String rouletteSql = String.format(
                "SELECT * FROM %s WHERE r_no = %d",
                Board.BOARD_BEST,
                randomValue
            );
            System.out.println("룰렛sql:" + rouletteSql);
            ResultSet rouletteRs = st.executeQuery(rouletteSql);
            rouletteRs.next();
            rouletteResult = new RouletteDto(
                String.valueOf(rouletteRs.getInt("r_no")),
                rouletteRs.getString("r_menu"),
                rouletteRs.getString("r_category"),
                rouletteRs.getString("r_user_id"),
                rouletteRs.getString("r_total_like"),
                rouletteRs.getString("r_weekly_like")
            );

            // 서브 룰렛 실행
            String subRouletteSql = String.format(
                "SELECT * FROM %s WHERE r_category = (SELECT r_category FROM %s WHERE r_no = %d) AND r_no != %d ORDER BY RAND() LIMIT 5",
                Board.BOARD_BEST,
                Board.BOARD_BEST,
                randomValue,
                randomValue
            );
            System.out.println(subRouletteSql);
            ResultSet subRouletteRs = st.executeQuery(subRouletteSql);
            while (subRouletteRs.next()) {
                subRouletteResults.add(new RouletteDto(
                    subRouletteRs.getString("r_menu"),
                    subRouletteRs.getString("r_category"),
                    subRouletteRs.getString("r_total_like")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            super.close();
        }

        return new RouletteResult(rouletteResult, subRouletteResults);
    }

    private int generateRandom() {
        int random = 0;
        try {
            String sql = String.format("SELECT COUNT(r_no) FROM %s", Board.BOARD_BEST);
            ResultSet countRs = st.executeQuery(sql);
            if (countRs.next()) {
                int count_no = countRs.getInt(1);
                random = (int) Math.floor(Math.random() * count_no) + 1;
            }
            countRs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return random;
    }
    public void processLike(String num) {

    	try {
    		// 1. 데이터베이스 연결
    		connect();
    		
    		// 2. 현재 날짜와 주를 가져오기
    		Calendar cal = Calendar.getInstance();
            int currentWeek = cal.get(Calendar.WEEK_OF_YEAR);
            int currentMonth = cal.get(Calendar.MONTH) + 1;
            
            // 3. 좋아요 업데이트 쿼리
            String updateQuery = "UPDATE omm_roulette " +
                    "SET r_total_like = r_total_like + 1, " +
                    "    r_weekly_like = r_weekly_like + 1, " +
                    "    r_month_like = r_month_like + 1 " +
                    "WHERE r_no = " + num;
            System.out.println("Update Query: " + updateQuery);

            // 4. 쿼리 실행
            int rowsAffected = st.executeUpdate(updateQuery);

            if (rowsAffected > 0) {
            	System.out.println("좋아요가 업데이트되었습니다.");

                // 5. 한 주가 지나면 주간 좋아요 초기화
                String resetWeeklyLikeQuery = "UPDATE omm_roulette " +
                        "SET r_weekly_like = 0 " +
                        "WHERE DATE_FORMAT(r_like_time, '%Y%u') < " + currentWeek;
                st.executeUpdate(resetWeeklyLikeQuery);
                System.out.println("weekly like reset for week" + currentWeek);

                // 6. 한 달이 지나면 주간 좋아요 초기화
                String resetMonthLikeQuery = "UPDATE omm_roulette " +
                        "SET r_month_like = 0 " +
                        "WHERE YEARWEEK(r_like_time) < " + currentMonth;
                st.executeUpdate(resetMonthLikeQuery);
            } else {
            	System.out.println("좋아요 업데이트에 실패했습니다.");
            }
    	}
    	catch (Exception e) {
    		e.printStackTrace();
            System.out.println("오류가 발생했습니다.");
            
    	} finally {
            // 7. 데이터베이스 연결 해제
            close();
        }
    }


}